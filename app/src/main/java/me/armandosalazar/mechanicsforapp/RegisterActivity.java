package me.armandosalazar.mechanicsforapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RegisterActivity extends AppCompatActivity {

    private TextInputLayout   txtLayoutName, txtLayoutLastName, txtLayoutPass, txtLayoutRepeatPass, txtLayoutEmail, txtLayoutRfc;
    private TextInputEditText txtName, txtLastName, txtPass, txtRepeatPass, txtEmail, txtRfc;
    private CheckBox cbMechanic;
    private Spinner spMechanicType;
    private String previousUsers;
    private String [] typeOfMechanic = {"Seleccione un tipo","Eléctrico","General", "Hojalatería y pintura",
            "Mecánico Diesel","Frenos y transmisión"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        txtLayoutEmail = findViewById(R.id.txtInputNewEmail);
        txtLayoutLastName = findViewById(R.id.txtInputLastName);
        txtLayoutName = findViewById(R.id.txtInputName);
        txtLayoutPass = findViewById(R.id.txtInputNewPass);
        txtLayoutRepeatPass = findViewById(R.id.txtInputRepeatPass);
        txtLayoutRfc = findViewById(R.id.txtInputRfc);

        txtName = findViewById(R.id.txtName);
        txtLastName = findViewById(R.id.txtLastName);
        txtEmail = findViewById(R.id.txtNewEmail);
        txtPass = findViewById(R.id.txtNewPass);
        txtRepeatPass = findViewById(R.id.txtRepeatPass);
        txtRfc = findViewById(R.id.txtRfc);

        spMechanicType = findViewById(R.id.spTypeOfMechanic);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,typeOfMechanic);

        spMechanicType.setAdapter(adapter);
        cbMechanic = findViewById(R.id.cbMechanic);
        cbMechanic.setOnClickListener(view -> {
            if (isAMechanic()){
                txtRfc.setVisibility(View.VISIBLE);
                txtLayoutRfc.setVisibility(View.VISIBLE);
                spMechanicType.setVisibility(View.VISIBLE);
            }else{
                txtRfc.setVisibility(View.GONE);
                txtLayoutRfc.setVisibility(View.GONE);
                spMechanicType.setVisibility(View.GONE);
            }
        });

        abrirArchivo();
    }

    private boolean isAMechanic(){
        return cbMechanic.isChecked();
    }

    public void registerUser(View view){
        if (allFieldsFilled()){
           if (txtPass.getText().toString().equals(txtRepeatPass.getText().toString())){
               registerUserOnFile(previousUsers);
               Toast.makeText(this, "Registro de usuario exitoso", Toast.LENGTH_SHORT).show();
               finish();
           }else{
               Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
           }
        }else {
            Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }
    
    private boolean allFieldsFilled(){
        if (isAMechanic()){
            return !txtName.getText().toString().equals("") && !txtLastName.getText().toString().equals("") &&
                    !txtEmail.getText().toString().equals("") && !txtPass.getText().toString().equals("") &&
                    !txtRepeatPass.getText().toString().equals("") && !txtRfc.getText().toString().equals("")
                    && spMechanicType.getSelectedItemPosition() != 0;
        }else{
            return !txtName.getText().toString().equals("") && !txtLastName.getText().toString().equals("") &&
                    !txtEmail.getText().toString().equals("") && !txtPass.getText().toString().equals("") && 
                    !txtRepeatPass.getText().toString().equals("");
        }
      
    }

    private String registerUserOnFile(String currentContentOfTheFile){
        int indexSpinner = spMechanicType.getSelectedItemPosition();
        StringBuilder stringBuilder = new StringBuilder();
        if (currentContentOfTheFile != null){
            stringBuilder.append(currentContentOfTheFile);
            stringBuilder.append("\n");
            stringBuilder.append("Nombre: ");
            stringBuilder.append( txtName.getText().toString());
            stringBuilder.append("Apellido(s): ");
            stringBuilder.append(txtLastName.getText().toString());
            stringBuilder.append("Correo: ");
            stringBuilder.append(txtEmail.getText().toString());
            stringBuilder.append("Password: ");
            stringBuilder.append(txtPass.getText().toString());
            if (isAMechanic()){
                stringBuilder.append("RFC: ");
                stringBuilder.append(txtRfc.getText().toString());
                stringBuilder.append("Tipo de mecanico: ");
                stringBuilder.append(spMechanicType.getItemAtPosition(indexSpinner));
            }

        }else{
            stringBuilder.append("\n");
            stringBuilder.append("Nombre: ");
            stringBuilder.append( txtName.getText().toString());
            stringBuilder.append("Apellido(s): ");
            stringBuilder.append(txtLastName.getText().toString());
            stringBuilder.append("Correo: ");
            stringBuilder.append(txtEmail.getText().toString());
            stringBuilder.append("Password: ");
            stringBuilder.append(txtPass.getText().toString());
            if (isAMechanic()){
                stringBuilder.append("RFC: ");
                stringBuilder.append(txtRfc.getText().toString());
                stringBuilder.append("Tipo de mecanico: ");
                stringBuilder.append(spMechanicType.getItemAtPosition(indexSpinner));
            }
        }
        return stringBuilder.toString();
    }

    private void abrirArchivo(){
        String []archivos = fileList();

        if (existeArchivo(archivos, "users.txt")){
            try {
                //Objeto que asocia al archivo especificado, para lectura
                InputStreamReader archivoInterno = new
                        InputStreamReader(openFileInput("users.txt"));
                //Objeto que relaciona el arhicov con el flujo de entrada (lectura)
                BufferedReader leerArchivo = new BufferedReader(archivoInterno);
                String linea = leerArchivo.readLine();

                String textoLeido = "";

                while(linea != null){
                    textoLeido += linea + '\n';
                    linea = leerArchivo.readLine();
                }

                leerArchivo.close();
                archivoInterno.close();
                previousUsers = textoLeido;
            }catch (IOException e){
                Toast.makeText(this,"Error al leer el archivo.",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean existeArchivo(String []archivos, String s){
        for (String archivo : archivos) {
            if (s.equals(archivo)) {
                return true;
            }
        }
        return false;
    }


}