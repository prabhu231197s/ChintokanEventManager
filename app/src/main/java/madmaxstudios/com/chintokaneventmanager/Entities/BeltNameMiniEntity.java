package madmaxstudios.com.chintokaneventmanager.Entities;

import com.android.volley.VolleyError;

import java.util.List;

/**
 * Created by Prabhu Sivanandam on 15-Oct-17.
 */

public class BeltNameMiniEntity {
    int statusCode;
    List<BeltNameEntity> response;
    String message;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public List<BeltNameEntity> getResponse() {
        return response;
    }

    public void setResponse(List<BeltNameEntity> response) {
        this.response = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BeltNameMiniEntity() {

    }

    public BeltNameMiniEntity(int statusCode, List<BeltNameEntity> response, String message) {

        this.statusCode = statusCode;
        this.response = response;
        this.message = message;
    }

    public interface ChintokanRestClientInterface{
        public void onInitialize(BeltNameMiniEntity entity, VolleyError error);
    }

}
