package madmaxstudios.com.chintokaneventmanager.RestClient;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

import madmaxstudios.com.chintokaneventmanager.Activities.Homescreen;
import madmaxstudios.com.chintokaneventmanager.Constants.Constants;
import madmaxstudios.com.chintokaneventmanager.Entities.AddMoneyEntity;
import madmaxstudios.com.chintokaneventmanager.Entities.BeltMiniEntity;
import madmaxstudios.com.chintokaneventmanager.Entities.BeltNameMiniEntity;
import madmaxstudios.com.chintokaneventmanager.Entities.CategoryMiniEntity;
import madmaxstudios.com.chintokaneventmanager.Entities.DivisionsMiniEntity;
import madmaxstudios.com.chintokaneventmanager.Entities.DojoEntity;
import madmaxstudios.com.chintokaneventmanager.Entities.DojoMiniEntity;
import madmaxstudios.com.chintokaneventmanager.Entities.DojoNameMiniEntity;
import madmaxstudios.com.chintokaneventmanager.Entities.FeedbackEntity;
import madmaxstudios.com.chintokaneventmanager.Entities.GetBalanceEntity;
import madmaxstudios.com.chintokaneventmanager.Entities.LoginEntity;
import madmaxstudios.com.chintokaneventmanager.Entities.LoginMiniEntity;
import madmaxstudios.com.chintokaneventmanager.Entities.LogoutEntity;
import madmaxstudios.com.chintokaneventmanager.Entities.RegisterEventEntity;
import madmaxstudios.com.chintokaneventmanager.Entities.VerifyTokenEntity;

/**
 * Created by Prabhu Sivanandam on 14-Oct-17.
 */

public class RestClientImplementation {
    static RequestQueue queue;
    public static String BaseUrl = Constants.baseUrl;

    public static String getAbsoluteUrl(String Url){
        String url=null;
        Url = BaseUrl+Url;
        return Url;
    }

