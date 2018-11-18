package marianalins.github.com.nymovienotes.frontend;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import marianalins.github.com.nymovienotes.R;
import java.util.List;

import marianalins.github.com.nymovienotes.backend.Iterador;
import marianalins.github.com.nymovienotes.backend.Mostraveis;
import marianalins.github.com.nymovienotes.backend.Pessoa;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentoExibir#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentoExibir extends Fragment implements
        Cartoes.OnFragmentInteractionListener {
    private LinearLayout filmesFragmento;
    private TextView exibindoTxt;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private Iterador<Mostraveis> iter;

    private OnFragmentInteractionListener mListener;

    public FragmentoExibir() {
        // Required empty public constructor
    }

    public static FragmentoExibir newInstance(Iterador<Mostraveis> iter) {
        FragmentoExibir fragment = new FragmentoExibir();
        fragment.iter = iter;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragmento_exibir,
                container, false);
        filmesFragmento = (LinearLayout) view.findViewById(
                R.id.filmesFragmento);

        exibindoTxt = (TextView) view.findViewById(R.id.exibindoTxt);
        if(iter.hasNext()) {
            if(iter.peek() instanceof Pessoa) {
                exibindoTxt.setText("Atores e Diretores");
            }
        }
        //carregaFrgmento(fragment, Cartoes.class);
        mostraCartoes();
        return view;
    }

    public void mostraCartoes() {
        //Fragment fragment = null;
        FragmentTransaction fragmentTransaction = getChildFragmentManager()
                .beginTransaction();
        //fragmentTransaction.commitNow();
        while(iter.hasNext()) {
            try {
                Cartoes fragment = Cartoes.newInstance(iter.next());
                fragmentTransaction.add(R.id.filmesFragmento, fragment);
            } catch(Exception e) {
                Log.d("Fragmento", "Nao conseguiu carregar " +
                        "fragmento");
            }
        }
        fragmentTransaction.commit();

    }

    private void carregaFragmento(Fragment fragment, Class fragmentClass) {
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager =
                ((MyMovieNotes) getActivity()).getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.filmesFragmento, fragment).commit();
                //replace(R.id.fragmentoPrincipal,fragment).commit();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        // empty body
    }
}
