package marianalins.github.com.nymovienotes.frontend;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.List;

import marianalins.github.com.nymovienotes.R;
import marianalins.github.com.nymovienotes.backend.Iterador;
import marianalins.github.com.nymovienotes.backend.IteradorLista;
import marianalins.github.com.nymovienotes.backend.Mostraveis;
import marianalins.github.com.nymovienotes.backend.NaoAchadoException;
import marianalins.github.com.nymovienotes.backend.Pessoa;
import marianalins.github.com.nymovienotes.backend.PessoaController;
import marianalins.github.com.nymovienotes.backend.Titulo;
import marianalins.github.com.nymovienotes.backend.TituloController;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentoProcura.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentoProcura#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentoProcura extends Fragment implements View.OnClickListener {
    private Button procurarBtn;
    private EditText nomeEdit;
    private RadioButton procurarTituloBtn , procurarPessoaBtn;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragmentoProcura() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentoProcura.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentoProcura newInstance(String param1, String param2) {
        FragmentoProcura fragment = new FragmentoProcura();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static FragmentoProcura newInstance() {
        FragmentoProcura fragment = new FragmentoProcura();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_one, container, false);
        procurarBtn = (Button) view.findViewById(R.id.procurarBtn);
        procurarBtn.setOnClickListener(this);
        nomeEdit = (EditText) view.findViewById(R.id.nomeEdit);
        procurarTituloBtn = (RadioButton) view.findViewById(R.id.procurarTituloBtn);
        procurarPessoaBtn = (RadioButton) view.findViewById(R.id.procurarPessoaBtn);
        return view;
    }

    //--Eventos-------------------------------------------------------------------------------------

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.procurarBtn) {
            procurarBtnClick(view);
        }
    }

    // Botão Procurar Nome

    @SuppressWarnings("unchecked")
    public void procurarBtnClick(View view) {
        if(nomeEdit.getText().toString().trim().equals("") ||
                nomeEdit.getText().toString().isEmpty()) {
            criaAlert("Procura em branco" , "Digite um nome a ser procurado.");
            nomeEdit.requestFocus();
            return;
        }

        if(procurarTituloBtn.isChecked()) {
            TituloController t = new TituloController();
            Iterador<Mostraveis> retorno;
            try {
                retorno = new IteradorLista<Mostraveis>(
                        t.getTitulos(nomeEdit.getText().toString().trim()));
                FragmentoExibir fragmentoExibir = FragmentoExibir.newInstance(retorno);
            } catch (NaoAchadoException e) {
                criaAlert("Não Encontrado", "O Titulo " +
                        nomeEdit.getText().toString().trim() + " não foi encontrado.");
                nomeEdit.requestFocus();
            }
        } else if(procurarPessoaBtn.isChecked()) {
            PessoaController t = new PessoaController();
            try {
                retorno = t.getPessoa(nomeEdit.getText().toString().trim());

            } catch (NaoAchadoException e) {
                criaAlert("Não Encontrado", "O Artista " +
                        nomeEdit.getText().toString().trim() + " não foi encontrado.");
                nomeEdit.requestFocus();
            }
        }


        getActivity().getSupportFragmentManager().beginTransaction().replace
                (R.id.fragmentoPrincipal , fragmentoExibir).commit();
    }

    public void criaAlert(String titulo , String msg) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this.getContext());
        builder1.setMessage(msg);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        /*builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });*/

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }


    //----------------------------------------------------------------------------------------------
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
}
