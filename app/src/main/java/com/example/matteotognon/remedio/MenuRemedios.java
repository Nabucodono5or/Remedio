package com.example.matteotognon.remedio;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by MatteoTognon on 07/11/2017.
 */

public class MenuRemedios extends Fragment{
    View myView;
    private RecyclerView recyclerView;
    private Perfil perfil;
    private RecyclerView.LayoutManager layoutManager;
    private RemedioAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.activity_remedios, container, false);

        recyclerView = myView.findViewById(R.id.recycler_remedios);

        if(perfil != null){
           layoutManager = new LinearLayoutManager(getActivity());
           recyclerView.setLayoutManager(layoutManager);

           adapter = new RemedioAdapter(getActivity(), perfil.remedios);
           recyclerView.setAdapter(adapter);
        }

        return myView;
    }//onCreateView

    public void recuperarRemedios(){

    }//recuperarRemedios

}

