package com.example.weather_app3;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.app.DownloadManager.Request;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private List<ModelClass> modelClass = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recylerview);
        GpsTracker gpsTracker = new GpsTracker(this);
        if (gpsTracker.canGetLocation()) {

            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();
            Log.d("lat",latitude+"");

            find_city(String.valueOf(latitude), String.valueOf(longitude));


        } else {
            gpsTracker.showSettingsAlert();
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 100);
        }


    }

    public void find_city(String lat, String lon) {
        String url = "http://api.openweathermap.org/data/2.5/group?id=964137,2267057,3117735,2988507,2950159,2618425,4219762,2643743,5344157,3067696,2761369&appid=90ebdc57172a838d0fce0abbc044df8e&units=metric";
        JsonObjectRequest jor = new JsonObjectRequest(Request.NETWORK_MOBILE, url, null, new Response.Listener<JSONObject>() {

            public void onResponse(JSONObject response) {
                Log.d("response", response.toString());
                try {
                    JSONArray main_object = response.getJSONArray("list");
                    Log.d("list", main_object.length() + "");
                    for (int i = 0; i < main_object.length(); i++) {
                        String city = main_object.getJSONObject(i).getString("name");
                        Log.d("city::", city);

                        String time = main_object.getJSONObject(i).getString("dt");
                        Log.d("time:::", time);

                        String temp = main_object.getJSONObject(i).getString("main");
                        Log.d("temp:::", temp);

                        Log.d("city:::",city);

                        JSONObject detail_array = main_object.getJSONObject(i).getJSONObject("main");
                        JSONObject wind_arry = main_object.getJSONObject(i).getJSONObject("wind");

                        Log.d("temparature:::",detail_array.getString("temp"));
                        Log.d("humidity:::",detail_array.getString("humidity"));
                        JSONArray weather = main_object.getJSONObject(i).getJSONArray("weather");
                        Log.d("weather",weather.getJSONObject(0).getString("main"));
                        Log.d("description",weather.getJSONObject(0).getString("description"));
                        Log.d("icon",weather.getJSONObject(0).getString("icon"));
                        Log.d("temp_max",detail_array.getString("temp_max"));
                        Log.d("temp_min",detail_array.getString("temp_min"));
                        Log.d("speed",wind_arry.getString("speed"));
                        Log.d("degree",wind_arry.getString("deg"));

                        modelClass.add(new ModelClass(city, time, detail_array.getString("temp"), detail_array.getString("humidity"), weather.getJSONObject(0).getString("main"), weather.getJSONObject(0).getString("description"), weather.getJSONObject(0).getString("icon"),detail_array.getString("temp_max"),detail_array.getString("temp_min"),wind_arry.getString("speed")));
//                        getCurrentLocationData(lat,lon,modelClass);
                        WeatherAdapter weatherAdapter = new WeatherAdapter(MainActivity.this,modelClass);
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                        recyclerView.setLayoutManager(mLayoutManager);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        recyclerView.setAdapter(weatherAdapter);

                    }

                } catch (JSONException e) {
                    Log.d("error", e.toString());
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error response", error.toString());

            }
        });
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(jor);

    }



}