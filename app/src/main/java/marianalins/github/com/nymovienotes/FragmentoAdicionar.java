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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

import marianalins.github.com.nymovienotes.back.NaoAchadoException;
import marianalins.github.com.nymovienotes.back.PessoaController;
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
        adicionarPessoaBtn = (RadioButton) view.findViewById(R.id.adicionarPessoaBtn);
        adicionarTituloBtn = (RadioButton) view.findViewById(R.id.adicionarTituloBtn);
        nomeAdicionarEdit = (EditText) view.findViewById(R.id.nomeAdicionarEdit);
        adicionarTituloBtn = (RadioButton) view.findViewById(R.id.adicionarTituloBtn);
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
        if(view.getId() == R.id.adicionarBtn) {
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

    /*public void adicionarBtnClick(View view) {
        if (nomeAdicionarEdit.getText().toString().trim().equals("") ||
                nomeAdicionarEdit.getText().toString().isEmpty()) {
            criaAlert("Em branco", "Digite um nome a ser adicionado.");
            nomeAdicionarEdit.requestFocus();
            return;
        }

        if (adicionarBtn.isChecked()) {
            TituloController t = new TituloController();
            try {
                t.getTitulos(nomeAdicionarEdit.getText().toString().trim());
            } catch (NaoAchadoException e) {
                criaAlert("Não Encontrado", "O Titulo " +
                        nomeAdicionarEdit.getText().toString().trim() + " não foi encontrado.");
                nomeAdicionarEdit.requestFocus();
            }
        } else {
            PessoaController t = new PessoaController();
            try {
                t.getPessoa(nomeAdicionarEdit.getText().toString().trim());
            } catch (NaoAchadoException e) {
                criaAlert("Não Encontrado", "O Artista " +
                        nomeAdicionarEdit.getText().toString().trim() + " não foi encontrado.");
                nomeAdicionarEdit.requestFocus();
            }
        }
    }
*/


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

