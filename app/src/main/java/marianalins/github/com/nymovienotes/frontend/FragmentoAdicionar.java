package marianalins.github.com.nymovienotes.frontend;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

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

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentoAdicionar.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentoAdicionar#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentoAdicionar extends Fragment implements View.OnClickListener,
        FragmentoAdicionar2.OnFragmentInteractionListener {
    private Button adicionarBtn, adicionarInfoBtn;
    private EditText nomeAdicionarEdit;
    private RadioGroup escolhaFS;
    private RadioButton adicionarTituloBtn , adicionarPessoaBtn;
    private RadioButton opcao1Btn , opcao2Btn;
    private CheckBox imdbActivate;
    private EditText numTempEditText;
    private TextView nomeAdicionarTextView, numTempTextView;
    private Tipo tipo = Tipo.FILME;
    private Acao acao = Acao.ADICIONAR;
    private Pessoa pessoa = null;
    private Titulo titulo = null;
    LinearLayout campoEscolhaLayout;
    private RadioGroup campoEscolha;
    private int estrelas = 0;
    private File imgPath = null;
    private ImageView star1Image ,star2Image ,star3Image ,star4Image ,star5Image, imgV;

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

    public Acao getAcao() {
        return acao;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public Titulo getTitulo() {
        return titulo;
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

    public static FragmentoAdicionar newInstance(Acao acao, Tipo tipo, Pessoa pessoa) {
        FragmentoAdicionar f = FragmentoAdicionar.newInstance(acao, tipo);
        f.pessoa = pessoa;
        return f;
    }

    public static FragmentoAdicionar newInstance(Acao acao, Tipo tipo, Titulo titulo) {
        FragmentoAdicionar f = FragmentoAdicionar.newInstance(acao, tipo);
        f.titulo = titulo;
        return f;
    }

    private static FragmentoAdicionar newInstance(Acao acao, Tipo tipo) {
        FragmentoAdicionar f = new FragmentoAdicionar();
        f.acao = acao;
        f.tipo = tipo;
        return f;
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
        opcao1Btn.setVisibility(View.GONE);
        opcao2Btn.setVisibility(View.GONE);
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
        adicionarInfoBtn = (Button) view.findViewById(R.id.adicionarInfoBtn);
        adicionarInfoBtn.setOnClickListener(this);
        campoEscolha = (RadioGroup) view.findViewById(R.id.campoEscolha);
        escolhaFS = (RadioGroup) view.findViewById(R.id.escolhaFS);
        campoEscolhaLayout = (LinearLayout) view.findViewById(R.id.campoEscolhaLayout);
        imgV = (ImageView) view.findViewById(R.id.imgV);
        final int RESULT_LOAD_IMAGE = 1;
        imgV.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

        if(acao == Acao.SUBADICIONAR) {
            if(tipo == Tipo.ATOR || tipo == Tipo.DIRETOR) {
                adicionarPessoaBtn.setChecked(true);
                opcao1Btn.setText("Ator");
                opcao2Btn.setText("Diretor");

                if(opcao2Btn.isChecked()) {
                    numTempTextView.setVisibility(View.VISIBLE);
                    numTempEditText.setVisibility(View.VISIBLE);
                }
            } else if(tipo == Tipo.FILME || tipo == Tipo.SERIE || tipo == Tipo.EPISODIO) {
                adicionarTituloBtn.setChecked(true);
                opcao1Btn.setText("Filme");
                opcao2Btn.setText("Serie");
            }
            opcao1Btn.setVisibility(View.VISIBLE);
            opcao2Btn.setVisibility(View.VISIBLE);
            campoEscolhaLayout.setVisibility(View.GONE);

        }
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getActivity().getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            imgPath = new File(picturePath);
            cursor.close();

            imgV.setImageBitmap(BitmapFactory.decodeFile(picturePath));

        }


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
        estrelas = num;
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
            getFragmentManager().beginTransaction().replace(R.id.fragmentoPrincipal,
                    FragmentoProcura.newInstance()).commit();
            //   adicionarBtnClick(view);
        } else if(view.getId() == R.id.adicionarInfoBtn) {
            adicionarInfoBtnClick(view);
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

    public void adicionarInfoBtnClick(View view) {
        adicionarBtnClick(view);
//        MyMovieNotes act = (MyMovieNotes) getActivity();
//        System.out.println(act);
        FragmentoAdicionar2 frag;
        if(tipo == Tipo.FILME || tipo == Tipo.SERIE || tipo == Tipo.EPISODIO){
            frag = FragmentoAdicionar2.newInstance(acao, tipo, titulo);
        } else {
            frag = FragmentoAdicionar2.newInstance(acao, tipo, pessoa);
        }
        getFragmentManager().beginTransaction().replace(R.id.fragmentoPrincipal, frag).commitNow();
        /*if(tipo == Tipo.FILME || tipo == Tipo.SERIE || tipo == Tipo.EPISODIO){
            act.fragmentoAdicionar2(acao, tipo, titulo);
        } else {
            act.fragmentoAdicionar2(acao, tipo, pessoa);
        }*/
    }

    public void eventoRadioBtnClick() {
        if(adicionarTituloBtn.isChecked()) {
            opcao1Btn.setText("Filme");
            opcao2Btn.setText("Serie");
            opcao1Btn.setVisibility(View.VISIBLE);
            opcao2Btn.setVisibility(View.VISIBLE);
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
            opcao1Btn.setVisibility(View.VISIBLE);
            opcao2Btn.setVisibility(View.VISIBLE);
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
                filme.setNota(estrelas);
                filme.setFoto(imgPath);
                if(acao == Acao.SUBADICIONAR) {
                    pessoa.adicionarTitulo(filme);
                } else {
                    t.adicionar(filme);
                }
                titulo = filme;
                tipo = Tipo.FILME;
            } else {
                if(acao != Acao.SUBADICIONAR) {
                    Serie serie = new Serie(nome);
                    serie.setNota(estrelas);
                    t.adicionar(serie);
                    serie.setFoto(imgPath);
                    tipo = Tipo.SERIE;
                    String temp = numTempEditText.getText().toString().trim();
                    if (!temp.isEmpty() && !temp.equals("")) {
                        try {
                            serie.setNumTemporadas(Integer.parseInt(temp));
                        } catch (NumberFormatException e) {
                            criaAlert("Número Inválido",
                                    "Formato de número invalido: " + temp);
                            return;
                        }
                    }
                    titulo = serie;
                } else {
                    Episodio ep = new Episodio(nome, (Serie) titulo);
                    ep.setNota(estrelas);
                    ep.setFoto(imgPath);
                    try {
                        numTempTextView.setText("Pertence a qual Temporada?");
                        ep.setTemporada(Integer.parseInt(numTempEditText.getText()
                                .toString()));
                    } catch (NumberFormatException e1) {
                        criaAlert("Número Inválido",
                                "Formato de número invalido");
                        return;
                    }
                }
            }
        } else {
            if (opcao1Btn.isChecked()) {
                Ator o = new Ator(nomeAdicionarEdit.getText().toString());
                p.adicionar(o);
                o.setNota(estrelas);
                o.setFoto(imgPath);;
                tipo = Tipo.ATOR;
                pessoa = o;
            } else {
                Diretor d = new Diretor(nomeAdicionarEdit.getText().toString());
                p.adicionar(d);
                d.setFoto(imgPath);
                d.setNota(estrelas);
                tipo = Tipo.DIRETOR;
                pessoa = d;
            }
        }
        Toast.makeText(this.getContext(), "Adicionado com sucesso!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPause() {
        super.onPause();
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

    @Override
    public void onFragmentInteraction(Uri uri) {
        //
    }
}




