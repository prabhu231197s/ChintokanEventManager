package madmaxstudios.com.chintokaneventmanager.Entities;

/**
 * Created by Prabhu Sivanandam on 16-Oct-17.
 */

public class DivisionEntity {
    int DivisionId;
    String Name;

    public int getDivisionId() {
        return DivisionId;
    }

    public void setDivisionId(int divisionId) {
        DivisionId = divisionId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public DivisionEntity() {

    }

    public DivisionEntity(int divisionId, String name) {

        DivisionId = divisionId;
        Name = name;
    }
}
