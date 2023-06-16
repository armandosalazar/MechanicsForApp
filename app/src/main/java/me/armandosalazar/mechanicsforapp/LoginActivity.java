package me.armandosalazar.mechanicsforapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import me.armandosalazar.mechanicsforapp.models.User;

public class LoginActivity extends AppCompatActivity {

    private EditText email, password;
    private CheckBox remember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.txtEmail);
        password = findViewById(R.id.txtPassword);
        remember = findViewById(R.id.cbRemember);
    }

    public void login(View view) {
        String email = this.email.getText().toString();
        String password = this.password.getText().toString();
        boolean remember = this.remember.isChecked();
        User user = new User(email.trim(), password.trim(), remember);
        Log.d("SharedPreferences",""+user.getEmail());

        if (user.isRegistered()) {
            savePreferences(user);
        }

        Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
        startActivity(intent);
        finish();
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
}