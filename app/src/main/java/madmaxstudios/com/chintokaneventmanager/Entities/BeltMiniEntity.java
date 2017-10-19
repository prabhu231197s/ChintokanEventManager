package madmaxstudios.com.chintokaneventmanager.Entities;

import com.android.volley.VolleyError;

import java.util.List;

/**
 * Created by Prabhu Sivanandam on 15-Oct-17.
 */

public class BeltMiniEntity {
    int statusCode;
    List<BeltEntity> response;
    String message;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public List<BeltEntity> getResponse() {
        return response;
    }

    public void setResponse(List<BeltEntity> response) {
        this.response = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BeltMiniEntity() {

    }

    public BeltMiniEntity(int statusCode, List<BeltEntity> response, String message) {

        this.statusCode = statusCode;
        this.response = response;
        this.message = message;
    }

    public interface ChintokanRestClientInterface{
        public void onInitialize(BeltMiniEntity beltMiniEntity, VolleyError error);
    }

}
