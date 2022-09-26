package android.com.jamsand.io.pokemonapp.network;

import android.com.jamsand.io.pokemonapp.utilities.Utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonListApiService {


    private static Retrofit retrofit;

    public static Retrofit getPokemonListRetrofitInstance(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(Utils.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
