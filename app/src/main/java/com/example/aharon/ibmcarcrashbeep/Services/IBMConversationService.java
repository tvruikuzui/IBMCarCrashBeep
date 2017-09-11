package com.example.aharon.ibmcarcrashbeep.Services;

import android.os.AsyncTask;
import android.util.Log;

import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Aharon on 23/08/2017.
 */

public class IBMConversationService {

    public String integrateWithIBM(String message){

        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {

                ConversationService service = new ConversationService("2017-05-26");
                service.setUsernameAndPassword("e355c7ce-af2a-45f9-92b4-a6adc78b895c","j8Gx63QJrwr0");
                MessageRequest request = new MessageRequest.Builder().inputText(params[0]).build();
                MessageResponse response = service.message("cb00f01d-691e-4a22-9b25-4fc75f4e6a78",request).execute();
                Log.d("ahron",response.getOutput().toString());
                JSONObject jsonObject = new JSONObject(response.getOutput());
                try {
                    Log.d("ahron", jsonObject.getJSONArray("text").getString(0));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("ahron",response.getContext().toString());


                return response.toString();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //System.out.println(s);
            }
        }.execute(message);

        return null;
    }

}
