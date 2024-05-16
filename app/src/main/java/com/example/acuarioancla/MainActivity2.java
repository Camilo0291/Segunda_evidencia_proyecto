package com.example.acuarioancla;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity2 extends AppCompatActivity {

    private EditText txtUser1, txtPass1;
    private Button btnIngresar2, btnIngresar3;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Inicializar Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Inicializar vistas
        txtUser1 = findViewById(R.id.txtUser1);
        txtPass1 = findViewById(R.id.txtPass1);
        btnIngresar2 = findViewById(R.id.btnIngresar2);
        btnIngresar3 = findViewById(R.id.btnIngresar3);

        // Configurar OnClickListener para btnIngresar2
        btnIngresar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtUser1.getText().toString().trim();
                String password = txtPass1.getText().toString().trim();
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity2.this, "Por favor, ingresa ambos datos",
                            Toast.LENGTH_SHORT).show();
                } else {
                    loginUser(email, password);
                }
            }
        });

        // Configurar OnClickListener para btnIngresar3
        btnIngresar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar al MainActivity sin realizar ninguna acción de inicio de sesión
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(MainActivity2.this, MainActivity3.class));
                            Toast.makeText(MainActivity2.this, "¡CORRECTO!", Toast.LENGTH_SHORT).show();
                        } else {
                            // Si el inicio de sesión falla, mostrar un mensaje al usuario
                            Toast.makeText(MainActivity2.this, "Error al ingresar los datos: " +
                                    task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity2.this, "Error al ingresar los datos", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
