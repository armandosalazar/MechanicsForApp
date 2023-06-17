package me.armandosalazar.mechanicsforapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import me.armandosalazar.mechanicsforapp.databinding.ActivitySidemenuBinding;
import me.armandosalazar.mechanicsforapp.models.User;

public class MenuActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivitySidemenuBinding binding;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySidemenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        user = (User)getIntent().getSerializableExtra("user");
        String nameOfUser = "";
        String lastNameOfUser = "";
        String email = "";
        String fullName = "";
        //Obtenemos datos del usuario
        if (user == null){
            SharedPreferences prefs = getSharedPreferences("user.dat", MODE_PRIVATE);
            nameOfUser = prefs.getString("name", "asdasd");
            lastNameOfUser  = prefs.getString("lastName", "aaa");
            email = prefs.getString("email","asdas");
            fullName = nameOfUser+" "+lastNameOfUser;
        }else{
            nameOfUser = user.getName();
            lastNameOfUser = user.getLastName();
            email = user.getEmail();
            fullName = nameOfUser + lastNameOfUser;
        }


        setSupportActionBar(binding.appBarMenu.toolbar);
        binding.appBarMenu.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.txtUserName);
        TextView navUserEmail = (TextView) headerView.findViewById(R.id.txtUserEmail);

        navUsername.setText(fullName);
        navUserEmail.setText(email);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_menu);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_menu);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.itmLogout:
                closeSession();
                break;
            case R.id.itmCloseApp:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void closeSession() {
        SharedPreferences preferences = getSharedPreferences("user.dat", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();

        Intent logOut = new Intent(this,LoginActivity.class);
        logOut.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(logOut);
        finish();
    }

//    public void showDateDialog(View view){
//        day = calendar.get(Calendar.DAY_OF_MONTH);
//        month = calendar.get(Calendar.MONTH);
//        year = calendar.get(Calendar.YEAR);
//
//        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
//                (datePicker, i, i1, i2) -> txtResponse.setText(i+"/"+(i1+1)+"/"+i2),year,month,day);
//        //datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.MAGENTA));
//        datePickerDialog.show();
//    }
}