package madmaxstudios.com.chintokaneventmanager.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.VolleyError;

import madmaxstudios.com.chintokaneventmanager.Entities.AddMoneyEntity;
import madmaxstudios.com.chintokaneventmanager.R;
import madmaxstudios.com.chintokaneventmanager.RestClient.RestClientImplementation;

/**
 * Created by Prabhu Sivanandam on 15-Oct-17.
 */

public class AddMoneyFragment extends Fragment {

    RelativeLayout layout;
    SharedPreferences preferences;
    String amount,camount;
    AddMoneyEntity entity;
    ProgressDialog dialog;
    EditText etTextAmount,etConfAmount;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_money,container,false);
        layout = (RelativeLayout)v.findViewById(R.id.addMoneyButton);
        etTextAmount = (EditText)v.findViewById(R.id.etAmount);
        preferences = getActivity().getSharedPreferences("userDetails", Context.MODE_PRIVATE);
        final String email = preferences.getString("mail","prabhu231197@gmail.com");
        entity = new AddMoneyEntity();
        dialog = new ProgressDialog(getContext());
        dialog.setMessage("Wait");
        dialog.setCancelable(false);
        dialog.setTitle("Processing");
        etConfAmount = (EditText)v.findViewById(R.id.etCAmount);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount = etTextAmount.getText().toString();
                camount = etConfAmount.getText().toString();
                if(amount.equals("")||amount.trim().equals("")||amount.isEmpty()||camount.equals("")||camount.trim().equals("")||camount.isEmpty()){
                    Toast.makeText(getContext(),"Fill all Fields",Toast.LENGTH_LONG).show();
                }
                else{
                    entity.setEmail(email);
                    entity.setEcash(amount);
                    dialog.show();
                    RestClientImplementation.AddMoney(entity,new AddMoneyEntity.RestClientInterface(){
                        @Override
                        public void onInitialize(int statusCode, String message, VolleyError error) {
                            if(error==null){
                                etConfAmount.setText("");
                                etTextAmount.setText("");
                                dialog.dismiss();
                                Toast.makeText(getContext(),message,Toast.LENGTH_LONG).show();
                            }
                            else{
                                dialog.dismiss();
                                Toast.makeText(getContext(),message,Toast.LENGTH_LONG).show();
                            }
                        }
                    },getContext());
                }
            }
        });
        return v;
    }
}
