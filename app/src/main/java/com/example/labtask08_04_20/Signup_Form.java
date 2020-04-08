package com.example.labtask08_04_20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.firestore.auth.FirebaseAuthCredentialsProvider;

public class Signup_Form extends AppCompatActivity {

    EditText txtEmail, txtPassword, txtConfirmPassword;
    Button btn_register;

    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup__form);
        getSupportActionBar() .setTitle("Signup Form");

        txtEmail = (EditText) findViewById(R. id. txt_Email);
        txtPassword = (EditText) findViewById(R. id. txt_Password);
        txtConfirmPassword = (EditText) findViewById(R. id. txt_ConfirmPassword);
        btn_register = (Button) findViewById(R. id. btn_register);

        firebaseAuth = FirebaseAuth.getInstance();

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = txtEmail.getText(). toString() .trim();
                String password = txtPassword.getText(). toString() .trim();
                String confirmPassword = txtConfirmPassword.getText(). toString() .trim();

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(Signup_Form.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                return;
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(Signup_Form.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(confirmPassword)){
                    Toast.makeText(Signup_Form.this, "Please Confirm Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.equals(confirmPassword)){
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Signup_Form.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                       startActivity(new intent(getApplicationContext(),MainActivity.class));
                                        Toast.makeText(Signup_Form.this, "Registeration Completed.",
                                                Toast.LENGTH_SHORT).show();
                                    } else {

                                        Toast.makeText(Signup_Form.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }

                                    // ...
                                }
                            });
                }



            }
        });
    }
}