    public static void Login(final LoginMiniEntity loginMiniEntity, final LoginMiniEntity.ChintokanRestClientInterface restClientInterface, final Context context){
        queue = VolleySingleton.getInstance(context).getRequestQueue();
        String loginUrl = Constants.loginUrl;
        loginUrl=getAbsoluteUrl(loginUrl);
        String userId = loginMiniEntity.getResponse().getUserId();
        String password = loginMiniEntity.getResponse().getPassword();
        loginUrl+="?email="+userId+"&password="+password;
        Log.d("conso:",loginUrl);
        JsonBaseRequest request = new JsonBaseRequest(Request.Method.GET, loginUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("response:", String.valueOf(response));
                try{
                    Gson gson = new Gson();
                    LoginMiniEntity newEntity = gson.fromJson(response.toString(),LoginMiniEntity.class);
                    loginMiniEntity.setMessage(newEntity.getMessage());
                    loginMiniEntity.setStatusCode(newEntity.getStatusCode());
                    loginMiniEntity.setResponse(newEntity.getResponse());
                    restClientInterface.onInitialize(loginMiniEntity,null);
                }
                catch(Exception e){
                    restClientInterface.onInitialize(loginMiniEntity,new VolleyError());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loginMiniEntity.setMessage("User name password does not match");
                restClientInterface.onInitialize(loginMiniEntity,new VolleyError());
            }
        },30000,0);
        queue.add(request);
    }

    public static void getDojoDetails(final DojoMiniEntity dojoList, final DojoMiniEntity.ChintokanRestClientInterface restClientInterface, final Context context) {
        queue = VolleySingleton.getInstance(context).getRequestQueue();
        String Url = Constants.getDojoUrl;
        Url = getAbsoluteUrl(Url);
        JsonBaseRequest request = new JsonBaseRequest(Request.Method.GET, Url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    Gson gson = new Gson();
                    DojoMiniEntity n = gson.fromJson(response.toString(),DojoMiniEntity.class);
                    dojoList.setStatusCode(n.getStatusCode());
                    dojoList.setResponse(n.getResponse());
                    dojoList.setMessage(n.getMessage());
                    restClientInterface.onInitialize(dojoList,null);
                }
                catch(Exception e){
                    restClientInterface.onInitialize(dojoList,new VolleyError());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dojoList.setMessage("Something went wrong");
                restClientInterface.onInitialize(dojoList,new VolleyError());
            }
        },30000,0);
        queue.add(request);
    }

    public static void getBeltDetails(final BeltMiniEntity beltList, final BeltMiniEntity.ChintokanRestClientInterface restClientInterface, final Context context) {
        queue = VolleySingleton.getInstance(context).getRequestQueue();
        String Url = Constants.getBeltUrl;
        Url = getAbsoluteUrl(Url);
        JsonBaseRequest request = new JsonBaseRequest(Request.Method.GET, Url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    Gson gson = new Gson();
                    BeltMiniEntity n = gson.fromJson(response.toString(),BeltMiniEntity.class);
                    beltList.setStatusCode(n.getStatusCode());
                    beltList.setResponse(n.getResponse());
                    beltList.setMessage(n.getMessage());
                    restClientInterface.onInitialize(beltList,null);
                }
                catch(Exception e){
                    restClientInterface.onInitialize(beltList,new VolleyError());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                beltList.setMessage("Something went wrong");
                restClientInterface.onInitialize(beltList,new VolleyError());
            }
        },30000,0);
        queue.add(request);
    }

    public static void getBeltNames(final BeltNameMiniEntity beltList, final BeltNameMiniEntity.ChintokanRestClientInterface restClientInterface, final Context context) {
        queue = VolleySingleton.getInstance(context).getRequestQueue();
        String Url = Constants.getBeltNameUrl;
        Url = getAbsoluteUrl(Url);
        JsonBaseRequest request = new JsonBaseRequest(Request.Method.GET, Url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    Gson gson = new Gson();
                    BeltNameMiniEntity n = gson.fromJson(response.toString(),BeltNameMiniEntity.class);
                    beltList.setStatusCode(n.getStatusCode());
                    beltList.setResponse(n.getResponse());
                    beltList.setMessage(n.getMessage());
                    restClientInterface.onInitialize(beltList,null);
                }
                catch(Exception e){
                    restClientInterface.onInitialize(beltList,new VolleyError());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                beltList.setMessage("Something went wrong");
                restClientInterface.onInitialize(beltList,new VolleyError());
            }
        },30000,3);
        queue.add(request);
    }


    public static void getDojoNames(final DojoNameMiniEntity beltList, final DojoNameMiniEntity.ChintokanRestClientInterface restClientInterface, final Context context) {
        queue = VolleySingleton.getInstance(context).getRequestQueue();
        String Url = Constants.getDojoNameUrl;
        Url = getAbsoluteUrl(Url);
        JsonBaseRequest request = new JsonBaseRequest(Request.Method.GET, Url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    Gson gson = new Gson();
                    DojoNameMiniEntity n = gson.fromJson(response.toString(),DojoNameMiniEntity.class);
                    beltList.setStatusCode(n.getStatusCode());
                    beltList.setResponse(n.getResponse());
                    beltList.setMessage(n.getMessage());
                    restClientInterface.onInitialize(beltList,null);
                }
                catch(Exception e){
                    restClientInterface.onInitialize(beltList,new VolleyError());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                restClientInterface.onInitialize(beltList,new VolleyError());
            }
        },30000,3);
        queue.add(request);
    }

    public static void signUp(final LoginEntity user, final LoginEntity.ChintokanRestClientInterface restClientInterface, final Context context) {
        queue = VolleySingleton.getInstance(context).getRequestQueue();
        String Url = Constants.signupUrl;
        Url = getAbsoluteUrl(Url);
        JSONObject params = user.getJsonObjectAsParams();
        Log.d("params",""+params);
        JsonBaseRequest request = new JsonBaseRequest(Request.Method.POST, Url,params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    Log.d("tag2",response.toString());
                    int statusCode = response.getInt("statusCode");
                    String message = response.getString("message");
                    restClientInterface.onInitialize(statusCode,message,null);
                }
                catch(Exception e){
                    restClientInterface.onInitialize(500,"Something went wrong",new VolleyError());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                restClientInterface.onInitialize(500,"Something went wrong",new VolleyError());
            }
        },30000,0);
        queue.add(request);
    }

    public static void Logout(final String email,final LogoutEntity logoutEntity,final LogoutEntity.ChintokanRestClientInterface restClientInterface,Context context){
        queue = VolleySingleton.getInstance(context).getRequestQueue();
        String Url = Constants.logoutUrl;
        Url=getAbsoluteUrl(Url);
        Url+="?email="+email;
        JsonBaseRequest request = new JsonBaseRequest(Request.Method.GET, Url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Gson gson = new Gson();
                LogoutEntity n=gson.fromJson(response.toString(),LogoutEntity.class);
                logoutEntity.setMessage(n.getMessage());
                logoutEntity.setStatusCode(n.getStatusCode());
                restClientInterface.onInitialize(logoutEntity,null);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                restClientInterface.onInitialize(logoutEntity,new VolleyError());
            }
        },30000,3);
        queue.add(request);
    }

    public static void VerifyToken(final VerifyTokenEntity verifyTokenEntity,final VerifyTokenEntity.ChintokanRestClientInterface restClientInterface,Context context){
        queue = VolleySingleton.getInstance(context).getRequestQueue();
        String Url=Constants.verifyTokenUrl;
        Url=getAbsoluteUrl(Url);
        JSONObject params = verifyTokenEntity.getJsonObjectAsParams();
        JsonBaseRequest request = new JsonBaseRequest(Request.Method.POST, Url, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    int code = response.getInt("statusCode");
                    String message = response.getString("message");
                    restClientInterface.onInitialize(code,message,null);
                } catch (JSONException e) {
                    restClientInterface.onInitialize(500,"Something went wrong",new VolleyError());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                restClientInterface.onInitialize(500,"Something wrong",new VolleyError());
            }
        },30000,3);
        queue.add(request);
    }

    public static void AddMoney(final AddMoneyEntity addMoneyEntity,final AddMoneyEntity.RestClientInterface restClientInterface,final Context context){
        queue = VolleySingleton.getInstance(context).getRequestQueue();
        String Url = Constants.addMoneyUrl;
        Url = getAbsoluteUrl(Url);
        JSONObject params = addMoneyEntity.getJsonObjectAsParams();
        JsonBaseRequest request = new JsonBaseRequest(Request.Method.POST, Url, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    int statusCode = response.getInt("statusCode");
                    String message = response.getString("message");
                    restClientInterface.onInitialize(statusCode,message,null);
                } catch (JSONException e) {
                    restClientInterface.onInitialize(500,"Something went wrong",new VolleyError());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                restClientInterface.onInitialize(500,"Something wrong",new VolleyError());
            }
        },30000,3);
        queue.add(request);
    }

    public static void getCategories(final CategoryMiniEntity categoryMiniEntity,final CategoryMiniEntity.RestClientInterface restClientInterface,final Context context){
        queue = VolleySingleton.getInstance(context).getRequestQueue();
        String Url=Constants.getCategoryUrl;
        Url=getAbsoluteUrl(Url);
        JsonBaseRequest request= new JsonBaseRequest(Request.Method.GET, Url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    Gson gson = new Gson();
                    CategoryMiniEntity entity = gson.fromJson(response.toString(),CategoryMiniEntity.class);
                    categoryMiniEntity.setStatusCode(entity.getStatusCode());
                    categoryMiniEntity.setMessage(entity.getMessage());
                    categoryMiniEntity.setResponse(entity.getResponse());
                    restClientInterface.onInitialize(response.toString(),entity,null);
                }
                catch (Exception e){
                    restClientInterface.onInitialize(null,categoryMiniEntity,new VolleyError());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                restClientInterface.onInitialize("Something wrong",categoryMiniEntity,new VolleyError());
            }
        },30000,3);
        queue.add(request);
    }

    public static void getDivisions(final DivisionsMiniEntity divisionsMiniEntity, final DivisionsMiniEntity.RestClientInterface restClientInterface, final Context context){
        queue = VolleySingleton.getInstance(context).getRequestQueue();
        String Url=Constants.getDivisionUrl;
        Url=getAbsoluteUrl(Url);
        JsonBaseRequest request= new JsonBaseRequest(Request.Method.GET, Url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    Gson gson = new Gson();
                    DivisionsMiniEntity entity = gson.fromJson(response.toString(),DivisionsMiniEntity.class);
                    divisionsMiniEntity.setStatusCode(entity.getStatusCode());
                    divisionsMiniEntity.setMessage(entity.getMessage());
                    divisionsMiniEntity.setResponse(entity.getResponse());
                    Log.d("div",response.toString());
                    restClientInterface.onInitialize(response.toString(),entity,null);
                }
                catch (Exception e){
                    restClientInterface.onInitialize(null,divisionsMiniEntity,new VolleyError());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                restClientInterface.onInitialize("Something wrong",divisionsMiniEntity,new VolleyError());
            }
        },30000,3);
        queue.add(request);
    }

    public static void registerEvent(final RegisterEventEntity registerEventEntity,final RegisterEventEntity.RestClientInterface restClientInterface,final Context context){
        queue = VolleySingleton.getInstance(context).getRequestQueue();
        String Url = Constants.registerUrl;
        Url = getAbsoluteUrl(Url);
        JSONObject param = registerEventEntity.getJsonObjectAsParams();
        Log.d("dd",param.toString());
        JsonBaseRequest request = new JsonBaseRequest(Request.Method.POST, Url, param, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    Gson gson = new Gson();
                    int code = response.getInt("statusCode");
                    String message = response.getString("message");
                    restClientInterface.onInitialize(code,message,null);
                }
                catch(Exception e){
                    restClientInterface.onInitialize(500,"Error",new VolleyError());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                restClientInterface.onInitialize(500,"Already registered",error);
            }
        },3000,3);
        queue.add(request);
    }

    public static void getBalance(int userId,final GetBalanceEntity getBalanceEntity,final GetBalanceEntity.RestClientInterface restClientInterface,final Context context){
        queue = VolleySingleton.getInstance(context).getRequestQueue();
        String Url = Constants.getBalanceUrl;
        Url = getAbsoluteUrl(Url);
        Url+="?UserId="+userId;
        JsonBaseRequest request = new JsonBaseRequest(Request.Method.GET, Url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    int statusCode = response.getInt("statusCode");
                    String message = response.getString("message");
                    Long cash = response.getLong("response");
                    restClientInterface.onInitialize(statusCode,message,cash,null);
                }
                catch (Exception e){
                    restClientInterface.onInitialize(500,"Something went wrong", (long) 0,new VolleyError());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                getBalanceEntity.setMessage("something went wrong");
                restClientInterface.onInitialize(500,getBalanceEntity.getMessage(), (long) 0,error);
            }
        },3000,5);
        queue.add(request);
    }

    public static void postFeedback(final FeedbackEntity feedbackEntity,final FeedbackEntity.RestClientInterface restClientInterface,final Context context){
        queue = VolleySingleton.getInstance(context).getRequestQueue();
        String Url = Constants.postFeedbackUrl;
        Url = getAbsoluteUrl(Url);
        JSONObject param = feedbackEntity.getJsonObjectAsParams();
        Log.d("param",param.toString());
        JsonBaseRequest request = new JsonBaseRequest(Request.Method.POST, Url, param, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    int statusCode = response.getInt("statusCode");
                    String message = response.getString("message");
                    restClientInterface.onInitialize(statusCode,message,null);
                }
                catch (Exception e){
                    restClientInterface.onInitialize(500,"Something went wrong",new VolleyError());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                restClientInterface.onInitialize(500,"Something went wrong",new VolleyError());
            }
        },3000,5);
        queue.add(request);
    }

}
