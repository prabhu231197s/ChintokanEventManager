package madmaxstudios.com.chintokaneventmanager.Entities;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Prabhu Sivanandam on 14-Oct-17.
 */

public class LoginEntity {
    private String UserId;
    private String Name;
    private int Age;
    private int Weight;
    private String Email;
    private String Gender;
    private long PhoneNumber;
    private String Password;
    private int Dojo;
    private int Points;
    private int Belt;
    private int EventCount;
    private int MedalsWon;
    private int Admin;
    private int Role;
    private long Ecash;
    private int BlockFlag;
    private int LoggedIn;

    public LoginEntity() {
    }

    public LoginEntity(String userId, String name, int age, int weight, String email, long phoneNumber, String password, int dojo, int points, int belt, int eventCount, int medalsWon, int admin, int role, long ecash, int blockFlag, int loggedIn, String gender) {
        UserId = userId;
        Name = name;
        Age = age;
        Weight = weight;
        Email = email;
        PhoneNumber = phoneNumber;
        Password = password;
        Dojo = dojo;
        Points = points;
        Belt = belt;
        EventCount = eventCount;
        MedalsWon = medalsWon;
        Admin = admin;
        Role = role;
        Ecash = ecash;
        BlockFlag = blockFlag;
        LoggedIn = loggedIn;
        Gender = gender;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getUserId() {

        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public int getWeight() {
        return Weight;
    }

    public void setWeight(int weight) {
        Weight = weight;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public long getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getDojo() {
        return Dojo;
    }

    public void setDojo(int dojo) {
        Dojo = dojo;
    }

    public int getPoints() {
        return Points;
    }

    public void setPoints(int points) {
        Points = points;
    }

    public int getBelt() {
        return Belt;
    }

    public void setBelt(int belt) {
        Belt = belt;
    }

    public int getEventCount() {
        return EventCount;
    }

    public void setEventCount(int eventCount) {
        EventCount = eventCount;
    }

    public int getMedalsWon() {
        return MedalsWon;
    }

    public void setMedalsWon(int medalsWon) {
        MedalsWon = medalsWon;
    }

    public int getAdmin() {
        return Admin;
    }

    public void setAdmin(int admin) {
        Admin = admin;
    }

    public int getRole() {
        return Role;
    }

    public void setRole(int role) {
        Role = role;
    }

    public long getEcash() {
        return Ecash;
    }

    public void setEcash(long ecash) {
        Ecash = ecash;
    }

    public int getBlockFlag() {
        return BlockFlag;
    }

    public void setBlockFlag(int blockFlag) {
        BlockFlag = blockFlag;
    }

    public int getLoggedIn() {
        return LoggedIn;
    }

    public void setLoggedIn(int loggedIn) {
        LoggedIn = loggedIn;
    }

    public interface ChintokanRestClientInterface{
        public void onInitialize(int StatusCode,String message ,VolleyError error);
    }
    public JSONObject getJsonObjectAsParams()
    {
        JSONObject jsonObject=null;
        Gson gson=new Gson();
        String objectString=gson.toJson(this);
        if(jsonObject==null)
        {
            try {
                jsonObject=new JSONObject(objectString);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonObject;
    }
}
