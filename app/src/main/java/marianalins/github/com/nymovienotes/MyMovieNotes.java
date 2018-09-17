package marianalins.github.com.nymovienotes;

import android.Manifest;
import android.app.AlertDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import marianalins.github.com.nymovienotes.back.NaoAchadoException;
import marianalins.github.com.nymovienotes.back.PessoaController;
import marianalins.github.com.nymovienotes.back.TituloController;

public class MyMovieNotes extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        FragmentoProcura.OnFragmentInteractionListener,
        FragmentoRemover.OnFragmentInteractionListener,
        FragmentoAdicionar.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_movie_notes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActivityCompat.requestPermissions(MyMovieNotes.this,
                new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                },100);

        //--Codigo do gerenciamento de fragmentos---------------------------------------------------
        Fragment fragment = null;
        Class fragmentClass = null;
        fragmentClass = FragmentoProcura.class;
        carregaFragmento(fragment, fragmentClass);

        //------------------------------------------------------------------------------------------

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setTitle("myMovie Note");


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
        getMenuInflater().inflate(R.menu.my_movie_notes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.procurarMenu || id == R.id.adicionarMenu || id == R.id.removerMenu) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
//-------------------------------------------------------------------------------------------
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        Class fragmentClass = null;

        if (id == R.id.nav_procurar) {
            fragmentClass = FragmentoProcura.class;
        } else if (id == R.id.nav_adicionar) {
            fragmentClass = FragmentoAdicionar.class;
        } else if (id == R.id.nav_remover) {
            fragmentClass = FragmentoRemover.class;
        }

        carregaFragmento(fragment, fragmentClass);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // carrega fragmento dependendo da class que herda fragmento
    private void carregaFragmento(Fragment fragment, Class fragmentClass) {
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch(Exception e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragmentoPrincipal, fragment).commit();
    }

    @Override @SuppressWarnings("StatementWithEmptyBody")
    public void onFragmentInteraction(Uri uri) {
        // vazia
    }

    public void procurarMenuClick(MenuItem v) {
        Fragment fragment = null;
        Class fragmentClass = FragmentoProcura.class;
        carregaFragmento(fragment, fragmentClass);
    }

    public void adicionarMenuClick(MenuItem v) {
        Fragment fragment = null;
        Class fragmentClass = FragmentoAdicionar.class;
        carregaFragmento(fragment, fragmentClass);
    }

    public void removerMenuClick(MenuItem v) {
        Fragment fragment = null;
        Class fragmentClass = FragmentoRemover.class;
        carregaFragmento(fragment, fragmentClass);
    }
}
