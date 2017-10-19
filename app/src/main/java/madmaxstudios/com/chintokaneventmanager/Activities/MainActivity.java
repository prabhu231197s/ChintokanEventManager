package madmaxstudios.com.chintokaneventmanager.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import madmaxstudios.com.chintokaneventmanager.Entities.BeltEntity;
import madmaxstudios.com.chintokaneventmanager.Entities.BeltMiniEntity;
import madmaxstudios.com.chintokaneventmanager.Entities.CategoryEntity;
import madmaxstudios.com.chintokaneventmanager.Entities.CategoryMiniEntity;
import madmaxstudios.com.chintokaneventmanager.Entities.DivisionEntity;
import madmaxstudios.com.chintokaneventmanager.Entities.DivisionsMiniEntity;
import madmaxstudios.com.chintokaneventmanager.Entities.DojoEntity;
import madmaxstudios.com.chintokaneventmanager.Entities.DojoMiniEntity;
import madmaxstudios.com.chintokaneventmanager.Entities.GetBalanceEntity;
import madmaxstudios.com.chintokaneventmanager.R;
import madmaxstudios.com.chintokaneventmanager.RestClient.RestClientImplementation;

public class MainActivity extends AppCompatActivity {

    List<DojoEntity> list;
    List<DivisionEntity> divisionEntities;
    List<CategoryEntity> categoryEntities;
    SharedPreferences categoriesPref,divisionsPref;
    SharedPreferences.Editor ca;
    SharedPreferences.Editor pe;
    SharedPreferences.Editor di;
    List<BeltEntity> beltList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DojoMiniEntity entity = new DojoMiniEntity();
        final Context context = MainActivity.this;
        final SharedPreferences p = getSharedPreferences("userDetails",MODE_PRIVATE);
        list = new ArrayList<>();
        beltList = new ArrayList<>();
        SharedPreferences dojoList = getSharedPreferences("dojoList",MODE_PRIVATE);
        final SharedPreferences.Editor editor = dojoList.edit();
        RestClientImplementation.getDojoDetails(entity,new DojoMiniEntity.ChintokanRestClientInterface(){
            @Override
            public void onInitialize(DojoMiniEntity entit, VolleyError error) {
                if(entit!=null){
                    Log.d("tag",entit.getResponse().get(0).getId()+"");
                    if(entit.getStatusCode()==200||entit.getStatusCode()==304){
                        list = entit.getResponse();
                        for (DojoEntity entity:list){
                            editor.putString(String.valueOf(entity.getDojo()), String.valueOf(entity.getId()));
                        }
                        editor.commit();
                    }
                    else{
                        Toast.makeText(MainActivity.this,entit.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(MainActivity.this,"Something went wrong",Toast.LENGTH_LONG).show();
                }
            }
        },MainActivity.this);
        BeltMiniEntity miniEntity = new BeltMiniEntity();
        SharedPreferences pref = getSharedPreferences("beltList",MODE_PRIVATE);
        final SharedPreferences.Editor edit = pref.edit();
        RestClientImplementation.getBeltDetails(miniEntity,new BeltMiniEntity.ChintokanRestClientInterface(){
            @Override
            public void onInitialize(BeltMiniEntity entit, VolleyError error) {
                if(entit!=null){
                    if(entit.getStatusCode()==200||entit.getStatusCode()==304){
                        beltList = entit.getResponse();
                        editor.putString("beltList",beltList.toString());
                        for (BeltEntity entity:beltList){
                            editor.putString(String.valueOf(entity.getBelt()), String.valueOf(entity.getBeltId()));
                        }
                        editor.commit();
                    }
                    else{
                        Toast.makeText(MainActivity.this,entit.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(MainActivity.this,"Something went wrong",Toast.LENGTH_LONG).show();
                }
            }
        },MainActivity.this);

        categoriesPref = getSharedPreferences("categories",MODE_PRIVATE);
        ca = categoriesPref.edit();
        divisionsPref = getSharedPreferences("divisions",MODE_PRIVATE);
        di = divisionsPref.edit();
        CategoryMiniEntity categoryMiniEntity = new CategoryMiniEntity();
        DivisionsMiniEntity divisionsMiniEntity = new DivisionsMiniEntity();
        RestClientImplementation.getCategories(categoryMiniEntity,new CategoryMiniEntity.RestClientInterface(){
            @Override
            public void onInitialize(String resp,CategoryMiniEntity entity, VolleyError error) {
                if(error==null){
                    ca.putString("list",resp);
                    categoryEntities = entity.getResponse();
                    for(CategoryEntity item:categoryEntities){
                        ca.putInt(item.getCategory(),item.getCategoryId());
                    }
                    ca.commit();
                }
                else {
                    Toast.makeText(context,error.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        },context);

        RestClientImplementation.getDivisions(divisionsMiniEntity,new DivisionsMiniEntity.RestClientInterface(){
            @Override
            public void onInitialize(String resp,DivisionsMiniEntity entity, VolleyError error) {
                if(error==null){
                    di.putString("list",resp);
                    divisionEntities = entity.getResponse();
                    for(DivisionEntity item:divisionEntities){
                        di.putInt(item.getName(),item.getDivisionId());
                    }
                    di.commit();
                }
                else{
                    Toast.makeText(context,error.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        },context);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                String name = p.getString("status","");
                int stat = p.getInt("id",0);
                if(stat==0){
                    Intent i = new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(i);
                }
                else{
                    RestClientImplementation.getBalance(stat,new GetBalanceEntity(),new GetBalanceEntity.RestClientInterface(){
                        @Override
                        public void onInitialize(int statusCode, String message, Long balance, VolleyError error) {
                            if(statusCode==200||statusCode==304){
                                pe = p.edit();
                                pe.putLong("cash",balance);
                                pe.commit();
                                Intent i = new Intent(MainActivity.this,Homescreen.class);
                                startActivity(i);
                            }
                            else{
                                Toast.makeText(context,message,Toast.LENGTH_LONG).show();
                            }
                        }
                    },context);
                }
            }
        },5000);
    }
}
