package madmaxstudios.com.chintokaneventmanager.Entities;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Prabhu Sivanandam on 16-Oct-17.
 */

public class RegisterEventEntity {
    int UserId;
    int Division;
    int Category;
    String Event;

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public int getDivision() {
        return Division;
    }

    public void setDivision(int division) {
        Division = division;
    }

    public int getCategory() {
        return Category;
    }

    public void setCategory(int category) {
        Category = category;
    }

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }

    public RegisterEventEntity() {

    }

    public RegisterEventEntity(int userId, int division, int category, String event) {

        UserId = userId;
        Division = division;
        Category = category;
        Event = event;
    }
    public interface RestClientInterface{
        void onInitialize(int code,String message, VolleyError error);
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
