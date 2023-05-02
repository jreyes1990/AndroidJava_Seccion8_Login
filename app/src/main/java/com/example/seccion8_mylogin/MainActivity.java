package com.example.seccion8_mylogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
  private EditText editTextEmail;
  private EditText editTextPassword;
  private Switch switchRemember;
  private Button buttonLogin;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    bindUI();
  }

  private void bindUI(){
    editTextEmail = (EditText) findViewById(R.id.editTextEmail);
    editTextPassword = (EditText) findViewById(R.id.editTextPassword);
    switchRemember = (Switch) findViewById(R.id.switchRemember);
    buttonLogin = (Button) findViewById(R.id.buttonLogin);
  }

  private boolean isValidEmail(String email){
    return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
  }

  private boolean isValidPassword(String password){
    return password.length() > 4;
  }
}