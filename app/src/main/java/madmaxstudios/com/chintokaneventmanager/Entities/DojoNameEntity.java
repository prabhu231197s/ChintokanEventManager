package madmaxstudios.com.chintokaneventmanager.Entities;

/**
 * Created by Prabhu Sivanandam on 15-Oct-17.
 */

public class DojoNameEntity {
    String Dojo;

    public DojoNameEntity() {
    }

    public DojoNameEntity(String dojo) {

        Dojo = dojo;
    }

    public String getDojo() {
        return Dojo;
    }

    public void setDojo(String dojo) {
        Dojo = dojo;
    }
}
