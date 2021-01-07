package com.infinity.yogacorrectionapp.HttpRequest;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserController {

    PosePredictionModelAPI posePredictionModelAPI;

    public UserController() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.4:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        posePredictionModelAPI = retrofit.create(PosePredictionModelAPI.class);
    }

    public void initiatePrediction(String capture, int level, String token) {

        UserModel user=new UserModel(capture,level,token);

        Call<PredictionModelResponse> call = posePredictionModelAPI.initiatePredictionPOST(user);

        call.enqueue(new Callback<PredictionModelResponse>() {
            @Override
            public void onResponse(Call<PredictionModelResponse> call, Response<PredictionModelResponse> response) {
                if (!response.isSuccessful()) {
                    Integer code=response.code();
                    Log.d("Response not successful", code.toString());
                    return;
                }

                Integer codeSuccess=response.code();
                Log.d("Response code", codeSuccess.toString());

                PredictionModelResponse rep= response.body();
                Log.d("Response message", rep.getMessage());

            }

            @Override
            public void onFailure(Call<PredictionModelResponse> call, Throwable t) {
                Log.d("Failed POST request", t.getMessage());
            }
        });

    }

    public void stopPrediction(){

        Call<PredictionModelResponse> call = posePredictionModelAPI.stopPredictionGET();
        call.enqueue(new Callback<PredictionModelResponse>() {
            @Override
            public void onResponse(Call<PredictionModelResponse> call, Response<PredictionModelResponse> response) {
                if (!response.isSuccessful()) {
                    Integer code=response.code();
                    Log.d("Response not successful", code.toString());
                    return;
                }

                Integer codeSuccess=response.code();
                Log.d("Response code", codeSuccess.toString());

                PredictionModelResponse rep= response.body();
                Log.d("Response message", rep.getMessage());
            }
            @Override
            public void onFailure(Call<PredictionModelResponse> call, Throwable t) {
                Log.d("Failed GET request", t.getMessage());
            }
        });

    }
}
