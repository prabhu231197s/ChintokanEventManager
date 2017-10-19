package madmaxstudios.com.chintokaneventmanager.Entities;

import com.android.volley.VolleyError;

import java.util.List;

/**
 * Created by Prabhu Sivanandam on 16-Oct-17.
 */

public class DivisionsMiniEntity {
    int statusCode;
    String message;
    List<DivisionEntity> response;

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

    public List<DivisionEntity> getResponse() {
        return response;
    }

    public void setResponse(List<DivisionEntity> response) {
        this.response = response;
    }

    public DivisionsMiniEntity() {

    }

    public DivisionsMiniEntity(int statusCode, String message, List<DivisionEntity> response) {

        this.statusCode = statusCode;
        this.message = message;
        this.response = response;
    }

    public interface RestClientInterface{
        void onInitialize(String resp,DivisionsMiniEntity entity, VolleyError error);
    }
}
