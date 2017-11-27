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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.activity_perfis, container, false);

        recuperarPerfis();

        recyclerView = myView.findViewById(R.id.recycler_perfis);

        if(!perfis.isEmpty()){
            layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);

            adapter = new PerfilAdapter(getActivity(),perfis);
            recyclerView.setAdapter(adapter);

        }else{
            menssagem("Lista de perfis vazia");
        }

        //preciso preencher o arrayList de perfis, provavelmente deserializando-os
        //e depois inserindo na lista

        return myView;
    }//onCreateView

    public void recuperarPerfis(){
        File root = getActivity().getFilesDir();
        File diretorio = new File(root.toString());
        File[] arquivos = diretorio.listFiles();

        if(arquivos!=null){
            try{
                int length = arquivos.length;

                for(int i = 0; i < arquivos.length; i++){
                    File f = arquivos[i];

                    if(f.isFile()){
                        FileInputStream fi = new FileInputStream(f);
                        ObjectInputStream oi = new ObjectInputStream(fi);
                        Perfil p = (Perfil) oi.readObject();
                        perfis.add(p);
                    }//if
                }//for i

            }catch (Exception e){
                menssagem(e.getMessage());
            }//catch
        }//if

    }//recuperarPerfis


    public void menssagem(String sr){
        Toast.makeText(getActivity(),sr, Toast.LENGTH_SHORT).show();
    }//menssagem

}//class
