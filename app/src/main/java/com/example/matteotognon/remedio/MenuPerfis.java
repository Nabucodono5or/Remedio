package com.example.matteotognon.remedio;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;


public class MenuPerfis extends Fragment {
    View myView;
    private RecyclerView recyclerView;
    private ArrayList<Perfil> perfis = new ArrayList<>();
    private PerfilAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private DatabaseReference mDatabase;
    FirebaseDatabase firebaseDatabase;
    FirebaseUser user;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.activity_perfis, container, false);

        //recuperarPerfis();

        user = FirebaseAuth.getInstance().getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase = firebaseDatabase.getReference().child(user.getUid()).child("perfis");

        recyclerView = myView.findViewById(R.id.recycler_perfis);

        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new PerfilAdapter(getActivity(),perfis);
        recyclerView.setAdapter(adapter);

        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Perfil perfil = dataSnapshot.getValue(Perfil.class);
                if(perfil == null){
                    menssagem("nenhum registro foi encontrado");
                }else {
                    perfis.add(perfil);
                    adapter.notifyDataSetChanged();
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


        return myView;
    }//onCreateView




    public void recuperarPerfis(){
    }//recuperarPerfis


    public void menssagem(String sr){
        Toast.makeText(getActivity(),sr, Toast.LENGTH_SHORT).show();
    }//menssagem

}//class
