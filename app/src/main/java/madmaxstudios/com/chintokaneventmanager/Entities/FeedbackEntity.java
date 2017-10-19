package madmaxstudios.com.chintokaneventmanager.Entities;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Prabhu Sivanandam on 17-Oct-17.
 */

public class FeedbackEntity {
    int UserId;
    String Feedback;

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getFeedback() {
        return Feedback;
    }

    public void setFeedback(String feedback) {
        Feedback = feedback;
    }

    public FeedbackEntity() {

    }

    public FeedbackEntity(int userId, String feedback) {

        UserId = userId;
        Feedback = feedback;
    }

    public interface RestClientInterface{
        void onInitialize(int code, String message, VolleyError error);
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
