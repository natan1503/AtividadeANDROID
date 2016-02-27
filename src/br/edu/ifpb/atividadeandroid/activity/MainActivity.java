package br.edu.ifpb.atividadeandroid.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;


import org.json.JSONException;
import org.json.JSONObject;

import br.edu.ifpb.atividadeandroid.R;
import br.edu.ifpb.atividadeandroid.asynctask.BuscarNomeAsyncTask;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends Activity implements TextWatcher {

    // Define o tamanho mínimo do texto para consulta no servidor.
    private static int MINIMO = 4;

    private static EditText nomeEditText;
    static List<String> nomes;
    static ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Inicialização da activity e definição do layout.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Recuperando o EditText e
        nomeEditText = (EditText) findViewById(R.id.nomeEditText);
        nomeEditText.addTextChangedListener(this);

        ListView nomesListView = (ListView) findViewById(R.id.nomesListView);
        nomes = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                nomes);

        nomesListView.setAdapter(adapter);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        Log.i("EditTextListener","beforeTextChanged: " + s);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        Log.i("EditTextListener", "onTextChanged: " + s);
        String nome = s.toString();

        // Consultar o servidor. Criar o JSONObject e uma AsyncTask<JSONObject, Void, Response>
        try {

            if (nome.length() >= MINIMO) {
                // JSON
                JSONObject json = new JSONObject();
                json.put("fullName", nome);

                BuscarNomeAsyncTask buscarNomeAsyncTask = new BuscarNomeAsyncTask();
                buscarNomeAsyncTask.execute(json);

                // Adicionar ao ListView.
                nomes.add(nome);
                adapter.notifyDataSetChanged();
            }

        } catch (JSONException e) {

            Log.e("EditTextListener", e.getMessage());
        }
    }

    @Override
    
    public void afterTextChanged(Editable s) {

        Log.i("EditTextListener","afterTextChanged: " + s);
    }
    
    public static void Listar (List<String> names){
    	
    	//Limpando a lista
    	adapter.clear();
    	
        for (int n = 0;n < names.size();n++) {
        	
            nomes.add(names.get(n));
            
            adapter.notifyDataSetChanged();
        }
    }
}