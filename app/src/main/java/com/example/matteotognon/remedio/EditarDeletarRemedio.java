package com.example.matteotognon.remedio;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class EditarDeletarRemedio extends Fragment implements View.OnClickListener{
    View myView;
    Remedio remedio;
    String idPerfil;
    FirebaseUser user;
    Button btnUpdateRemedio, btnDeleteRemedio;
    EditText receita, inter, quant;
    private DatabaseReference mDatabase;
    ArrayList<EditText> listaEntrada;
    ToggleButton toggleButton;
    private static final String TAG = "MyFirstFireBase";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_editar_deletar_remedio, container, false);

        user = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference(user.getUid()).child("perfis").
                child(idPerfil).child("remedios");

        receita = myView.findViewById(R.id.editTextOldReceita);
        inter = myView.findViewById(R.id.editTextOldIntervalo);
        quant = myView.findViewById(R.id.editTextOldQuant);

        btnDeleteRemedio = myView.findViewById(R.id.btnDeleteRemedio);
        btnUpdateRemedio = myView.findViewById(R.id.btnUpdateRemedio);

        toggleButton = myView.findViewById(R.id.toggleButtonAlarm);


        btnUpdateRemedio.setOnClickListener(this);
        btnDeleteRemedio.setOnClickListener(this);

        return myView;
    }


    public void updateRemedio(){
        //usarei o updateChild aqui
    }


    public void deleteRemedio(){
        //usarei o updateChild aqui
    }


    public void setAlarm(){
        //para a nortificação
    }


    public void setIdPerfil(String idPerfil){
        this.idPerfil = idPerfil;
        //o adapter receberá do Menu remedios o id do perfil
    }


    public void setRemedio(Remedio remedio){
        this.remedio = remedio;
    }


    @Override
    public void onClick(View view) {

    }
}
