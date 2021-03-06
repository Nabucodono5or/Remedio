package com.example.matteotognon.remedio;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class GerenciamentoPerfil extends Fragment implements View.OnClickListener{

    View myView;
    Perfil perfil;
    TextView nome, descricao;
    MenuRemedios menuRemedios;
    EditarDeletarPerfil editarDeletarPerfil;
    MenuEditarRemedio menuEditarRemedio;
    Button btnAddRemedio, btnEditarPerfil;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_gerenciamento_perfil, container, false);

        nome = myView.findViewById(R.id.textViewNomePerfil);
        descricao = myView.findViewById(R.id.textViewDescricaoPerfil);

        btnAddRemedio = myView.findViewById(R.id.btnAdcionarRemedio);
        btnEditarPerfil = myView.findViewById(R.id.btnEditarPerfil);

        setTelaUsuario();

        menuRemedios = new MenuRemedios();
        menuRemedios.setPerfil(perfil);
        menuRemedios.recuperarRemedios(getActivity());


        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_remedios, menuRemedios).commit();

        btnAddRemedio.setOnClickListener(this);
        btnEditarPerfil.setOnClickListener(this);
        return myView;
    }//onCreateView


    public void setTelaUsuario(){
        nome.setText(perfil.getNome());
        descricao.setText(perfil.getDescricao());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }//onAttach

    @Override
    public void onDetach() {
        super.onDetach();
        //tentar resolver o prblema com o fragment se sobrepor ao outro
    }//onDetach

    public void setPerfil(Perfil perfil){
        this.perfil = perfil;
    }

    @Override
    public void onClick(View view) {
        FragmentManager fragmentManager = getFragmentManager();

        switch (view.getId()){
            case R.id.btnAdcionarRemedio:
                menuEditarRemedio = new MenuEditarRemedio();
                menuEditarRemedio.setPerfil(perfil);

                fragmentManager.beginTransaction().replace(R.id.content_frame, menuEditarRemedio).commit();

                break;
            case R.id.btnEditarPerfil:
                editarDeletarPerfil = new EditarDeletarPerfil();
                editarDeletarPerfil.setPerfil(perfil);

                fragmentManager.beginTransaction().replace(R.id.content_frame, editarDeletarPerfil).commit();
                break;
        }
    }
}//fragment
