package com.example.matteotognon.remedio;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by MatteoTognon on 07/11/2017.
 */

public class MenuEditarRemedio extends Fragment implements View.OnClickListener {
    FirebaseUser user;
    private DatabaseReference mDatabase;
    Perfil perfil;
    View myView;
    EditText nomeRemedio, quant, intervalo;
    int qx, ix;
    Remedio remedio;
    ArrayList<EditText> listaEntrada;
    Button btnSalvarRemedio;
    String idPerfil;
    private static final String TAG = "MyFirstFireBase";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.activity_editar_remedio, container, false);

        if (container != null) {
            container.removeAllViews();
        }

        user = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference(user.getUid()).child("perfis");

        nomeRemedio = myView.findViewById(R.id.editTextNomeRemedio);
        quant = myView.findViewById(R.id.editTextQuantidade);
        intervalo = myView.findViewById(R.id.editTextIntervalo);
        btnSalvarRemedio = myView.findViewById(R.id.buttonSalvar);

        listaEntrada = new ArrayList<>();

        listaEntrada.add(nomeRemedio);
        listaEntrada.add(quant);
        listaEntrada.add(intervalo);

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

        btnSalvarRemedio.setOnClickListener(this);

        return myView;
    }


    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }


    public void onClickSalvarRemedio(){

        if(!isEmpty()){
            converterIntervaloQuantidade(quant.getText().toString(), intervalo.getText().toString());
            salvarPerfil(nomeRemedio.getText().toString(), qx, ix);
        }

    }//onClickSalvarRemedio


    public void salvarPerfil(String nome, int  q, int i){
        remedio = new Remedio(nome,q,i);
        perfil.addRemedio(remedio);
        // se não funcionar usar a opção de baixo
        mDatabase.child(idPerfil).setValue(perfil);
        addUserChangeListener();

    }//salvarPerfil


    private void addUserChangeListener() {
        mDatabase.child(idPerfil).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Perfil p = dataSnapshot.getValue(Perfil.class);

                if (p == null) {
                    Log.e(TAG, "User data is null!");
                    return;
                }

                Log.e(TAG, "User data is changed!" + p.getNome() + ", " + p.getDescricao());
                limparCampos();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "Failed to read user", databaseError.toException());
            }
        });
    }//addUserChangeListener


    public void converterIntervaloQuantidade(String q, String i){
        qx = Integer.parseInt(q);
        ix = Integer.parseInt(i);
    }


    private boolean isEmpty(){
        for (EditText e: listaEntrada) {
            if(e.getText().toString().equals("")){
                return true;
            }//if e
        }//foreach
        return false;
    }//isEmpty


    private void limparCampos(){
        for (EditText e: listaEntrada) {
            e.setText("");
        }
    }

    @Override
    public void onClick(View view) {
        onClickSalvarRemedio();
    }
}
