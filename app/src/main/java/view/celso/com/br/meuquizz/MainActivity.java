package view.celso.com.br.meuquizz;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private RadioGroup radioGroup;
    private TextView   titulo;
    private RadioButton buttonA;
    private RadioButton buttonB;
    private RadioButton buttonC;
    private Button botaoOK;
    private TextView txtPergunta;

    String[] perguntas = {
            "primeira pergunta",
            "segunda Pergunta",
            "terceira pergunta",
            "quarta pergunta",
            "quinta pergunta"
    };
    String[] opcaoB = {
            "Resposta B da primeira pergunta",
            "Resposta B da segunda pergunta",
            "Resposta B da terceira pergunta",
            "Resposta B da quarta pergunta",
            "Resposta B da quinta pergunta"
    };
    String[] opcaoC = {
            "Resposta C da primeira pergunta",
            "Resposta C da segunda pergunta",
            "Resposta C da terceira pergunta",
            "Resposta C da quarta pergunta",
            "Resposta C da quinta pergunta"
    };
    String[] opcaoA = {
            "Resposta A da primeira pergunta",
            "Resposta A da segunda pergunta",
            "Resposta A da terceira pergunta",
            "Resposta A da quarta pergunta",
            "Resposta A da quinta pergunta"
    };

    int[] listaRespostas =new  int[perguntas.length];
    int gabarito[] = {1,2,3,1,2};
    int respostasCorretas = 0;
    int numeroPergunta = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtPergunta = (TextView) findViewById(R.id.txtPergunta);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        buttonA = (RadioButton) findViewById(R.id.opcaoA);
        buttonB = (RadioButton) findViewById(R.id.opcaoB);
        buttonC = (RadioButton) findViewById(R.id.opcaoC);
        botaoOK = (Button) findViewById(R.id.btnOk);
        botaoOK.setEnabled(false);

        btnOk(botaoOK);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.opcaoA:
                        Log.i("s", "opção A");
                        listaRespostas[numeroPergunta-1] = 1;
                    break;
                    case R.id.opcaoB:
                        Log.i("s", "opção B");
                        listaRespostas[numeroPergunta-1] = 2;
                        break;
                    case R.id.opcaoC:
                        Log.i("s", "opção C");
                        listaRespostas[numeroPergunta-1] = 3;
                        break;
                }
                botaoOK.setEnabled(true);
            }
        });

    }

    public void btnOk(View v){
        if(numeroPergunta == perguntas.length){
            confereResutado();
            botaoOK.setEnabled(false);
            buttonA.setEnabled(false);
            buttonB.setEnabled(false);
            buttonC.setEnabled(false);
        }else {
            txtPergunta.setText(perguntas[numeroPergunta]);
            buttonA.setText(opcaoA[numeroPergunta]);
            buttonB.setText(opcaoB[numeroPergunta]);
            buttonC.setText(opcaoC[numeroPergunta]);
            numeroPergunta++;
            botaoOK.setEnabled(false);
            radioGroup.clearCheck();
        }

    }

    public void confereResutado(){
        int contadorLista = 0;
        for(int numero : listaRespostas){
            if(numero == gabarito[contadorLista]){
                //Toast.makeText(this,"Resposta Correta", Toast.LENGTH_LONG).show();
                respostasCorretas++;
            }else{
                //Toast.makeText(this,"Resposta Errada", Toast.LENGTH_LONG).show();
            }
            contadorLista++;

        }
        alertaResultado(botaoOK);
        //Toast.makeText(this,"Resposta Correta " +respostasCorretas , Toast.LENGTH_LONG).show();
    }

    public void alertaResultado(View v){
        AlertDialog alertDialog;
        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Funcionou!");
        alertDialog.setMessage("Voce acertou "+respostasCorretas+ " Questoes");
        alertDialog.show();
    }
}
