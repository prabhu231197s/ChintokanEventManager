package madmaxstudios.com.chintokaneventmanager.Entities;

import com.android.volley.VolleyError;

/**
 * Created by Prabhu Sivanandam on 14-Oct-17.
 */

public class LoginMiniEntity {
    private int statusCode;
    private LoginEntity response;
    private String message;

    public LoginMiniEntity() {

    }

    public LoginMiniEntity(int statusCode, LoginEntity response, String message) {
        this.statusCode = statusCode;
        this.response = response;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public LoginEntity getResponse() {
        return response;
    }

    public void setResponse(LoginEntity response) {
        this.response = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public interface ChintokanRestClientInterface{
        void onInitialize(LoginMiniEntity loginMiniEntity, VolleyError error);
    }

}
