package madmaxstudios.com.chintokaneventmanager.Entities;

import com.android.volley.VolleyError;

/**
 * Created by Prabhu Sivanandam on 15-Oct-17.
 */

public class LogoutEntity {
    int statusCode;
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

    public LogoutEntity() {

    }

    public LogoutEntity(int statusCode, String message) {

        this.statusCode = statusCode;
        this.message = message;
    }
    public interface ChintokanRestClientInterface{
         void onInitialize(LogoutEntity entity, VolleyError error);
    }
}
