package me.armandosalazar.mechanicsforapp.dao;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import me.armandosalazar.mechanicsforapp.models.Mechanic;
import me.armandosalazar.mechanicsforapp.models.User;

public class DAO {
    private static DAO instance;
    private static SharedPreferences sharedPreferences;
    private static Gson gson;

    public static DAO getInstance(SharedPreferences sharedPreferences) {
        DAO.sharedPreferences = sharedPreferences;
        gson = new Gson();


        if (instance == null) {
            instance = new DAO();
        }
        return instance;
    }

    // TODO: Implement DAO methods for Users
    public void createUser(User user) {
        ArrayList<User> users = getUsers();
        if (users == null) {
            users = new ArrayList<>();
        }
        users.add(user);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("users", gson.toJson(users));
        editor.apply();
    }

    public ArrayList<User> getUsers() {
        Type type = new TypeToken<ArrayList<User>>() {
        }.getType();
        return gson.fromJson(sharedPreferences.getString("users", ""), type);
    }

    // TODO: Implement DAO methods for Mechanic
    public void createMechanic(Mechanic mechanic) {
        ArrayList<Mechanic> mechanics = getMechanics();
        if (mechanics == null) {
            mechanics = new ArrayList<>();
        }

        mechanics.add(mechanic);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("mechanics", gson.toJson(mechanics));
        editor.apply();
    }

    public ArrayList<Mechanic> getMechanics() {
        Type type = new TypeToken<ArrayList<Mechanic>>() {
        }.getType();
        return gson.fromJson(sharedPreferences.getString("mechanics", ""), type);
    }

    // TODO: Verify user
    public User userExist(String email, String password) {
        ArrayList<User> users = getUsers();
        ArrayList<Mechanic> mechanics = getMechanics();
        for (User user : users
        ) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        for (Mechanic mechanic : mechanics
        ) {
            if (mechanic.getEmail().equals(email) && mechanic.getPassword().equals(password)) {
                return mechanic;
            }
        }

        return null;
    }

}
