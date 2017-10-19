package madmaxstudios.com.chintokaneventmanager.Entities;

import com.android.volley.VolleyError;

import java.util.List;

/**
 * Created by Prabhu Sivanandam on 16-Oct-17.
 */

public class CategoryMiniEntity {
    int statusCode;
    List<CategoryEntity> response;
    String message;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public List<CategoryEntity> getResponse() {
        return response;
    }

    public void setResponse(List<CategoryEntity> response) {
        this.response = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CategoryMiniEntity() {

    }

    public CategoryMiniEntity(int statusCode, List<CategoryEntity> response, String message) {

        this.statusCode = statusCode;
        this.response = response;
        this.message = message;
    }

    public interface RestClientInterface{
        void onInitialize(String resp,CategoryMiniEntity entity, VolleyError error);
    }
}
