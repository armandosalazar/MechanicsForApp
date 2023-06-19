package me.armandosalazar.mechanicsforapp.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import me.armandosalazar.mechanicsforapp.R;
import me.armandosalazar.mechanicsforapp.models.Vehicle;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.ViewHolder> {
    private final ArrayList<Vehicle> vehicles;

    public VehicleAdapter(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView vehicleName;
        private final TextView vehicleYear;
        private final TextView vehiclePlates;

        public ViewHolder(@NonNull ViewGroup parent) {
            super(parent);
            vehicleName = parent.findViewById(R.id.text_vehicle_name);
            vehicleYear = parent.findViewById(R.id.text_vehicle_year);
            vehiclePlates = parent.findViewById(R.id.text_vehicle_plate);
        }
    }

    @NonNull
    @Override
    public VehicleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // View view = View.inflate(parent.getContext(), R.layout.vehicle_item, null);
        // esto generaba un error con el width
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vehicle_item, parent, false);

        return new ViewHolder((ViewGroup) view);
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleAdapter.ViewHolder holder, int position) {
        Vehicle vehicle = vehicles.get(position);
        String name = vehicle.getBrand() + " " + vehicle.getModel();
        holder.vehicleName.setText(name);
        holder.vehicleYear.setText(vehicle.getYear());
        holder.vehiclePlates.setText(vehicle.getLicensePlates());

    }

    @Override
    public int getItemCount() {
        return vehicles.size();
    }
}
