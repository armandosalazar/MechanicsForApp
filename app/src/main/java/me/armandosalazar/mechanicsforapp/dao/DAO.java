package me.armandosalazar.mechanicsforapp.dao;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import me.armandosalazar.mechanicsforapp.models.Mechanic;
import me.armandosalazar.mechanicsforapp.models.User;
import me.armandosalazar.mechanicsforapp.models.Vehicle;

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

    public void saveUser(User user) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user", gson.toJson(user));
        editor.apply();
    }

    public User getSaveUser() {
        Type type = new TypeToken<User>() {
        }.getType();
        return gson.fromJson(sharedPreferences.getString("user", ""), type);
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
        Log.e("DAO", "userExist: " + users);
        Log.e("DAO", "mechanicsExist: " + mechanics);
        if (users != null) {
            for (User user : users
            ) {
                if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                    return user;
                }
            }
        }
        if (mechanics != null) {
            for (Mechanic mechanic : mechanics
            ) {
                if (mechanic.getEmail().equals(email) && mechanic.getPassword().equals(password)) {
                    return mechanic;
                }
            }
        }

        return null;
    }

    // TODO: Implement DAO methods for Vehicles
    public void createVehicle(Vehicle vehicle) {
        ArrayList<Vehicle> vehicles = getVehicles();
        if (vehicles == null) {
            vehicles = new ArrayList<>();
        }
        vehicles.add(vehicle);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("vehicles", gson.toJson(vehicles));
        editor.apply();
    }

    public ArrayList<Vehicle> getVehicles() {
        Type type = new TypeToken<ArrayList<Vehicle>>() {
        }.getType();
        return gson.fromJson(sharedPreferences.getString("vehicles", ""), type);
    }

}
