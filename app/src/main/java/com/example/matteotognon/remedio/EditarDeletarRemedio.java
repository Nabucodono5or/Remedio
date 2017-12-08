package com.example.matteotognon.remedio;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class EditarDeletarRemedio extends Fragment implements View.OnClickListener{
    View myView;
    int qx, ix;
    Remedio remedio;
    String idPerfil;
    Perfil perfil;
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
        mDatabase = FirebaseDatabase.getInstance().getReference(user.getUid()).child("perfis");

        receita = myView.findViewById(R.id.editTextOldReceita);
        inter = myView.findViewById(R.id.editTextOldIntervalo);
        quant = myView.findViewById(R.id.editTextOldQuant);

        receita.setText(remedio.getNome());
        inter.setText(String.valueOf(remedio.getIntervalo()));
        quant.setText(String.valueOf(remedio.getQuntidade()));

        btnDeleteRemedio = myView.findViewById(R.id.btnDeleteRemedio);
        btnUpdateRemedio = myView.findViewById(R.id.btnUpdateRemedio);

        toggleButton = myView.findViewById(R.id.toggleButtonAlarm);


        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Perfil p = dataSnapshot.getValue(Perfil.class);
                if(p != null) {
                    if(p.getNome().equals(perfil.getNome())){
                        idPerfil = dataSnapshot.getKey();
                    }
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnUpdateRemedio.setOnClickListener(this);
        btnDeleteRemedio.setOnClickListener(this);

        return myView;
    }


    public void updateRemedio(){
        //usarei o updateChild aqui
        converterIntervaloQuantidade(quant.getText().toString(),inter.getText().toString());
        Remedio re = new Remedio(receita.getText().toString(),qx,ix);

        Map<String,Object> result = new HashMap<String, Object>();
        result.put(re.getNome(),re);
        mDatabase.child(idPerfil).child("remedios").updateChildren(result);
    }

    public void converterIntervaloQuantidade(String q, String i){
        qx = Integer.parseInt(q);
        ix = Integer.parseInt(i);
    }

    public void deleteRemedio(){
        mDatabase.child(idPerfil).child("remedios").child(remedio.getNome()).removeValue();
        Log.e(TAG, "deletado remedio");
    }


    public void setAlarm(){
        //para a nortificação
    }


    public void setPerfil(Perfil perfil){
        this.perfil = perfil;
    }

    public void setRemedio(Remedio remedio){
        this.remedio = remedio;
    }


    @Override
    public void onClick(View view) {
        if(R.id.btnUpdateRemedio == view.getId()){
            updateRemedio();
        }else if(R.id.btnDeleteRemedio == view.getId()){
            deleteRemedio();
        }
    }
}
