package marianalins.github.com.nymovienotes.frontend;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import marianalins.github.com.nymovienotes.R;
import marianalins.github.com.nymovienotes.backend.Ator;
import marianalins.github.com.nymovienotes.backend.Diretor;
import marianalins.github.com.nymovienotes.backend.Episodio;
import marianalins.github.com.nymovienotes.backend.Filme;
import marianalins.github.com.nymovienotes.backend.Pessoa;
import marianalins.github.com.nymovienotes.backend.PessoaController;
import marianalins.github.com.nymovienotes.backend.Serie;
import marianalins.github.com.nymovienotes.backend.Titulo;
import marianalins.github.com.nymovienotes.backend.TituloController;

import static marianalins.github.com.nymovienotes.frontend.Acao.ADICIONAR;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentoAdicionar2.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentoAdicionar2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentoAdicionar2 extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private Tipo tipo;
    private Acao acao;
    private String mParam1;
    private String mParam2;
    private Titulo titulo;
    private LinearLayout idiomaLayout , generoLayout , sinopseLayout , starsLayout,
            addBtnLayout , trailerLayout , nomeLayout , anoLayout , duracaoLayout ,
            paisLayout, nascimentoLayout , atorAddBtnLayout;
    private Pessoa pessoa;
    private TextView nomeTxt , paisTxt , anoTxt , duracaoTxt ,
            nascimentoText ,idiomaText , generoText , trailerText ,
            sinopseText , textoAdd , textoAdd2 , starText;
    private EditText nomeEdit , paisEdit , anoEdit , duracaoEdit ,
            nascimentoEdit , idiomaEdit , generoEdit , trailerEdit , sinopseEdit;
    private ImageView atorAdd , add ,star1Image ,star2Image
            ,star3Image ,star4Image ,star5Image;



    private OnFragmentInteractionListener mListener;

    public FragmentoAdicionar2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentoAdicionar2.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentoAdicionar2 newInstance(String param1, String param2) {
        FragmentoAdicionar2 fragment = new FragmentoAdicionar2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    public static FragmentoAdicionar2 newInstance(Acao acao, Tipo tipo, Pessoa pessoa) {
        FragmentoAdicionar2 f = newInstance(acao, tipo);
        f.pessoa = pessoa;
        return f;
    }

    public static FragmentoAdicionar2 newInstance(Acao acao, Tipo tipo, Titulo titulo) {
        FragmentoAdicionar2 f = newInstance(acao, tipo);
        f.titulo = titulo;
        return f;
    }


    private static FragmentoAdicionar2 newInstance(Acao acao, Tipo tipo) {
        FragmentoAdicionar2 fragment = new FragmentoAdicionar2();
        fragment.acao = acao;
        fragment.tipo = tipo;
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

        View view =  inflater.inflate(R.layout.fragment_fragmento_adicionar2, container, false);
        inicializaUI(view);
        atualizarUI();
        return view;
    }

    private void inicializaUI(View view) {
        starText = (TextView) view.findViewById(R.id.starText);
        nomeTxt = (TextView) view.findViewById(R.id.nomeTxt);
        nomeEdit = (EditText) view.findViewById(R.id.nomeEdit);
        paisTxt = (TextView) view.findViewById(R.id.paisTxt);
        paisEdit = (EditText) view.findViewById(R.id.paisEdit);
        anoTxt = (TextView) view.findViewById(R.id.anoTxt);
        anoEdit = (EditText) view.findViewById(R.id.anoEdit);
        duracaoTxt = (TextView) view.findViewById(R.id.duracaoTxt);
        duracaoEdit = (EditText) view.findViewById(R.id.duracaoEdit);
        nascimentoText = (TextView) view.findViewById(R.id.nascimentoText);
        nascimentoEdit = (EditText) view.findViewById(R.id.nascimentoEdit);
        idiomaText = (TextView) view.findViewById(R.id.idiomaText);
        idiomaEdit = (EditText) view.findViewById(R.id.idiomaEdit);
        generoText = (TextView) view.findViewById(R.id.generoText);
        generoEdit = (EditText) view.findViewById(R.id.generoEdit);
        trailerText = (TextView) view.findViewById(R.id.trailerText);
        trailerEdit = (EditText) view.findViewById(R.id.trailerEdit);
        sinopseText = (TextView) view.findViewById(R.id.sinopseText);
        sinopseEdit = (EditText) view.findViewById(R.id.sinopseEdit);
        textoAdd = (TextView) view.findViewById(R.id.textoAdd);
        atorAdd = (ImageView) view.findViewById(R.id.atorAdd);
        atorAdd.setOnClickListener(this);
        textoAdd2 = (TextView) view.findViewById(R.id.textoAdd2);
        add = (ImageView) view.findViewById(R.id.add);
        add.setOnClickListener(this);
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
        idiomaLayout = (LinearLayout) view.findViewById(R.id.idiomaLayout);
        generoLayout = (LinearLayout) view.findViewById(R.id.generoLayout);
        sinopseLayout = (LinearLayout) view.findViewById(R.id.sinopseLayout);
        starsLayout = (LinearLayout) view.findViewById(R.id.starsLayout);
        addBtnLayout = (LinearLayout) view.findViewById(R.id.addBtnLayout);
        trailerLayout = (LinearLayout) view.findViewById(R.id.trailerLayout);
        nomeLayout = (LinearLayout) view.findViewById(R.id.nomeLayout);
        paisLayout = (LinearLayout) view.findViewById(R.id.paisLayout);
        anoLayout = (LinearLayout) view.findViewById(R.id.anoLayout);
        duracaoLayout  = (LinearLayout) view.findViewById(R.id.duracaoLayout);
        nascimentoLayout = (LinearLayout) view.findViewById(R.id.nascimentoLayout);
        atorAddBtnLayout = (LinearLayout) view.findViewById(R.id.atorAddBtnLayout);
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
//==================================================================================
    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.atorAdd) {
            adicionarAtorBtnClick(view);
        }
    }

    public void adicionarAtorBtnClick(View view) {
        PessoaController p = new PessoaController();

        if(acao == Acao.ADICIONAR) {
            if(tipo == Tipo.ATOR) {
                Ator ator = (Ator) pessoa;
                SimpleDateFormat sd = new SimpleDateFormat("dd/mm/aaaa");
                try {
                    ator.setDataNascimento(sd.parse(nascimentoEdit.getText().toString()));
                } catch(ParseException e) {
                    criaAlert("Data Invalida", "A data entrada " + nascimentoEdit.getText().toString() +
                    " não é uma data válida!");
                }
                if(!ehVazio(paisEdit)) {
                    ator.setPais(paisEdit.getText().toString().trim());
                }
                p.adicionar(ator);
            } else {
                Diretor diretor = new Diretor(nomeEdit.getText().toString());
            }
        }

    }

    private boolean ehVazio(EditText e) {
        return (e.getText().toString().trim().isEmpty() ||
                e.getText().toString().trim().equals(""));
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
    //====================================================================
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
//=========================================================================
    public void adicionarInfo() {
        if(titulo instanceof Filme) {
            Filme filme = (Filme) titulo;
        } else if(titulo instanceof Serie) {
            Serie serie = (Serie) titulo;
        } else if(titulo instanceof Episodio) {
            Episodio episodio = (Episodio) titulo;
        }
    }
//=========================================================================

    public void update(Acao acao , Tipo tipo , Pessoa pessoa) {
        this.pessoa = pessoa;
        this.acao = acao;
        this.tipo = tipo;
    }

    public void atualizarUI() {
        if(tipo == Tipo.DIRETOR || tipo == Tipo.ATOR) {
            anoTxt.setVisibility(View.GONE);
            anoEdit.setVisibility(View.GONE);
            duracaoEdit.setVisibility(View.GONE);
            duracaoTxt.setVisibility(View.GONE);
            idiomaEdit.setVisibility(View.GONE);
            idiomaText.setVisibility(View.GONE);
            generoText.setVisibility(View.GONE);
            generoEdit.setVisibility(View.GONE);
            trailerText.setVisibility(View.GONE);
            trailerEdit.setVisibility(View.GONE);
            sinopseText.setVisibility(View.GONE);
            sinopseEdit.setVisibility(View.GONE);
            trailerText.setVisibility(View.GONE);
            trailerEdit.setVisibility(View.GONE);
            textoAdd2.setVisibility(View.GONE);
            add.setVisibility(View.GONE);
            idiomaLayout.setVisibility(View.GONE);
            generoLayout.setVisibility(View.GONE);
            starsLayout.setVisibility(View.GONE);
            sinopseLayout.setVisibility(View.GONE);
            addBtnLayout.setVisibility(View.GONE);
            trailerLayout.setVisibility(View.GONE);
            nomeLayout.setVisibility(View.GONE);


            if (acao == Acao.ADICIONAR) {
                nomeEdit.setVisibility(View.GONE);
                nomeTxt.setVisibility(View.GONE);
                starText.setVisibility(View.GONE);
                star1Image.setVisibility(View.GONE);
                star2Image.setVisibility(View.GONE);
                star3Image.setVisibility(View.GONE);
                star4Image.setVisibility(View.GONE);
                star5Image.setVisibility(View.GONE);
            }
        } else if(tipo == Tipo.FILME || tipo == Tipo.SERIE || tipo == Tipo.EPISODIO) {
            nascimentoText.setText("Nº de Temporadas:");
            nascimentoEdit.setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED);
            nascimentoEdit.setHint("Nº de Temporadas");
            textoAdd.setVisibility(View.GONE);
            atorAdd.setVisibility(View.GONE);
            atorAddBtnLayout.setVisibility(View.GONE);


            if(acao == Acao.ADICIONAR){
                nomeEdit.setVisibility(View.GONE);
                nomeTxt.setVisibility(View.GONE);
                starText.setVisibility(View.GONE);
                star1Image.setVisibility(View.GONE);
                star2Image.setVisibility(View.GONE);
                star3Image.setVisibility(View.GONE);
                star4Image.setVisibility(View.GONE);
                star5Image.setVisibility(View.GONE);
            }
            if(tipo == Tipo.FILME) {
                nascimentoText.setVisibility(View.GONE);
                nascimentoEdit.setVisibility(View.GONE);
                nascimentoLayout.setVisibility(View.GONE);
            }
        }
    }

    public void update(Acao acao , Tipo tipo , Titulo titulo) {
        this.titulo = titulo;
        this.acao = acao;
        this.tipo = tipo;

        //Se for editar aparece tudo e se for serie aparece tudo
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
