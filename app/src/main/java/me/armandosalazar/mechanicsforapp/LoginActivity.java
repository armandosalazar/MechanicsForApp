package me.armandosalazar.mechanicsforapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.armandosalazar.mechanicsforapp.models.User;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText email, password;
    private TextInputLayout emailContainer, passContainer;
    private CheckBox remember;
    private Button btnRegister;
    private boolean userFounded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailContainer = findViewById(R.id.txtInputEmail);
        passContainer = findViewById(R.id.txtInputPassword);


        email = findViewById(R.id.txtEmail);
        password = findViewById(R.id.txtPassword);
        remember = findViewById(R.id.cbRemember);
        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(view -> {
            Intent intent = new Intent(this,RegisterActivity.class);
            startActivity(intent);
        });

    }

    public void login(View view) {
        String email = this.email.getText().toString();
        String password = this.password.getText().toString();
        searchUserOnFile(email,password);
        Log.d("LoginActivity",email);
        User user = new User(email.trim(), password.trim(), userFounded);

        if (user.isRegistered()) {
            savePreferences(user);
            Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(this, "Usuario o contraseña incorrecta!", Toast.LENGTH_SHORT).show();
        }
    }

    private void savePreferences(User user) {
        SharedPreferences preferences = getSharedPreferences("user.dat", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("email", user.getEmail());
        Log.d("SharedPreferences",""+user.isRegistered());
        editor.putString("password", user.getPassword());
        editor.putBoolean("remember", user.isRegistered());
        editor.apply();
    }

    public void back(View view) {
        finish();
    }

    private void searchUserOnFile(String user, String pass){
        Pattern userPattern = Pattern.compile(user);
        Pattern passPattern = Pattern.compile(pass);
        int lineUser = 0;
        int linePass = 0;

        String []archivos = fileList();

        if (existeArchivo(archivos, "users.txt")){
            try {
                Log.d("Login","Si entro aqui");
                //Objeto que asocia al archivo especificado, para lectura
                InputStreamReader archivoInterno = new
                        InputStreamReader(openFileInput("users.txt"));
                //Objeto que relaciona el arhivo con el flujo de entrada (lectura)
                BufferedReader leerArchivo = new BufferedReader(archivoInterno);
                String linea = leerArchivo.readLine();

                String textoLeido = "";
                Matcher matcherUser;
                Matcher matcherPass;

                while(linea != null && !userFounded){
                    matcherUser = userPattern.matcher(linea);
                    matcherPass = passPattern.matcher(linea);

                    boolean userFound = matcherUser.find();
                    boolean passFound = matcherPass.find();
                    if (userFound && passFound){
                      if (linePass - lineUser == 1){
                          userFounded = true;
                      }
                    }
                    textoLeido += linea + '\n';
                    linea = leerArchivo.readLine();
                    if (!userFound){
                        lineUser++;
                    }

                    linePass++;

                }

                leerArchivo.close();
                archivoInterno.close();

            }catch (IOException e){
                Toast.makeText(this,"Error al leer el archivo.",Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "El archivo no existe", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean existeArchivo(String []archivos, String s){
        for (String archivo : archivos) {
            if (s.equals(archivo)) {
                return true;
            }
        }
        return false;
    }
}