package madmaxstudios.com.chintokaneventmanager.Entities;

/**
 * Created by Prabhu Sivanandam on 15-Oct-17.
 */

public class BeltEntity {
    int BeltId;
    String Belt;

    public BeltEntity() {
    }

    public int getBeltId() {
        return BeltId;
    }

    public void setBeltId(int beltId) {
        BeltId = beltId;
    }

    public String getBelt() {
        return Belt;
    }

    public void setBelt(String belt) {
        Belt = belt;
    }

    public BeltEntity(int beltId, String belt) {
        BeltId = beltId;
        Belt = belt;
    }
}
