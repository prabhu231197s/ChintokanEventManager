package madmaxstudios.com.chintokaneventmanager.Entities;

import com.android.volley.VolleyError;

/**
 * Created by Prabhu Sivanandam on 15-Oct-17.
 */

public class DojoEntity {
    int Id;
    String Dojo;
    String City;

    public DojoEntity(int dojoId, String dojo, String city) {
        Id = dojoId;
        Dojo = dojo;
        City = city;
    }

    public DojoEntity() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getDojo() {
        return Dojo;
    }

    public void setDojo(String dojo) {
        Dojo = dojo;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public interface ChintokanRestClientInterface{
        public void onInitialize(DojoEntity entit, VolleyError error);
    }

}
