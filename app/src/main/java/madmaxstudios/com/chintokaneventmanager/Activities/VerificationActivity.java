package madmaxstudios.com.chintokaneventmanager.Activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;

import madmaxstudios.com.chintokaneventmanager.Entities.VerifyTokenEntity;
import madmaxstudios.com.chintokaneventmanager.R;
import madmaxstudios.com.chintokaneventmanager.RestClient.RestClientImplementation;

/**
 * Created by Prabhu Sivanandam on 15-Oct-17.
 */

public class VerificationActivity extends AppCompatActivity {
    EditText etEmail,etToken;
    TextView tvVerifyButton;
    ProgressDialog dialog;
    VerifyTokenEntity entity;
    String email,token;
    RelativeLayout layout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verifytoken);
        etEmail = (EditText)findViewById(R.id.etemail);
        etToken = (EditText)findViewById(R.id.etToken);
        layout = (RelativeLayout)findViewById(R.id.verifyTokenButton);
        tvVerifyButton = (TextView)findViewById(R.id.tvVerifyButton);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait");
        dialog.setTitle("Verifying");
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                email = etEmail.getText().toString();
                token = etToken.getText().toString();
                if(email.isEmpty()||email.trim().equals("")||email.equals("")||token.isEmpty()||token.trim().equals("")||token.equals("")){
                    Toast.makeText(VerificationActivity.this,"Fill all fields",Toast.LENGTH_LONG).show();
                }
                else{
                    entity = new VerifyTokenEntity();
                    entity.setEmail(email);
                    entity.setToken(token);
                    RestClientImplementation.VerifyToken(entity,new VerifyTokenEntity.ChintokanRestClientInterface(){
                        @Override
                        public void onInitialize(int statusCode, String message, VolleyError error) {
                            if(error==null){
                                if(statusCode == 200||statusCode==304){
                                    dialog.dismiss();
                                    Toast.makeText(VerificationActivity.this,"Success",Toast.LENGTH_LONG).show();
                                    finish();
                                    startActivity(new Intent(VerificationActivity.this,LoginActivity.class));
                                }
                                else{
                                    Toast.makeText(VerificationActivity.this,message,Toast.LENGTH_LONG).show();
                                    dialog.dismiss();
                                }
                            }
                            else{
                                Toast.makeText(VerificationActivity.this,message,Toast.LENGTH_LONG).show();
                                dialog.dismiss();
                            }
                        }
                    },VerificationActivity.this);
                }
            }
        });
    }
}
