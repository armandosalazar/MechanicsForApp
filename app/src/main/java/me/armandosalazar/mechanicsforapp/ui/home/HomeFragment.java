package me.armandosalazar.mechanicsforapp.ui.home;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import me.armandosalazar.mechanicsforapp.dao.DAO;
import me.armandosalazar.mechanicsforapp.databinding.FragmentHomeBinding;
import me.armandosalazar.mechanicsforapp.models.Mechanic;
import me.armandosalazar.mechanicsforapp.recyclerview.CustomAdapter;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    // recycler view
    private RecyclerView recyclerView;
    private CustomAdapter customAdapter;
    private ArrayList<Mechanic> mechanics;

    // shared preferences
    private SharedPreferences sharedPreferences;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // final TextView textView = binding.textHome;
        // homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        sharedPreferences = getActivity().getSharedPreferences("mechanics.dat", 0);
        mechanics = DAO.getInstance(sharedPreferences).getMechanics();

        if (mechanics == null) {
            mechanics = new ArrayList<>();
        }

        for (Mechanic mechanic : mechanics) {
            System.out.println(mechanic.getName());
        }

        // recyclerView = binding.recyclerMechanics;
        recyclerView = root.findViewById(binding.recyclerMechanics.getId());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        customAdapter = new CustomAdapter(mechanics);
        recyclerView.setAdapter(customAdapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}