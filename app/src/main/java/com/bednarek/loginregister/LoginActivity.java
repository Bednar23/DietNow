package com.bednarek.loginregister;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    FirebaseAuth mAuth;
    EditText Name, Password;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        Name = (EditText) findViewById(R.id.Name);
        Password = (EditText) findViewById(R.id.Password);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        findViewById(R.id.toRegister).setOnClickListener(this);
        findViewById(R.id.Login).setOnClickListener(this);

    }

    private void userLogin(){

        String name = Name.getText().toString().trim();
        String password = Password.getText().toString().trim();

        if(name.isEmpty()){
            Name.setError("Login jest wymagany");
            Name.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(name).matches()){
            Name.setError("Proszę podać poprawny adres email");
            Name.requestFocus();
            return;
        }

        if(password.isEmpty()){
            Password.setError("Hasło jest wymagane");
            Password.requestFocus();
            return;
        }
        if(password.length()<6){
            Password.setError("Hasło musi składać się z co najmniej 6 znaków");
            Password.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(name, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if(task.isSuccessful()){
                    Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.toRegister:
                startActivity(new Intent(this, SignUpActivity.class));

                break;

            case R.id.Login:
                userLogin();
                break;
        }
    }
}
