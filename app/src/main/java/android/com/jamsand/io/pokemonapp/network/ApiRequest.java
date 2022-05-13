package android.com.jamsand.io.pokemonapp.network;

import android.com.jamsand.io.pokemonapp.model.Pokemon;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiRequest {
  //  @GET("https://pokeapi.co/api/v2/pokemon/")
    @GET("https://pokeapi.co/api/v2/pokemon/")
    Call<Pokemon> getAllPokemons();
}
