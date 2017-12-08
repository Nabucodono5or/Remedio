package com.example.matteotognon.remedio;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
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

public class MenuEditarPerfil extends Fragment implements View.OnClickListener{
    View myView;
    Button btnSalvar;
    EditText editTextNome;
    EditText editTextDescricao;
    String userId;
    private DatabaseReference mDatabase;
    FirebaseDatabase firebaseDatabase;
    FirebaseUser user;
    private static final String TAG = "MyFirstFireBase";

    ArrayList<EditText> listaEntrada;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.activity_editar_perfil, container, false);

        btnSalvar = myView.findViewById(R.id.btnSalvar);
        editTextDescricao = myView.findViewById(R.id.editTextDescricao);
        editTextNome = myView.findViewById(R.id.editTextNome);

        user = FirebaseAuth.getInstance().getCurrentUser();

        firebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase = firebaseDatabase.getReference().child(user.getUid());

        listaEntrada = new ArrayList<>();
        listaEntrada.add(editTextDescricao);
        listaEntrada.add(editTextNome);

        btnSalvar.setOnClickListener(this);
        return myView;
    }//onCreateView


    private boolean isEmpty(){
        for (EditText e: listaEntrada) {
            if(e.getText().toString().equals("")){
                return true;
            }//if e
        }//foreach
        return false;
    }//isEmpty


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnSalvar){
            salvarPerfil();
        }
    }//onClick


    private void salvarPerfil(){
            if(isEmpty()){
               Toast.makeText(getActivity(),"Preencha os campos",Toast.LENGTH_SHORT).show();
            } else {
                createUser();
            }
    }//salvarPerfil


    private void createUser(){
        if (TextUtils.isEmpty(userId)) {
            userId = mDatabase.push().getKey();
        }

        Perfil perfil = new Perfil(editTextNome.getText().toString(), editTextDescricao.getText().toString());


        mDatabase.child("perfis").child(userId).setValue(perfil);
        addUserChangeListener();
    }//createUser



    private void addUserChangeListener() {
        mDatabase.child("perfis").child(userId).addValueEventListener(new ValueEventListener() {
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


    private void limparCampos(){
        editTextNome.setText("");
        editTextDescricao.setText("");
    }//limparCampos

}//class
