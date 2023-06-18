package me.armandosalazar.mechanicsforapp.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import me.armandosalazar.mechanicsforapp.R;
import me.armandosalazar.mechanicsforapp.models.Mechanic;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private final ArrayList<Mechanic> mechanics;

    public CustomAdapter(ArrayList<Mechanic> mechanics) {
        this.mechanics = mechanics;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView mechanicName;
        private final TextView mechanicType;

        public ViewHolder(@NonNull ViewGroup parent) {
            super(parent);
            mechanicName = parent.findViewById(R.id.mechanic_name);
            mechanicType = parent.findViewById(R.id.mechanic_type);
            parent.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    System.out.println("Position: " + position);
                    System.out.println("Mechanic: " + mechanics.get(position).getName());
                }
            });

        }
    }

    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mechanic_item, parent, false);
        return new ViewHolder((ViewGroup) view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {
        Mechanic mechanic = mechanics.get(position);
        holder.mechanicName.setText(mechanic.getName());
        holder.mechanicType.setText(mechanic.getTypeOfMechanic());
    }

    @Override
    public int getItemCount() {
        return mechanics.size();
    }

}
