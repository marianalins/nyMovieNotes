package marianalins.github.com.nymovienotes.frontend;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import marianalins.github.com.nymovienotes.R;
import marianalins.github.com.nymovienotes.backend.Ator;
import marianalins.github.com.nymovienotes.backend.Diretor;
import marianalins.github.com.nymovienotes.backend.Filme;
import marianalins.github.com.nymovienotes.backend.Mostraveis;
import marianalins.github.com.nymovienotes.backend.Pessoa;
import marianalins.github.com.nymovienotes.backend.PessoaController;
import marianalins.github.com.nymovienotes.backend.Serie;
import marianalins.github.com.nymovienotes.backend.Titulo;
import marianalins.github.com.nymovienotes.backend.TituloController;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Cartoes#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Cartoes extends Fragment implements View.OnClickListener,
        DialogInterface.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Mostraveis mostravel;

    // view elements
    private TextView nota, nome;
    private ImageView foto;
    private LinearLayout editar, deletar;

    private OnFragmentInteractionListener mListener;

    public Cartoes() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Cartoes.
     */
    // TODO: Rename and change types and number of parameters
    public static Cartoes newInstance(Mostraveis mostravel) {
        Cartoes fragment = new Cartoes();
        fragment.mostravel = mostravel;
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
        View view =  inflater.inflate(R.layout.fragment_cartoes, container,
                false);
        inicializaUI(view);
        getMostravelInfo();
        return view;
    }

    // mostra info nos cartoes
    private void getMostravelInfo() {
        nome.setText(mostravel.getNome());
        nota.setText("" + mostravel.getNota());
        if(mostravel.getFoto() != null && mostravel.getFoto().exists()) {
            foto.setImageBitmap(BitmapFactory.decodeFile(
                    mostravel.getFoto().getAbsolutePath()));
        } else {
            foto.setImageResource(R.drawable.pop2);
        }
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

    private void inicializaUI(View view) {
        nome = (TextView) view.findViewById(R.id.nome);
        nota = (TextView) view.findViewById(R.id.nota);
        editar = (LinearLayout) view.findViewById(R.id.editar);
        editar.setOnClickListener(this);
        deletar = (LinearLayout) view.findViewById(R.id.deletar);
        deletar.setOnClickListener(this);
        foto = (ImageView) view.findViewById(R.id.foto);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.editar) {
            editarClick();
        } else if(view.getId() == R.id.deletar) {
            deletarClick();
        }
    }

    public void editarClick() {
        Tipo tipo;
        FragmentoAdicionar2 f;
        if(mostravel instanceof Pessoa) {
            Pessoa p = (Pessoa) mostravel;
            if(mostravel instanceof Ator) {
                tipo = Tipo.ATOR;
            } else {
                tipo = Tipo.DIRETOR;
            }
            f = FragmentoAdicionar2.newInstance(Acao.EDITAR, tipo, p);
        } else {
            Titulo t = (Titulo) mostravel;
            if(mostravel instanceof Filme) {
                tipo = Tipo.FILME;
            } else if(mostravel instanceof Serie) {
                tipo = Tipo.SERIE;
            } else {
                tipo = Tipo.EPISODIO;
            }
            f = FragmentoAdicionar2.newInstance(Acao.EDITAR, tipo,t);
        }

        getActivity().getSupportFragmentManager().beginTransaction().
                replace(R.id.fragmentoPrincipal , f).commit();
    }

    public void deletarClick() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this.getContext());
        builder1.setMessage("Deseja deletar \"" + mostravel.getNome() + "\"?");
        builder1.setCancelable(true);
        builder1.setPositiveButton("Sim", this);
        builder1.setNegativeButton(
                "Não",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    public void onClick(DialogInterface dialog, int id) {
        dialog.cancel();
        if(mostravel instanceof Titulo) {
            TituloController t = new TituloController();
            t.remover(mostravel.getCodigo());
        } else {
            PessoaController p = new PessoaController();
            p.remover(mostravel.getCodigo());
        }

        getView().animate().alpha(0f).rotation(1080f).setDuration(850L);
        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                getFragmentManager().beginTransaction().remove(Cartoes.this).commit();
            }
        }, 850);
    }

    public void confirmacao(String msg) {

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
