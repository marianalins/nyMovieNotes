package marianalins.github.com.nymovienotes;

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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import marianalins.github.com.nymovienotes.back.Ator;
import marianalins.github.com.nymovienotes.back.Diretor;
import marianalins.github.com.nymovienotes.back.Filme;
import marianalins.github.com.nymovienotes.back.NaoAchadoException;
import marianalins.github.com.nymovienotes.back.PessoaController;
import marianalins.github.com.nymovienotes.back.Serie;
import marianalins.github.com.nymovienotes.back.Titulo;
import marianalins.github.com.nymovienotes.back.TituloController;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentoAdicionar.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentoAdicionar#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentoAdicionar extends Fragment implements View.OnClickListener {
    private Button adicionarBtn;
    private EditText nomeAdicionarEdit;
    private RadioButton adicionarTituloBtn , adicionarPessoaBtn;
    private RadioButton opcao1Btn , opcao2Btn;
    private CheckBox imdbActivate;
    EditText numTempEditText;
    TextView nomeAdicionarTextView, numTempTextView;
    private ImageView star1Image ,star2Image ,star3Image ,star4Image ,star5Image ;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragmentoAdicionar() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentoAdicionar.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentoAdicionar newInstance(String param1, String param2) {
        FragmentoAdicionar fragment = new FragmentoAdicionar();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
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
        View view = inflater.inflate(R.layout.fragment_fragmento_adicionar, container, false);
        adicionarBtn = (Button) view.findViewById(R.id.adicionarBtn);
        adicionarBtn.setOnClickListener(this);
        star1Image = (ImageView) view.findViewById(R.id.star1Image);
        star2Image = (ImageView) view.findViewById(R.id.star2Image);
        star3Image = (ImageView) view.findViewById(R.id.star3Image);
        star4Image = (ImageView) view.findViewById(R.id.star4Image);
        star5Image = (ImageView) view.findViewById(R.id.star5Image);
        star1Image.setOnClickListener(this);
        star2Image.setOnClickListener(this);
        star3Image.setOnClickListener(this);
        star4Image.setOnClickListener(this);
        star5Image.setOnClickListener(this);
        opcao1Btn = (RadioButton) view.findViewById(R.id.opcao1Btn);
        opcao2Btn = (RadioButton) view.findViewById(R.id.opcao2Btn);
        opcao1Btn.setOnClickListener(this);
        opcao2Btn.setOnClickListener(this);
        opcao1Btn.setAlpha(0);
        opcao2Btn.setAlpha(0);
        nomeAdicionarTextView = (TextView) view.findViewById(R.id.nomeAdicionarTextView);
        imdbActivate = (CheckBox) view.findViewById(R.id.imdbActivate);
        imdbActivate.setOnClickListener(this);
        numTempEditText = (EditText) view.findViewById(R.id.numTempEditText);
        numTempTextView = (TextView) view.findViewById(R.id.numTempTextView);
        adicionarPessoaBtn = (RadioButton) view.findViewById(R.id.adicionarPessoaBtn);
        adicionarPessoaBtn.setOnClickListener(this);
        adicionarTituloBtn = (RadioButton) view.findViewById(R.id.adicionarTituloBtn);
        adicionarTituloBtn.setOnClickListener(this);
        nomeAdicionarEdit = (EditText) view.findViewById(R.id.nomeAdicionarEdit);
        return view;
    }

    private void resetarEstrela() {
        star1Image.setImageResource(R.drawable.greystar);
        star2Image.setImageResource(R.drawable.greystar);
        star3Image.setImageResource(R.drawable.greystar);
        star4Image.setImageResource(R.drawable.greystar);
        star5Image.setImageResource(R.drawable.greystar);
    }

    private void acenderEstrelas(int num) {
        resetarEstrela();
        switch (num) {
            case 5:
                star5Image.setImageResource(R.drawable.goldstar);
            case 4:
                star4Image.setImageResource(R.drawable.goldstar);
            case 3:
                star3Image.setImageResource(R.drawable.goldstar);
            case 2:
                star2Image.setImageResource(R.drawable.goldstar);
            case 1:
                star1Image.setImageResource(R.drawable.goldstar);
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

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.opcao1Btn) {
            eventoRadioBtnClick();
        } else if(view.getId() == R.id.opcao2Btn) {
            eventoRadioBtnClick();
        } else if(view.getId() == R.id.adicionarPessoaBtn) {
            eventoRadioBtnClick();
        } else if(view.getId() == R.id.adicionarTituloBtn) {
            eventoRadioBtnClick();
        } else if(view.getId() == R.id.adicionarBtn) {
            adicionarBtnClick(view);
         //   adicionarBtnClick(view);
        } else if (view.getId() == R.id.star1Image) {
            acenderEstrelas(1);
        } else if (view.getId() == R.id.star2Image) {
            acenderEstrelas(2);
        } else if (view.getId() == R.id.star3Image) {
            acenderEstrelas(3);
        } else if (view.getId() == R.id.star4Image) {
            acenderEstrelas(4);
        } else if (view.getId() == R.id.star5Image) {
            acenderEstrelas(5);
        }
    }

    public void eventoRadioBtnClick() {
        if(adicionarTituloBtn.isChecked()) {
            opcao1Btn.setText("Filme");
            opcao2Btn.setText("Serie");
            opcao1Btn.setAlpha(1);
            opcao2Btn.setAlpha(1);
            nomeAdicionarTextView.setText("Titulo:");
            if(opcao1Btn.isChecked()) {
                numTempTextView.setVisibility(View.GONE);
                numTempEditText.setVisibility(View.GONE);
            } else {
                numTempTextView.setVisibility(View.VISIBLE);
                numTempEditText.setVisibility(View.VISIBLE);
            }
        } else if(adicionarPessoaBtn.isChecked()) {
            opcao1Btn.setText("Ator");
            opcao2Btn.setText("Diretor");
            opcao1Btn.setAlpha(1);
            opcao2Btn.setAlpha(1);
            nomeAdicionarTextView.setText("Nome:");
        }
    }

    private void adicionarBtnClick(View view) {
        TituloController t = new TituloController();
        PessoaController p = new PessoaController();
        String nome = nomeAdicionarEdit.getText().toString().trim();

        if(!adicionarPessoaBtn.isChecked() && !adicionarTituloBtn.isChecked()) {
            criaAlert("Escolha não efetuada", "Selecionado o tipo a ser adicionado");
            adicionarTituloBtn.requestFocus();
            return ;
        }

        if(nome.equals("") || nome.isEmpty()) {
            criaAlert("Procura em Branco" , "Digite o Nome Para Ser Adicionado.");
            nomeAdicionarEdit.requestFocus();
            return;
        }

        if (adicionarTituloBtn.isChecked()) {
            if (opcao1Btn.isChecked()) {
                Filme filme = new Filme(nome);
                t.adicionar(filme);

            }else {
                Serie serie = new Serie(nome);
                t.adicionar(serie);
                String temp = numTempEditText.getText().toString().trim();
                if(!temp.isEmpty() && !temp.equals("")) {
                    try {
                        serie.setNumTemporadas(Integer.parseInt(temp));
                    } catch (NumberFormatException e) {
                        criaAlert("Número Inválido",
                                "Formato de número invalido: " + temp);
                        return;
                    }
                } else {
                    criaAlert("Número Inválido",
                            "Formato de número invalido: " + temp);
                    return;
                }
            }
        } else {
            if(opcao1Btn.isChecked()) {
                Ator o = new Ator(nomeAdicionarEdit.getText().toString());
                p.adicionar(o);
            } else {
                Diretor d = new Diretor(nomeAdicionarEdit.getText().toString());
                p.adicionar(d);
            }
        }
        Toast.makeText(this.getContext(), "Adicionado com sucesso!", Toast.LENGTH_LONG).show();

    }


    public void criaAlert(String titulo , String msg) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this.getContext());
        builder1.setMessage(msg);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Entendi        ",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
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

