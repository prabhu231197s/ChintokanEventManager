package madmaxstudios.com.chintokaneventmanager.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import madmaxstudios.com.chintokaneventmanager.Activities.SignupActivity;
import madmaxstudios.com.chintokaneventmanager.Entities.CategoryEntity;
import madmaxstudios.com.chintokaneventmanager.Entities.CategoryMiniEntity;
import madmaxstudios.com.chintokaneventmanager.Entities.DivisionEntity;
import madmaxstudios.com.chintokaneventmanager.Entities.DivisionsMiniEntity;
import madmaxstudios.com.chintokaneventmanager.Entities.RegisterEventEntity;
import madmaxstudios.com.chintokaneventmanager.R;
import madmaxstudios.com.chintokaneventmanager.RestClient.RestClientImplementation;

/**
 * Created by Prabhu Sivanandam on 16-Oct-17.
 */

public class RegisterEventFragment extends Fragment {

    RadioGroup gr;
    Context context;
    RelativeLayout getEvents;
    Spinner divisionSpinner,categorySpinner;
    List<DivisionEntity> dList;
    List<CategoryEntity> cList;
    List<String> categoryList;
    List<String> divisionList;
    DivisionEntity division;
    CategoryEntity categoryEntity;
    SharedPreferences pref;
    DivisionsMiniEntity divisionsMiniEntity;
    ProgressDialog dialog;
    CategoryMiniEntity categoryMiniEntity;
    String Division,Category,Event;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.registerevent,container,false);
        dList = new ArrayList<>();
        Division = "Open";
        Category = "Open";
        Event = "Kata";
        division = new DivisionEntity();
        categoryEntity = new CategoryEntity();
        getEvents = (RelativeLayout)v.findViewById(R.id.getEventsButton);
        cList = new ArrayList<>();
        dialog = new ProgressDialog(getContext());
        dialog.setMessage("Please Wait");
        dialog.setTitle("Fetching List");
        dialog.show();
        divisionList = new ArrayList<>();
        categoryList = new ArrayList<>();
        divisionsMiniEntity = new DivisionsMiniEntity();
        categoryMiniEntity = new CategoryMiniEntity();
        gr = (RadioGroup)v.findViewById(R.id.rgEvent);
        divisionSpinner = (Spinner)v.findViewById(R.id.spinnerDivision);
        categorySpinner = (Spinner)v.findViewById(R.id.spinnerCategory);
        final SharedPreferences preferences = getActivity().getSharedPreferences("categories",Context.MODE_PRIVATE);
        String list = preferences.getString("list",null);
        gr.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.radioKata){
                    Event = "Kata";
                }
                else if(checkedId == R.id.radioKumite){
                    Event = "Kumite";
                }
            }
        });
        divisionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Division = parent.getItemAtPosition(position).toString();
                Toast.makeText(getContext(),Division,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Category = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        RestClientImplementation.getDivisions(divisionsMiniEntity,new DivisionsMiniEntity.RestClientInterface(){
            @Override
            public void onInitialize(String resp,DivisionsMiniEntity entity, VolleyError error) {
                if(error==null){
                    dList = entity.getResponse();
                    for(DivisionEntity item:dList){
                        divisionList.add(item.getName());
                    }
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,divisionList);
                    divisionSpinner.setAdapter(dataAdapter);
                }
                else{
                    Toast.makeText(context,"Something went wrong",Toast.LENGTH_LONG).show();
                }
            }
        },context);

        RestClientImplementation.getCategories(categoryMiniEntity,new CategoryMiniEntity.RestClientInterface(){
            @Override
            public void onInitialize(String resp,CategoryMiniEntity entity, VolleyError error) {
                dialog.dismiss();
                if(error==null){
                    cList = entity.getResponse();
                    for(CategoryEntity item:cList){
                        categoryList.add(item.getCategory());
                    }
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,categoryList);
                    categorySpinner.setAdapter(dataAdapter);
                }
                else {
                    Toast.makeText(context,error.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        },context);

        getEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setTitle("Registering");
                dialog.show();
                pref = getActivity().getSharedPreferences("divisions",Context.MODE_PRIVATE);
                int dcode = pref.getInt(Division,1);
                pref = getActivity().getSharedPreferences("categories",Context.MODE_PRIVATE);
                int ccode = pref.getInt(Category,8);
                pref = getActivity().getSharedPreferences("userDetails",Context.MODE_PRIVATE);
                int id = pref.getInt("id",0);
                RegisterEventEntity entity = new RegisterEventEntity(id,dcode,ccode,Event);
                RestClientImplementation.registerEvent(entity,new RegisterEventEntity.RestClientInterface(){
                    @Override
                    public void onInitialize(int code, String message, VolleyError error) {
                        if(code==200){
                            dialog.dismiss();
                            Toast.makeText(getContext(),"Success",Toast.LENGTH_LONG).show();
                        }
                        else{
                            dialog.dismiss();
                            Toast.makeText(getContext(),message,Toast.LENGTH_LONG).show();
                        }
                    }
                },getContext());
            }
        });

        return v;
    }
}
