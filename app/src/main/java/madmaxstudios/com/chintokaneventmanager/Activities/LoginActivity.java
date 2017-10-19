package madmaxstudios.com.chintokaneventmanager.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;

import madmaxstudios.com.chintokaneventmanager.Entities.LoginEntity;
import madmaxstudios.com.chintokaneventmanager.Entities.LoginMiniEntity;
import madmaxstudios.com.chintokaneventmanager.R;
import madmaxstudios.com.chintokaneventmanager.RestClient.RestClientImplementation;

/**
 * Created by Prabhu Sivanandam on 14-Jun-17.
 */

public class LoginActivity extends AppCompatActivity {
    TextView tvLogin;
    TextView tvSignup;
    EditText etUserId,etPassword;
    LoginEntity user;
    SharedPreferences pref;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        pref= getSharedPreferences("userDetails",MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();
        tvLogin=(TextView)findViewById(R.id.tvLogin);
        etUserId=(EditText)findViewById(R.id.etUserId);
        tvSignup=(TextView)findViewById(R.id.tvSignup);
        etPassword=(EditText)findViewById(R.id.etPassword);
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName;
                String password = etPassword.getText().toString();
                if(etUserId.toString().equals("")||etUserId.toString().isEmpty()){
                    Toast.makeText(LoginActivity.this,"Enter ID",Toast.LENGTH_LONG).show();
                }
                else{
                    userName = etUserId.getText().toString();
                    if(password.isEmpty()||password.equals("")){
                        Toast.makeText(LoginActivity.this,"Enter Password",Toast.LENGTH_LONG).show();
                    }
                    else{
                        user = new LoginEntity();
                        user.setUserId(userName);
                        user.setPassword(password);
                        LoginMiniEntity entity = new LoginMiniEntity();
                        entity.setResponse(user);
                        RestClientImplementation.Login(entity,new LoginMiniEntity.ChintokanRestClientInterface(){
                            @Override
                            public void onInitialize(LoginMiniEntity loginMiniEntity, VolleyError error) {
                                if(error == null){
                                    if(loginMiniEntity.getResponse() != null){
                                        user = loginMiniEntity.getResponse();
                                        editor.putString("mail",user.getEmail());
                                        editor.putInt("id", Integer.parseInt(user.getUserId()));
                                        editor.putString("name",user.getName());
                                        editor.putLong("cash",user.getEcash());
                                        editor.putString("weigth",String.valueOf(user.getWeight()));
                                        editor.putString("age",String.valueOf(user.getAge()));
                                        editor.putString("status",String.valueOf(user.getLoggedIn()));
                                        editor.commit();
                                        Toast.makeText(LoginActivity.this,"success",Toast.LENGTH_LONG).show();
                                        finish();
                                        startActivity(new Intent(LoginActivity.this,Homescreen.class));
                                    }
                                    else{
                                        Toast.makeText(LoginActivity.this,"Something",Toast.LENGTH_LONG).show();
                                    }
                                }
                                else{
                                    Toast.makeText(LoginActivity.this,loginMiniEntity.getMessage(),Toast.LENGTH_LONG).show();
                                }
                            }
                        },LoginActivity.this);
                    }
                }
            }
        });
        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(i);
            }
        });
    }
}
