package madmaxstudios.com.chintokaneventmanager.Entities;

import com.android.volley.VolleyError;

import java.util.List;

/**
 * Created by Prabhu Sivanandam on 15-Oct-17.
 */

public class DojoMiniEntity {
    int statusCode;
    List<DojoEntity> response;
    String message;

    public DojoMiniEntity() {
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public List<DojoEntity> getResponse() {
        return response;
    }

    public void setResponse(List<DojoEntity> response) {
        this.response = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DojoMiniEntity(int statusCode, List<DojoEntity> response, String message) {

        this.statusCode = statusCode;
        this.response = response;
        this.message = message;
    }
    public interface ChintokanRestClientInterface{
        public void onInitialize(DojoMiniEntity entit, VolleyError error);
    }
}
