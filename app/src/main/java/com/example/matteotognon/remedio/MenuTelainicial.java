package com.example.matteotognon.remedio;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by MatteoTognon on 07/11/2017.
 */

public class MenuTelainicial extends Fragment implements View.OnClickListener {

    View myView;
    Button cadastrar;
    Button listarPerfis;
    Button listarRemedios;
    Perfil perfil;
    private DatabaseReference ref;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.activity_tela_inicial, container, false);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        ref = FirebaseDatabase.getInstance().getReference().child(user.getUid()).child("perfis");

        cadastrar =  myView.findViewById(R.id.button);
        listarPerfis =  myView.findViewById(R.id.button2);
        listarRemedios =  myView.findViewById(R.id.button3);

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Perfil p = dataSnapshot.getValue(Perfil.class);
                if(perfil != null){
                    if(p.isPrincipal()){
                        perfil = p;
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

        cadastrar.setOnClickListener(this);
        listarPerfis.setOnClickListener(this);
        listarRemedios.setOnClickListener(this);

        return myView;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    @Override
    public void onClick(View view) {
        FragmentManager fragmentManager = getFragmentManager();
        switch (view.getId()){
            case R.id.button:
                fragmentManager.beginTransaction().replace(R.id.content_frame, new MenuEditarPerfil()).addToBackStack("editarPerfil").commit();
                break;
            case R.id.button2:
                fragmentManager.beginTransaction().replace(R.id.content_frame, new MenuPerfis()).addToBackStack("Perfis").commit();
                break;
            case  R.id.button3:
                if(!(perfil == null)){
                    MenuRemedios menuRemedios = new MenuRemedios();
                    menuRemedios.setPerfil(perfil);
                    menuRemedios.recuperarRemedios(getActivity());

                    fragmentManager.beginTransaction().replace(R.id.content_frame, menuRemedios).addToBackStack("Remedios").commit();
                } else{
                    Toast.makeText(getActivity(), "Cadastre perfil principal", Toast.LENGTH_SHORT).show();
                }
                //por enqunto deixarei Menu remédios a única responsável por essa terefa - listar Remédios
                break;
        }//switch
    }//onClick
}
