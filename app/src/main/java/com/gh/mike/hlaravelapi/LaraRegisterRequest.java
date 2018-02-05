package com.gh.mike.hlaravelapi;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mike on 2/3/18.
 */

public class LaraRegisterRequest extends StringRequest {
    public static String  REGISTER_REQUEST_URL="http://mimobile.com/mregister?api_token="+MainActivity.token+"&email="+MainActivity.txtemail+"&password="+MainActivity.txtpass+"&phone="+MainActivity.txtphone+"&name="+MainActivity.txtfullname;

    private Map<String, String> params;
    public LaraRegisterRequest( Response.Listener<String> listener) {
        super(Method.GET, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        MainActivity.txtemail ="";
        MainActivity.txtpass ="";
        MainActivity.txtphone ="";
        MainActivity.txtfullname ="";
    }

    @Override
    public Map<String,String> getParams(){return params;}


    }

