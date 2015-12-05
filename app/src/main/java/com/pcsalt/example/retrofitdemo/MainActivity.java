package com.pcsalt.example.retrofitdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.pcsalt.example.retrofitdemo.controller.ApiEndpoints;
import com.pcsalt.example.retrofitdemo.model.Geocode;
import com.pcsalt.example.retrofitdemo.model.geocode.STATUS;

import java.util.HashMap;
import java.util.Map;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public static final String TEST_URL = "http://maps.googleapis.com";
    private Retrofit mRetrofit;

    private EditText etLatitude, etLongitude;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRetrofit();
        initViews();
    }

    private void initRetrofit() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(TEST_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private void initViews() {
        etLatitude = (EditText) findViewById(R.id.et_latitude);
        etLongitude = (EditText) findViewById(R.id.et_longitude);
        tvResult = (TextView) findViewById(R.id.tv_result);
        Button btnGetAddress = (Button) findViewById(R.id.btn_get_address);

        btnGetAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestAddress(etLatitude.getText().toString(), etLongitude.getText().toString());
            }
        });
    }

    private void requestAddress(String latitude, String longitude) {
        Map<String, String> params = new HashMap<>();
        params.put("latlng", String.format("%s,%s", latitude, longitude));

        ApiEndpoints apiService = mRetrofit.create(ApiEndpoints.class);

        Call<Geocode> geocodeCall = apiService.getGeocodeByLatLng(params);

        geocodeCall.enqueue(new Callback<Geocode>() {
            @Override
            public void onResponse(Response<Geocode> response, Retrofit retrofit) {
                Geocode geocode = response.body();
                if (geocode.getStatus() == STATUS.OK) {
                    tvResult.setText(geocode.getResults().get(0).getFormattedAddress());
                } else {
                    tvResult.setText(geocode.getStatus().name());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });

    }
}
