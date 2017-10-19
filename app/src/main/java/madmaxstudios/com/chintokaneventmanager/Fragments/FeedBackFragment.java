package madmaxstudios.com.chintokaneventmanager.Fragments;

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

import madmaxstudios.com.chintokaneventmanager.Entities.FeedbackEntity;
import madmaxstudios.com.chintokaneventmanager.R;
import madmaxstudios.com.chintokaneventmanager.RestClient.RestClientImplementation;

/**
 * Created by Prabhu Sivanandam on 17-Oct-17.
 */

public class FeedBackFragment extends Fragment {

    String feedback;
    SharedPreferences pref;
    EditText fb;
    RelativeLayout submit;
    int id;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.feedback,container,false);
        fb = (EditText)v.findViewById(R.id.etFeedback);
        pref = getActivity().getSharedPreferences("userDetails", Context.MODE_PRIVATE);
        id = pref.getInt("id",4);
        submit = (RelativeLayout)v.findViewById(R.id.submitFeedback);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedback = fb.getText().toString();
                if(feedback.equals("")||feedback.trim().equals("")||feedback.isEmpty()){
                    Toast.makeText(getContext(),"Please fill",Toast.LENGTH_LONG).show();
                }
                else{
                    FeedbackEntity entity = new FeedbackEntity(id,feedback);
                    RestClientImplementation.postFeedback(entity,new FeedbackEntity.RestClientInterface(){
                        @Override
                        public void onInitialize(int code, String message, VolleyError error) {
                            if(error==null){
                                fb.setText("");
                                Toast.makeText(getContext(),"Success",Toast.LENGTH_LONG).show();
                            }
                            else{
                                Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        }
                    },getContext());
                }
            }
        });
        return v;
    }
}
