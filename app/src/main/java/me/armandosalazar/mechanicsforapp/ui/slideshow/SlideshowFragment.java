package me.armandosalazar.mechanicsforapp.ui.slideshow;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import me.armandosalazar.mechanicsforapp.dao.DAO;
import me.armandosalazar.mechanicsforapp.databinding.FragmentSlideshowBinding;
import me.armandosalazar.mechanicsforapp.models.Vehicle;
import me.armandosalazar.mechanicsforapp.recyclerview.VehicleAdapter;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;
    private TextInputLayout textInputBrand, textInputModel, textInputYear, textInputPlates, textInputColor;
    // share preferences
    private SharedPreferences sharedPreferences;

    public SlideshowFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // SlideshowViewModel slideshowViewModel = new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // final TextView textView = binding.textSlideshow;
        // slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        // init share preferences
        sharedPreferences = requireActivity().getSharedPreferences("mechanics.dat", 0);


        // set items
        textInputBrand = binding.textInputBrand;
        textInputModel = binding.textInputModel;
        textInputYear = binding.textInputYear;
        textInputPlates = binding.textInputPlates;
        textInputColor = binding.textInputColor;

        RecyclerView recyclerView = binding.recyclerVehicles;
        recyclerView.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new VehicleAdapter(DAO.getInstance(sharedPreferences).getVehicles()));

        Button buttonAccept = binding.buttonAccept;

        buttonAccept.setOnClickListener(v -> {
            if (!emptyTextInputs()) {
                String brand = Objects.requireNonNull(textInputBrand.getEditText()).getText().toString();
                String model = Objects.requireNonNull(textInputModel.getEditText()).getText().toString();
                String year = Objects.requireNonNull(textInputYear.getEditText()).getText().toString();
                String plates = Objects.requireNonNull(textInputPlates.getEditText()).getText().toString();
                String color = Objects.requireNonNull(textInputColor.getEditText()).getText().toString();

                Vehicle vehicle = new Vehicle(brand, model, year, plates, color);
                DAO.getInstance(sharedPreferences).createVehicle(vehicle);

                // show dialog success
                AlertDialog.Builder cuadroAlert = new AlertDialog.Builder(getContext());
                cuadroAlert.setTitle("Vehículo agregado");

                cuadroAlert.setMessage("Se ha agregado el vehículo").setPositiveButton("OK", (dialogInterface, i) -> {
                }).show();

                // clear inputs
                textInputBrand.getEditText().setText("");
                textInputModel.getEditText().setText("");
                textInputYear.getEditText().setText("");
                textInputPlates.getEditText().setText("");
                textInputColor.getEditText().setText("");

                // update recycler view
                recyclerView.setAdapter(new VehicleAdapter(DAO.getInstance(sharedPreferences).getVehicles()));


            } else {
                // show dialog empty inputs
                AlertDialog.Builder cuadroAlert = new AlertDialog.Builder(getContext());
                cuadroAlert.setTitle("Verifique los campos");

                cuadroAlert.setMessage("Rellene todos los compos").setPositiveButton("OK", (dialogInterface, i) -> {
                }).show();
            }
        });


        return root;
    }

    private boolean emptyTextInputs() {
        return Objects.requireNonNull(textInputBrand.getEditText()).getText().toString().isEmpty() || Objects.requireNonNull(textInputModel.getEditText()).getText().toString().isEmpty() || Objects.requireNonNull(textInputYear.getEditText()).getText().toString().isEmpty() || Objects.requireNonNull(textInputPlates.getEditText()).getText().toString().isEmpty() || Objects.requireNonNull(textInputColor.getEditText()).getText().toString().isEmpty();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}