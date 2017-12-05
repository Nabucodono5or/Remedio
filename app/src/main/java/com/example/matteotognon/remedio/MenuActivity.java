package com.example.matteotognon.remedio;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, GerenciandoPath {

    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    private static final String TAG = "MenuActivity";

    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        auth = FirebaseAuth.getInstance();

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user == null){
                    startActivity(new Intent(MenuActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, new MenuTelainicial()).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getFragmentManager();

        if (id == R.id.Teladeinicio) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new MenuTelainicial()).commit();
        } else if (id == R.id.EditarRemedio) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new MenuEditarRemedio()).commit();
        } else if (id == R.id.EditarPerfis) {
              fragmentManager.beginTransaction().replace(R.id.content_frame, new MenuEditarPerfil()).commit();
        } else if (id == R.id.Perfis) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new MenuPerfis()).commit();
        } else if (id == R.id.Remedios) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new MenuRemedios()).commit();
        } else if(id == R.id.Logout) {
            auth.signOut();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void setFragment(Perfil perfil) {
        FragmentManager fragmentManager = getFragmentManager();
        //inicia uma fragment transaction

        if(!(perfil == null)){
            GerenciamentoPerfil gerenciamentoPerfil = new GerenciamentoPerfil();
            gerenciamentoPerfil.setPerfil(perfil);

            Log.e(TAG, "setFragment");

            fragmentManager.beginTransaction().replace(R.id.content_frame, gerenciamentoPerfil).commit();

        }else {
            Toast.makeText(this,"perfil é nulo",Toast.LENGTH_SHORT).show();
        }
    }
}//class
