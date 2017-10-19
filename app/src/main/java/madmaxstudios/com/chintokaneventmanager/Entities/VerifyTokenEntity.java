package madmaxstudios.com.chintokaneventmanager.Entities;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Prabhu Sivanandam on 15-Oct-17.
 */

public class VerifyTokenEntity {
    String email;
    String token;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public VerifyTokenEntity() {

    }

    public VerifyTokenEntity(String email, String token) {

        this.email = email;
        this.token = token;
    }
    public interface ChintokanRestClientInterface{
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
