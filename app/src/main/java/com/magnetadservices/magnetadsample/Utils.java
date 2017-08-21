package com.magnetadservices.magnetadsample;

import android.content.Context;

import com.magnetadservices.magnetadsample.models.Country;
import com.magnetadservices.sdk.MagnetNativeContentAd;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Esmaeili on 2017-08-20.
 */

public class Utils {

    private static String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("countries.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            int result = is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static List<Object> getListOfCountries(Context context) {
        List<Object> data = new ArrayList<>();
        try {
            JSONObject countriesObj = new JSONObject(loadJSONFromAsset(context));
            JSONArray countries = countriesObj.getJSONArray("countries");
            for (int i=0; i<countries.length(); i++) {
                JSONObject country = (JSONObject) countries.get(i);
                int flagResId = context.getResources().getIdentifier("ic_flag_of_" + country.getString("eng_name").replace(" ", "_"), "drawable", context.getPackageName());
                int imageResId = context.getResources().getIdentifier("image_" + country.getString("eng_name").replace(" ", "_"), "drawable", context.getPackageName());
                data.add(new Country(country.getString("name"), country.getString("description"), flagResId, imageResId));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static List<Object> injectAdObjects(Context context, List<Object> data) {
        for (int i = data.size()-1; i >= 0; i--) {
            if (i%2 == 0 && i != 0) {
                data.add(i, MagnetNativeContentAd.create(context));
            }
        }
        return data;
    }
}
