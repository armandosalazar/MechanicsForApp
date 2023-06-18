package me.armandosalazar.mechanicsforapp.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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
//                    System.out.println("Position: " + position);
//                    System.out.println("Mechanic: " + mechanics.get(position).getName());
                    Mechanic mechanic = mechanics.get(position);
                    showCustomDialog(parent.getContext(),
                            LayoutInflater.from(parent.getContext()), mechanic);
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

    public void showCustomDialog(Context context, LayoutInflater layoutInflater, Mechanic mechanic) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Datos del mecanico");
        View view = layoutInflater.inflate(R.layout.custom_dialog, null);
        // view.findViewById(R.id.imgCustomDialog);
        ((TextView) view.findViewById(R.id.tvMechanicName)).setText(mechanic.getName() + " " + mechanic.getLastName());
        ((TextView) view.findViewById(R.id.tvTypeOfMechanic)).setText(mechanic.getTypeOfMechanic());
        ((Button) view.findViewById(R.id.btnAgendar)).setText("Agendar");
        ((Button) view.findViewById(R.id.btnCancelar)).setText("Cancelar");

        builder.setView(view);
        AlertDialog alertDialog = builder.create();

        view.findViewById(R.id.btnAgendar).setOnClickListener(v -> {
            //textInputLayoutResponse.getEditText().setText("Se aceptó");
            Toast.makeText(context, "Se aceptó", Toast.LENGTH_SHORT).show();
            alertDialog.dismiss();
        });
        view.findViewById(R.id.btnCancelar).setOnClickListener(v -> {
            //textInputLayoutResponse.getEditText().setText("Se declinó");
            Toast.makeText(context, "Se declinó", Toast.LENGTH_SHORT).show();
            alertDialog.dismiss();
        });

        alertDialog.show();
    }

}
