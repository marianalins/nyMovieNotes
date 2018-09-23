package marianalins.github.com.nymovienotes.frontend;

import android.Manifest;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

import java.util.concurrent.ExecutionException;

import marianalins.github.com.nymovienotes.R;
import marianalins.github.com.nymovienotes.backend.Pessoa;
import marianalins.github.com.nymovienotes.backend.PessoaController;
import marianalins.github.com.nymovienotes.backend.Titulo;
import marianalins.github.com.nymovienotes.backend.TituloController;

public class MyMovieNotes extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        FragmentoProcura.OnFragmentInteractionListener,
        FragmentoRemover.OnFragmentInteractionListener,
        FragmentoAdicionar.OnFragmentInteractionListener,
        FragmentoAdicionar2.OnFragmentInteractionListener,
        FragmentoExibir.OnFragmentInteractionListener,
        Cartoes.OnFragmentInteractionListener {
    private Fragment fragment;
    private Class fragmentClass;

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
        fragmentClass = null;
        fragmentClass = FragmentoProcura.class;
        carregaFragmento(fragmentClass);

        //------------------------------------------------------------------------------------------

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

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

        try {
            if (id == R.id.nav_procurar) {
                fragment = (Fragment) FragmentoProcura.class.newInstance();
            } else if (id == R.id.nav_adicionar) {
                fragment = FragmentoAdicionar.class.newInstance();
            } else if (id == R.id.nav_removerTitulo) {
                TituloController t = new TituloController();
                fragment = FragmentoExibir.newInstance(t.getLista());
            } else if (id == R.id.nav_removerPessoa) {
                fragment = FragmentoExibir.newInstance(new PessoaController().getLista());
            }
        } catch(Exception e) {
            return false;
        }

        //carregaFragmento(fragmentClass);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentoPrincipal, fragment)
                .commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // carrega fragmento dependendo da class que herda fragmento
    private void carregaFragmento(Class fragmentClass) {
        fragmentLoad(fragmentClass);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragmentoPrincipal, fragment).commit();
    }

    private void fragmentLoad(Class fragmentClass) {
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void adicionaFragmento(Class fragmentClass) {
        fragmentLoad(fragmentClass);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.fragmentoPrincipal, fragment).commit();
    }


    @Override @SuppressWarnings("StatementWithEmptyBody")
    public void onFragmentInteraction(Uri uri) {
        // vazia
    }

    public void chamarFragmentoAdicionar2() {
        try {
            FragmentoAdicionar f = (FragmentoAdicionar) fragment;
            if(f.getTipo() == Tipo.ATOR || f.getTipo() == Tipo.DIRETOR) {
                fragment = FragmentoAdicionar2.newInstance(f.getAcao(), f.getTipo(), f.getPessoa());
            } else {
                fragment = FragmentoAdicionar2.newInstance(f.getAcao(), f.getTipo(), f.getTitulo());
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentoPrincipal,
                    fragment);
        } catch(Exception e) {
            // nao conseguiu carregar fragmento
        }
    }

    public void procurarMenuClick(MenuItem v) {
        Fragment fragment = null;
        Class fragmentClass = FragmentoProcura.class;
        carregaFragmento(fragmentClass);
    }

    public void adicionarMenuClick(MenuItem v) {
        Fragment fragment = null;
        Class fragmentClass = FragmentoAdicionar.class;
        carregaFragmento(fragmentClass);
    }

    public void removerMenuClick(MenuItem v) {
        fragmentClass = FragmentoRemover.class;
        carregaFragmento(fragmentClass);
    }

    public void fragmentoAdicionar2(Acao acao, Tipo tipo, Pessoa pessoa) {
        FragmentoAdicionar2 f = carregaAdicionar2();
        f.update(acao,tipo,pessoa);

    }

    public void fragmentoAdicionar2(Acao acao, Tipo tipo , Titulo titulo) {
        FragmentoAdicionar2 f = carregaAdicionar2();
        f.update(acao,tipo,titulo);
    }

    private FragmentoAdicionar2 carregaAdicionar2() {
        fragmentClass = FragmentoAdicionar2.class;
        adicionaFragmento(fragmentClass);
        return (FragmentoAdicionar2) fragment;
    }

    public void adicionarInfoBtnClick(View view) {
        //adicionarBtnClick(view);
        //MyMovieNotes act = (MyMovieNotes) getActivity();
        //System.out.println(act);


        /*if(tipo == Tipo.FILME || tipo == Tipo.SERIE || tipo == Tipo.EPISODIO){
            act.fragmentoAdicionar2(Acao.ADICIONAR, tipo, titulo);
        } else {
            act.fragmentoAdicionar2(Acao.ADICIONAR, tipo, pessoa);
        }*/
    }
}
