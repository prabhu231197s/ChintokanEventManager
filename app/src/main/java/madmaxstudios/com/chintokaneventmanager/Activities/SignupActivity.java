package madmaxstudios.com.chintokaneventmanager.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.List;

import madmaxstudios.com.chintokaneventmanager.Entities.BeltNameEntity;
import madmaxstudios.com.chintokaneventmanager.Entities.BeltNameMiniEntity;
import madmaxstudios.com.chintokaneventmanager.Entities.DojoEntity;
import madmaxstudios.com.chintokaneventmanager.Entities.DojoNameEntity;
import madmaxstudios.com.chintokaneventmanager.Entities.DojoNameMiniEntity;
import madmaxstudios.com.chintokaneventmanager.Entities.LoginEntity;
import madmaxstudios.com.chintokaneventmanager.Entities.VerifyTokenEntity;
import madmaxstudios.com.chintokaneventmanager.R;
import madmaxstudios.com.chintokaneventmanager.RestClient.RestClientImplementation;

/**
 * Created by Prabhu Sivanandam on 14-Oct-17.
 */

public class SignupActivity extends AppCompatActivity {
    TextView tvSignUpButton;
    BeltNameMiniEntity miniEntity;
    List<BeltNameEntity> beltList;
    DojoNameMiniEntity dojoMini;
    List<DojoNameEntity> dojoList;
    List<String> finalBeltNameList;
    List<String> finalDojoNameList;
    ProgressDialog dialog;
    Spinner dojo,belt;
    EditText etName,etAge,etWeight,etPhone,etMail,etPassword;
    RadioGroup radioGroup;
    String Name,sAge,Mail,sWeight,Phone,Password,Gender,Belt,Dojo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        dialog = new ProgressDialog(this);
        dojo = (Spinner)findViewById(R.id.spinnerDojo);
        belt = (Spinner) findViewById(R.id.spinnerBelt);
        dialog.setMessage("Please Wait");
        dialog.setTitle("Fetching Details");
        dialog.show();
        radioGroup = (RadioGroup)findViewById(R.id.rgGender);
        tvSignUpButton = (TextView)findViewById(R.id.tvSignupButton);
        etName = (EditText)findViewById(R.id.etName);
        etAge = (EditText)findViewById(R.id.etAge);
        etMail = (EditText)findViewById(R.id.etEmail);
        etWeight = (EditText)findViewById(R.id.etWeight);
        etPhone = (EditText)findViewById(R.id.etPhone);
        etPassword = (EditText)findViewById(R.id.etPass);
        miniEntity = new BeltNameMiniEntity();
        beltList = new ArrayList<>();
        finalBeltNameList = new ArrayList<>();
        finalDojoNameList = new ArrayList<>();
        dojoMini = new DojoNameMiniEntity();
        dojoList = new ArrayList<>();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.radiofemale){
                    Gender = "Female";
                }
                else if(checkedId==R.id.radiomale){
                    Gender = "Male";
                }
            }
        });
        dojo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Dojo = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        belt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Belt = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        RestClientImplementation.getBeltNames(miniEntity,new BeltNameMiniEntity.ChintokanRestClientInterface(){
            @Override
            public void onInitialize(BeltNameMiniEntity entity, VolleyError error) {
                if(error==null){
                    beltList = entity.getResponse();
                    for(int i=0;i<beltList.size();i++){
                        BeltNameEntity entit = beltList.get(i);
                        finalBeltNameList.add(entit.getBelt());
                    }
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(SignupActivity.this,android.R.layout.simple_spinner_dropdown_item,finalBeltNameList);
                    belt.setAdapter(dataAdapter);
                }
                else{
                    Toast.makeText(SignupActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        },SignupActivity.this);
        RestClientImplementation.getDojoNames(dojoMini,new DojoNameMiniEntity.ChintokanRestClientInterface(){
            @Override
            public void onInitialize(DojoNameMiniEntity entity, VolleyError error) {
                if(error==null){
                    dojoList = entity.getResponse();
                    for(int i=0;i<dojoList.size();i++){
                        DojoNameEntity entit = dojoList.get(i);
                        finalDojoNameList.add(entit.getDojo());
                    }
                    ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(SignupActivity.this,android.R.layout.simple_spinner_dropdown_item,finalDojoNameList);
                    dojo.setAdapter(dataAdapter2);
                    dialog.dismiss();
                }
                else{
                    Toast.makeText(SignupActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }
            }
        },SignupActivity.this);
        final SharedPreferences preferences = getSharedPreferences("dojoList",MODE_PRIVATE);
        final SharedPreferences pref = getSharedPreferences("beltList",MODE_PRIVATE);
        Gender = "Female";
        tvSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Name = etName.getText().toString();
                sAge = etAge.getText().toString();
                Mail = etMail.getText().toString();
                sWeight = etWeight.getText().toString();
                Phone = etPhone.getText().toString();
                Password = etPassword.getText().toString();
                Belt = "Blue";
                Dojo = "xyz";
                if(Name.isEmpty()||Name.equals("")||sAge.isEmpty()||sAge.equals("")||Mail.isEmpty()||Mail.equals("")||sWeight.equals("")||sWeight.isEmpty()||Phone.isEmpty()||Phone.equals("")||Password.isEmpty()||Password.equals("")){
                    Toast.makeText(SignupActivity.this,"Fill all fields",Toast.LENGTH_LONG).show();
                }
                else{
                    dialog = new ProgressDialog(SignupActivity.this);
                    dialog.setMessage("Wait");
                    dialog.setTitle("SigningUp");
                    int Age = Integer.parseInt(sAge);
                    Long phone = Long.parseLong(Phone);
                    int Weigth = Integer.parseInt(sWeight);
                    LoginEntity entity = new LoginEntity();
                    entity.setName(Name);
                    entity.setAge(Age);
                    entity.setEmail(Mail);
                    entity.setWeight(Weigth);
                    entity.setPhoneNumber(phone);
                    entity.setPassword(Password);
                    entity.setGender(Gender);
                    String doj = preferences.getString(Dojo,"1");
                    entity.setDojo(Integer.parseInt(doj));
                    String bel = pref.getString(Belt,"1");
                    entity.setBelt(Integer.parseInt(bel));
                    entity.setBlockFlag(1);
                    dialog.show();
                    RestClientImplementation.signUp(entity,new LoginEntity.ChintokanRestClientInterface(){
                        @Override
                        public void onInitialize(int StatusCode, String message, VolleyError error) {
                            if(error==null){
                                if(StatusCode==200||StatusCode==304){
                                    dialog.dismiss();
                                    startActivity(new Intent(SignupActivity.this, VerificationActivity.class));
                                    Toast.makeText(SignupActivity.this,message,Toast.LENGTH_LONG).show();
                                }
                                else{
                                    dialog.dismiss();
                                    Toast.makeText(SignupActivity.this,message,Toast.LENGTH_LONG).show();
                                }
                            }
                            else{
                                dialog.dismiss();
                                Toast.makeText(SignupActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        }
                    },SignupActivity.this);
                }
            }
        });
    }
}
