package br.edu.ifpb.atividadeandroid.asynctask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

import br.edu.ifpb.atividadeandroid.activity.MainActivity;
import br.edu.ifpb.atividadeandroid.util.HttpService;
import br.edu.ifpb.atividadeandroid.util.Response;


public class BuscarNomeAsyncTask extends AsyncTask<JSONObject, Void, Response> {

    @Override
    protected Response doInBackground(JSONObject... jsons) {

        Response response = null;

        JSONObject json = jsons[0];
        
        Log.i("EditTextListener", "doInBackground (JSON): " + json);

        try {

            response = HttpService.sendJSONPostResquest("get-byname", json);

        } catch (IOException e) {

            Log.e("EditTextListener", e.getMessage());
            
        }

        return response;
    }

    @Override
    protected void onPostExecute(Response response) {
    	
    	   String name = null;

           List<String> names = new ArrayList<String>();

           int i=0;
           
           try {
        	   
               JSONArray jsonArray = new JSONArray(response.getContentValue());

               while(jsonArray.getString(i)!= null) {
            	   
                   JSONObject jo = new JSONObject(jsonArray.getString(i));
                   
                   name = jo.getString("fullName");
                   
                   names.add(name);
                   
                   i++;
               }

           } catch (JSONException e) {
        	   
               e.printStackTrace();
               
           }

           MainActivity.Listar(names);

       }
           
}
