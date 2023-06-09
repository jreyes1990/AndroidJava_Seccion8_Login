package com.example.seccion8_mylogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
  private SharedPreferences sharedPreferences;
  private EditText editTextEmail;
  private EditText editTextPassword;
  private Switch switchRemember;
  private Button buttonLogin;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    bindUI();

    sharedPreferences = getSharedPreferences("Preferences", Context.MODE_PRIVATE);

    setCredentialsIfExist();

    buttonLogin.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        if (login(email, password)){
          goToMain();
          saveOnPreferences(email, password);
        }
      }
    });
  }

  private void bindUI(){
    editTextEmail = (EditText) findViewById(R.id.editTextEmail);
    editTextPassword = (EditText) findViewById(R.id.editTextPassword);
    switchRemember = (Switch) findViewById(R.id.switchRemember);
    buttonLogin = (Button) findViewById(R.id.buttonLogin);
  }

  private void setCredentialsIfExist(){
    String email = getUserMailPreferences();
    String password = getUserPasswordPreferences();

    if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
      editTextEmail.setText(email);
      editTextPassword.setText(password);
    }
  }

  private boolean login(String email, String password){
    if (!isValidEmail(email)){
      Toast.makeText(this, "Email is not valid, please try again", Toast.LENGTH_SHORT).show();
      return false;
    } else if (!isValidPassword(password)) {
      Toast.makeText(this, "Password is not valid, 4 characters or more, please try again", Toast.LENGTH_SHORT).show();
      return false;
    }else{
      return true;
    }
  }

  private boolean isValidEmail(String email){
    return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
  }

  private boolean isValidPassword(String password){
    return password.length() >= 4;
  }

  private void goToMain(){
    Intent intent = new Intent(this, MainActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    startActivity(intent);
  }

  private void saveOnPreferences(String email, String password){
    if (switchRemember.isChecked()){
      SharedPreferences.Editor editor = sharedPreferences.edit();
      editor.putString("email", email);
      editor.putString("pass", password);
      //editor.commit();
      editor.apply();
    }
  }

  private String getUserMailPreferences(){
    return sharedPreferences.getString("email", "");
  }

  private String getUserPasswordPreferences(){
    return sharedPreferences.getString("pass", "");
  }
}