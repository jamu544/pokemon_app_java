package android.com.jamsand.io.pokemonapp.repository;

import android.com.jamsand.io.pokemonapp.model.Pokemon;
import android.com.jamsand.io.pokemonapp.network.ApiRequest;
import android.com.jamsand.io.pokemonapp.network.RetrofitRequest;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonRepository {
    private static final String TAG = PokemonRepository.class.getSimpleName();
    private final ApiRequest apiRequest;

    public PokemonRepository() {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }

    public LiveData<Pokemon> getRepositoryOfPokemons(){
        final MutableLiveData<Pokemon> data = new MutableLiveData<>();
        apiRequest.getAllPokemonsFromApiRquest()
                .enqueue(new Callback<Pokemon>() {
                    @Override
                    public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                        Log.d(TAG, "onResponse response:: "+ response);
                        if (response.body() != null){
                            data.setValue(response.body());

                            Log.d(TAG, "pokemon total:: "+ response.body());
                            Log.d(TAG, "size:: :"+ response.body().results.size());
                        }
                    }

                    @Override
                    public void onFailure(Call<Pokemon> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }
}
