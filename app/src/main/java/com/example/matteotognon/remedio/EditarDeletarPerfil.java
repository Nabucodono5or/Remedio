package com.example.matteotognon.remedio;

import android.app.Fragment;
import android.app.FragmentManager;
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
import java.util.HashMap;
import java.util.Map;


public class EditarDeletarPerfil extends Fragment implements View.OnClickListener{

    View myView;
    Perfil perfil;
    String perfilId;
    FirebaseUser user;
    EditText editNome, editDesc;
    Button btnUpdate, btnDelete;
    ArrayList<EditText> listaEntrada;
    private DatabaseReference mDatabase;
    private static final String TAG = "MyFirstFireBase";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_editar_deletar_perfil, container, false);

        if (container != null) {
            container.removeAllViews();
        }

        user = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference(user.getUid()).child("perfis");

        listaEntrada = new ArrayList<>();

        editDesc = myView.findViewById(R.id.editTextOldDesc);
        editNome = myView.findViewById(R.id.editTextOldNome);

        editNome.setText(perfil.getNome());
        editDesc.setText(perfil.getDescricao());

        listaEntrada.add(editNome);
        listaEntrada.add(editDesc);

        btnDelete = myView.findViewById(R.id.btnDelete);
        btnUpdate = myView.findViewById(R.id.btnUpdate);

        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Perfil p = dataSnapshot.getValue(Perfil.class);
                if(p != null) {
                    if(p.getNome().equals(perfil.getNome())){
                        perfilId = dataSnapshot.getKey();
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

        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);

        return myView;
    }


    public void updatePerfil(){
        //usar updateChild

        if(!isEmpty()){
            Perfil p = new Perfil(editNome.getText().toString(), editDesc.getText().toString());
            Map<String,Object> result = new HashMap<String, Object>();

            result.put("descricao", p.getDescricao());
            result.put("nome", p.getNome());

            mDatabase.child(perfilId).updateChildren(result);
            Log.e(TAG,"dados atualizados");
        }
        addUserChangeListener();
    }


    public void deletePerfil(){
        if(!perfil.isPrincipal()){
            mDatabase.child(perfilId).removeValue();
        }
        Log.e(TAG,"deletando perfil");
    }

    private boolean isEmpty(){
        for (EditText e: listaEntrada) {
            if(e.getText().toString().equals("")){
                return true;
            }//if e
        }//foreach
        return false;
    }//isEmpty


    private void addUserChangeListener() {
        mDatabase.child(perfilId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Perfil p = dataSnapshot.getValue(Perfil.class);

                if (p == null) {
                    Log.e(TAG, "User data is null!");
                    return;
                }

                Log.e(TAG, "User data is changed!" + p.getNome() + ", " + p.getDescricao());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "Failed to read user", databaseError.toException());
            }
        });
    }//addUserChangeListener


    public void setPerfil(Perfil perfil){
        this.perfil = perfil;
    }



    @Override
    public void onClick(View view) {
        FragmentManager fragmentManager = getFragmentManager();

        if(R.id.btnUpdate == view.getId()){
            updatePerfil();
            fragmentManager.beginTransaction().replace(R.id.content_frame, new MenuTelainicial()).commit();
        }else if(R.id.btnDelete == view.getId()){
            deletePerfil();
            fragmentManager.beginTransaction().replace(R.id.content_frame, new MenuTelainicial()).commit();
        }
    }
}
