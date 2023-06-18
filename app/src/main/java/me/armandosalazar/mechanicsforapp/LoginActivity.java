package me.armandosalazar.mechanicsforapp;

import androidx.appcompat.app.AlertDialog;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.armandosalazar.mechanicsforapp.dao.DAO;
import me.armandosalazar.mechanicsforapp.models.User;

public class LoginActivity extends AppCompatActivity {

    // Share Preferences instance
    private SharedPreferences sharedPreferences;

    private TextInputEditText email, password;
    private TextInputLayout emailContainer, passContainer;
    private CheckBox remember;
    private Button btnRegister;
    private boolean userFounded;
    private String name, lastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // init share preferences
        sharedPreferences = getSharedPreferences("mechanics.dat", MODE_PRIVATE);

        emailContainer = findViewById(R.id.txtInputEmail);
        passContainer = findViewById(R.id.txtInputPassword);


        email = findViewById(R.id.txtEmail);
        password = findViewById(R.id.txtPassword);
        remember = findViewById(R.id.cbRemember);

        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(view -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });

        ArrayList<User> users = DAO.getInstance(sharedPreferences).getUsers();
        if (users != null) {
            for (User user : users) {
                Log.d("User", user.toString());
            }
        } else {
            Log.d("User", "No hay usuarios");
        }

    }

    public void login(View view) {
        String email = String.valueOf(Objects.requireNonNull(emailContainer.getEditText()).getText());
        String password = String.valueOf(Objects.requireNonNull(passContainer.getEditText()).getText());
        User user = null;
        try {
             user = DAO.getInstance(sharedPreferences).userExist(email, password);

        }catch (Exception e){
            e.printStackTrace();
        }

        if (user != null) {
            Log.e("LOGIN", user.getEmail() + " - " + user.getPassword());
            Intent intent = new Intent(this, MenuActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
            finish();
        } else {
           showAlertDialog(view,"Usuario o contraseña incorrecta!");
        }


//        String email = this.email.getText().toString();
//        String password = this.password.getText().toString();
//        searchUserOnFile(email, password);
//        String[] words = name.split("(?=\\p{Upper})");
//        name = words[1];
//        lastName = words[3];
//        User user = new User(name, lastName, email, password, userFounded);
//        if (userFounded && remember.isChecked()) {
//            // savePreferences(user);
//            DAO.getInstance(sharedPreferences).createUser(user);
//        }
//
//        if (userFounded) {
//            Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
//            intent.putExtra("user", user);
//            startActivity(intent);
//            finish();
//        } else {
//            Toast.makeText(this, "Usuario o contraseña incorrecta!", Toast.LENGTH_SHORT).show();
//        }
    }

//    private void savePreferences(User user) {
//        SharedPreferences preferences = getSharedPreferences("user.dat", MODE_PRIVATE);
//        SharedPreferences.Editor editor = preferences.edit();
//        editor.putString("email", user.getEmail());
//        editor.putString("password", user.getPassword());
//        editor.putString("name", user.getName());
//        editor.putString("lastName", user.getLastName());
//        editor.putBoolean("remember", user.isRegistered());
//        editor.apply();
//    }

    public void back(View view) {
        finish();
    }

    public void showAlertDialog(View view,String message){
        AlertDialog.Builder cuadroAlert = new AlertDialog.Builder(LoginActivity.this);
        cuadroAlert.setTitle("Verifique los campos");

        cuadroAlert.setMessage(message).setPositiveButton("OK",
                (dialogInterface, i) -> {
                }).show();
    }
//    private void searchUserOnFile(String user, String pass) {
//        Pattern userPattern = Pattern.compile(user);
//        Pattern namePattern = Pattern.compile("Nombre:\\s[a-zA-Z]+");
//        int lineUser = 0;
//        int linePass = 0;
//
//        String[] archivos = fileList();
//
//        if (existeArchivo(archivos, "users.txt")) {
//            try {
//                //Objeto que asocia al archivo especificado, para lectura
//                InputStreamReader archivoInterno = new
//                        InputStreamReader(openFileInput("users.txt"));
//                //Objeto que relaciona el arhivo con el flujo de entrada (lectura)
//                BufferedReader leerArchivo = new BufferedReader(archivoInterno);
//                String linea = leerArchivo.readLine();
//
//                Matcher matcherUser;
//                Matcher nameMatcher;
//
//                while (linea != null && !userFounded) {
//                    matcherUser = userPattern.matcher(linea);
//                    nameMatcher = namePattern.matcher(linea);
//
//                    boolean userFound = matcherUser.find();
//                    boolean passFound = linea.matches("(.*)" + pass);
//                    boolean nameFound = nameMatcher.find();
//                    if (userFound && passFound) {
//                        if (linePass == lineUser) {
//                            userFounded = true;
//
//                        }
//                    }
//                    if (nameFound) {
//                        name = linea;
//                    }
//                    linea = leerArchivo.readLine();
//                    if (!userFound) {
//                        lineUser++;
//                    }
//
//                    linePass++;
//
//                }
//
//                leerArchivo.close();
//                archivoInterno.close();
//
//            } catch (IOException e) {
//                Toast.makeText(this, "Error al leer el archivo.", Toast.LENGTH_SHORT).show();
//            }
//        } else {
//            Toast.makeText(this, "El archivo no existe", Toast.LENGTH_SHORT).show();
//        }
//    }

//    private boolean existeArchivo(String[] archivos, String s) {
//        for (String archivo : archivos) {
//            if (s.equals(archivo)) {
//                return true;
//            }
//        }
//        return false;
//    }
}