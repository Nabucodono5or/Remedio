package com.example.matteotognon.remedio;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by MatteoTognon on 07/11/2017.
 */

public class MenuRemedios extends Fragment{
    View myView;
    private RecyclerView recyclerView;
    private Perfil perfil;
    private RecyclerView.LayoutManager layoutManager;
    private RemedioAdapter adapter;
    private List<Remedio> remds;
    private Context context;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.activity_remedios, container, false);

        recyclerView = myView.findViewById(R.id.recycler_remedios);


        if(perfil.getRemedios() != null){
           remds = new ArrayList<Remedio>(perfil.getRemedios().values());
           layoutManager = new LinearLayoutManager(getActivity());
           recyclerView.setLayoutManager(layoutManager);

           adapter = new RemedioAdapter(context, remds, perfil);
           recyclerView.setAdapter(adapter);
        }else {
            Toast.makeText(getActivity(), "valor nulo para lista de remedios", Toast.LENGTH_SHORT).show();
        }

        return myView;
    }//onCreateView

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public void recuperarRemedios(Context context){
        this.context = context;
    }//recuperarRemedios
}

