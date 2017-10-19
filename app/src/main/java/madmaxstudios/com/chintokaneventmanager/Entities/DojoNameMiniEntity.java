package madmaxstudios.com.chintokaneventmanager.Entities;

import com.android.volley.VolleyError;

import java.util.List;

/**
 * Created by Prabhu Sivanandam on 15-Oct-17.
 */

public class DojoNameMiniEntity {
    int statusCode;
    List<DojoNameEntity> response;
    String message;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DojoNameMiniEntity() {

    }

    public List<DojoNameEntity> getResponse() {
        return response;
    }

    public void setResponse(List<DojoNameEntity> response) {
        this.response = response;
    }

    public DojoNameMiniEntity(int statusCode, List<DojoNameEntity> response, String message) {

        this.statusCode = statusCode;
        this.response = response;
        this.message = message;
    }

    public interface ChintokanRestClientInterface{
        public void onInitialize(DojoNameMiniEntity entity, VolleyError error);
    }

}
