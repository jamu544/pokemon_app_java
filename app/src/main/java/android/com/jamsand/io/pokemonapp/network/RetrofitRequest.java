package android.com.jamsand.io.pokemonapp.network;

import android.com.jamsand.io.pokemonapp.utils.AppConstants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitRequest {
    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(AppConstants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
