package android.com.jamsand.io.pokemonapp.network;

import android.com.jamsand.io.pokemonapp.model.Details;
import android.com.jamsand.io.pokemonapp.model.Pokemon;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIInterface {

    @GET("https://pokeapi.co/api/v2/pokemon/")
    Call<Pokemon> getAllPokemonsFromApiRquest();

   @GET("https://pokeapi.co/api/v2/pokemon/{name}")
    Call <Details> getPokemonDetailsFromApiRequest(@Path("name") String name);
}
