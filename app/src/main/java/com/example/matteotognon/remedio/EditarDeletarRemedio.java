package com.example.matteotognon.remedio;

import android.app.AlarmManager;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ToggleButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class EditarDeletarRemedio extends Fragment implements View.OnClickListener{
    View myView;
    int qx, ix;
    Remedio remedio;
    String idPerfil;
    Perfil perfil;
    FirebaseUser user;
    Button btnUpdateRemedio, btnDeleteRemedio;
    EditText receita, inter, quant;
    private DatabaseReference mDatabase;
    ToggleButton toggleButton;
    private static final String TAG = "MyFirstFireBase";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_editar_deletar_remedio, container, false);

        if (container != null) {
            container.removeAllViews();
        }

        user = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference(user.getUid()).child("perfis");

        receita = myView.findViewById(R.id.editTextOldReceita);
        inter = myView.findViewById(R.id.editTextOldIntervalo);
        quant = myView.findViewById(R.id.editTextOldQuant);

        receita.setText(remedio.getNome());
        inter.setText(String.valueOf(remedio.getIntervalo()));
        quant.setText(String.valueOf(remedio.getQuntidade()));

        btnDeleteRemedio = myView.findViewById(R.id.btnDeleteRemedio);
        btnUpdateRemedio = myView.findViewById(R.id.btnUpdateRemedio);

        toggleButton = myView.findViewById(R.id.toggleButtonAlarm);


        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Perfil p = dataSnapshot.getValue(Perfil.class);
                if(p != null) {
                    if(p.getNome().equals(perfil.getNome())){
                        idPerfil = dataSnapshot.getKey();
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

        btnUpdateRemedio.setOnClickListener(this);
        btnDeleteRemedio.setOnClickListener(this);

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    setAlarm();
                    Log.e(TAG,"setando alarm");
                }else {
                    Log.e(TAG,"The toggle is disabled\n");
                    cancelAlarm();
                }
            }
        });


        return myView;
    }


    public void updateRemedio(){
        //usarei o updateChild aqui
        converterIntervaloQuantidade(quant.getText().toString(),inter.getText().toString());
        Remedio re = new Remedio(receita.getText().toString(),qx,ix);

        Map<String,Object> result = new HashMap<String, Object>();
        result.put(re.getNome(),re);
        mDatabase.child(idPerfil).child("remedios").updateChildren(result);
    }

    public void converterIntervaloQuantidade(String q, String i){
        qx = Integer.parseInt(q);
        ix = Integer.parseInt(i);
    }

    public void deleteRemedio(){
        mDatabase.child(idPerfil).child("remedios").child(remedio.getNome()).removeValue();
        Log.e(TAG, "deletado remedio");
    }


    public void setAlarm(){
        //para a nortificação

        boolean alarmeAtivo = (PendingIntent.getBroadcast(getActivity(),0,new Intent("ATIVADO"),PendingIntent.FLAG_NO_CREATE) == null);

        if(alarmeAtivo){

            Log.e(TAG,"alarm não ativo");

            long intervalo = 3600000 * Long.parseLong(inter.getText().toString());

            Intent intent = new Intent("ATIVADO");
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(),0,intent,0);

            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(System.currentTimeMillis());
            c.add(Calendar.SECOND,2);

            AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),intervalo,pendingIntent);
        } else {
            cancelAlarm();
            setAlarm();
        }
    }

    public void cancelAlarm(){
        Intent intent = new Intent("ATIVADO");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(),0,intent,0);

        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
    }

    public void setPerfil(Perfil perfil){
        this.perfil = perfil;
    }

    public void setRemedio(Remedio remedio){
        this.remedio = remedio;
    }


    @Override
    public void onClick(View view) {
        FragmentManager fragmentManager = getFragmentManager();
        if(R.id.btnUpdateRemedio == view.getId()){
            updateRemedio();
            fragmentManager.beginTransaction().replace(R.id.content_frame, new MenuTelainicial()).commit();
        }else if(R.id.btnDeleteRemedio == view.getId()){
            deleteRemedio();
            fragmentManager.beginTransaction().replace(R.id.content_frame, new MenuTelainicial()).commit();
        }
    }
}
