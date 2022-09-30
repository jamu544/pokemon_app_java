package android.com.jamsand.io.pokemonapp.repository;

import android.com.jamsand.io.pokemonapp.model.Details;
import android.com.jamsand.io.pokemonapp.model.Pokemon;
import android.com.jamsand.io.pokemonapp.network.APIInterface;
import android.com.jamsand.io.pokemonapp.network.APIClient;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonRepository {
    private static final String TAG = PokemonRepository.class.getSimpleName();
    private final APIInterface APIInterface;

    public PokemonRepository() {
        APIInterface = APIClient.getRetrofitInstance().create(APIInterface.class);
    }

    public LiveData<Pokemon> getRepositoryListOfPokemons() {
        final MutableLiveData<Pokemon> data = new MutableLiveData<>();
        APIInterface.getAllPokemonsFromApiRquest()
                .enqueue(new Callback<Pokemon>() {
                    @Override
                    public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                        Log.d(TAG, "onResponse response:: " + response);
                        if (response.body() != null) {
                            data.setValue(response.body());

                            Log.d(TAG, "pokemon total:: " + response.body());
                            Log.d(TAG, "size:: :" + response.body().results.size());
                        }
                    }

                    @Override
                    public void onFailure(Call<Pokemon> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }

    public LiveData<Details> getRepositoryOfPokemonDetails(String name) {
        final MutableLiveData<Details> data = new MutableLiveData<>();
        APIInterface.getPokemonDetailsFromApiRequest(name)
                .enqueue(new Callback<Details>() {
                    @Override
                    public void onResponse(Call<Details> call, Response<Details> response) {
                        Log.d(TAG, "onResponse response:: " + response);
                        if (response.body() != null) {
                            data.setValue(response.body());

                            Log.d(TAG, "pokemon datails:: " + response.body());

                        }
                    }

                    @Override
                    public void onFailure(Call<Details> call, Throwable t) {

                    }
                });

        return data;
    }

}
