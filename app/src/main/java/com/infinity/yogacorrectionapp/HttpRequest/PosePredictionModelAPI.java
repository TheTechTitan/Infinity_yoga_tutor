package com.infinity.yogacorrectionapp.HttpRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PosePredictionModelAPI {
    @POST("prediction")
    Call<PredictionModelResponse> initiatePredictionPOST(@Body UserModel user);

    @GET("prediction/stop")
    Call<PredictionModelResponse> stopPredictionGET();
}
