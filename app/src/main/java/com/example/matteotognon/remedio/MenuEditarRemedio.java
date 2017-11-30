package com.example.matteotognon.remedio;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by MatteoTognon on 07/11/2017.
 */

public class MenuEditarRemedio extends Fragment {
    Perfil perfil;
    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.activity_editar_remedio, container, false);
        return myView;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
}
