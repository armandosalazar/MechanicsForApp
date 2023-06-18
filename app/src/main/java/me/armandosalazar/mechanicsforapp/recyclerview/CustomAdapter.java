package me.armandosalazar.mechanicsforapp.recyclerview;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;

import me.armandosalazar.mechanicsforapp.R;
import me.armandosalazar.mechanicsforapp.models.Mechanic;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private final ArrayList<Mechanic> mechanics;
    private Calendar calendar;

    public CustomAdapter(ArrayList<Mechanic> mechanics) {
        this.mechanics = mechanics;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView mechanicName;
        private final TextView mechanicType;


        public ViewHolder(@NonNull ViewGroup parent) {
            super(parent);
            calendar = Calendar.getInstance();
            mechanicName = parent.findViewById(R.id.mechanic_name);
            mechanicType = parent.findViewById(R.id.mechanic_type);
            parent.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Mechanic mechanic = mechanics.get(position);
                    showCustomDialog(parent.getContext(), LayoutInflater.from(parent.getContext()), mechanic);
                }
            });

        }
    }

    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mechanic_item, parent, false);
        return new ViewHolder((ViewGroup) view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {
        Mechanic mechanic = mechanics.get(position);
        String name = mechanic.getName() + " " + mechanic.getLastName();
        holder.mechanicName.setText(name);
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
        String name = mechanic.getName() + " " + mechanic.getLastName();
        ((TextView) view.findViewById(R.id.mechanic_name_dialog)).setText(name);
        ((TextView) view.findViewById(R.id.mechanic_type_dialog)).setText(mechanic.getTypeOfMechanic());
//        ((Button) view.findViewById(R.id.btnAcceptDialog)).setText("Agendar");
//        ((Button) view.findViewById(R.id.btnCancelDialog)).setText("Cancelar");

        builder.setView(view);
        AlertDialog alertDialog = builder.create();

        view.findViewById(R.id.btnAcceptDialog).setOnClickListener(v -> {
            showDateDialog(view);
            //textInputLayoutResponse.getEditText().setText("Se aceptó");
            // Toast.makeText(context, "Se aceptó", Toast.LENGTH_SHORT).show();
            alertDialog.dismiss();
        });
        view.findViewById(R.id.btnCancelDialog).setOnClickListener(v -> {
            //textInputLayoutResponse.getEditText().setText("Se declinó");
            Toast.makeText(context, "Se declinó", Toast.LENGTH_SHORT).show();
            alertDialog.dismiss();
        });

        alertDialog.show();
    }

    public void showDateDialog(View view) {
        int day, month, year;
        calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(), (datePicker, i, i1, i2) -> {
//                    TextView txtResponse = findViewById(R.id.txtResponse);
//                    txtResponse.setText(i + "/" + (i1 + 1) + "/" + i2);
        }, year, month, day);

        datePickerDialog.show();
    }

}
