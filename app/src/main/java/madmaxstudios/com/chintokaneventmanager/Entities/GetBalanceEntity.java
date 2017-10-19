package madmaxstudios.com.chintokaneventmanager.Entities;

import com.android.volley.VolleyError;

/**
 * Created by Prabhu Sivanandam on 16-Oct-17.
 */

public class GetBalanceEntity {
    int statusCode;
    int response;
    String message;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getResponse() {
        return response;
    }

    public void setResponse(int response) {
        this.response = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public GetBalanceEntity() {

    }

    public GetBalanceEntity(int statusCode, int response, String message) {

        this.statusCode = statusCode;
        this.response = response;
        this.message = message;
    }
    public interface RestClientInterface{
        void onInitialize(int statusCode, String message, Long balance, VolleyError error);
    }
}
