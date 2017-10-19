package madmaxstudios.com.chintokaneventmanager.Entities;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Prabhu Sivanandam on 15-Oct-17.
 */

public class AddMoneyEntity {
    String Email;
    String Ecash;

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getEcash() {
        return Ecash;
    }

    public void setEcash(String ecash) {
        Ecash = ecash;
    }

    public AddMoneyEntity() {

    }

    public AddMoneyEntity(String email, String ecash) {

        Email = email;
        Ecash = ecash;
    }

    public interface RestClientInterface{
        void onInitialize(int statusCode,String message, VolleyError error);
    }
    public JSONObject getJsonObjectAsParams()
    {
        JSONObject jsonObject=null;
        Gson gson=new Gson();
        String objectString=gson.toJson(this);
        if(jsonObject==null)
        {
            try {
                jsonObject=new JSONObject(objectString);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonObject;
    }
}
