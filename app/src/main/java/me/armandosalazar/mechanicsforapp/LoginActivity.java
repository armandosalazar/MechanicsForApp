package me.armandosalazar.mechanicsforapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import me.armandosalazar.mechanicsforapp.dao.DAO;
import me.armandosalazar.mechanicsforapp.models.User;

public class LoginActivity extends AppCompatActivity {

    // Share Preferences instance
    private SharedPreferences sharedPreferences;

    private TextInputLayout emailContainer, passContainer;
    private CheckBox remember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // init share preferences
        sharedPreferences = getSharedPreferences("mechanics.dat", MODE_PRIVATE);

        // init components
        emailContainer = findViewById(R.id.txtInputEmail);
        passContainer = findViewById(R.id.txtInputPassword);

        remember = findViewById(R.id.cbRemember);

        Button btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(view -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });

        // check if user is logged
        if (sharedPreferences.getBoolean("isLogged", false)) {
            Intent intent = new Intent(this, MenuActivity.class);
            User user = DAO.getInstance(sharedPreferences).getSaveUser();
            intent.putExtra("user", user);
            startActivity(intent);
            finish();
        }

    }

    public void login(View view) {
        String email = String.valueOf(Objects.requireNonNull(emailContainer.getEditText()).getText());
        String password = String.valueOf(Objects.requireNonNull(passContainer.getEditText()).getText());
        User user = null;
        try {
            user = DAO.getInstance(sharedPreferences).userExist(email, password);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (user != null) {
            if (remember.isChecked()) {
                saveSession();
                // save user information
                DAO.getInstance(sharedPreferences).saveUser(user);
            }
            Log.e("LOGIN", user.getEmail() + " - " + user.getPassword());
            Intent intent = new Intent(this, MenuActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
            finish();
        } else {
            showAlertDialog(view, "Usuario o contraseña incorrecta!");
        }
    }


    public void back(View view) {
        finish();
    }

    public void showAlertDialog(View view, String message) {
        AlertDialog.Builder cuadroAlert = new AlertDialog.Builder(LoginActivity.this);
        cuadroAlert.setTitle("Verifique los campos");

        cuadroAlert.setMessage(message).setPositiveButton("OK", (dialogInterface, i) -> {
        }).show();
    }

    private void saveSession() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLogged", true);
        editor.apply();
    }

}