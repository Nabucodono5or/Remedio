package com.example.matteotognon.remedio;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class MenuPerfis extends Fragment {
    View myView;
    private RecyclerView recyclerView;
    private ArrayList<Perfil> perfis;
    private PerfilAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.activity_perfis, container, false);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new PerfilAdapter(getActivity(),perfis);
        //preciso preencher o arrayList de perfis, provavelmente deserializando-os
        //e deposi inserindo na lista


        recyclerView.setAdapter(adapter);
        return myView;
    }
}
