package android.com.jamsand.io.pokemonapp.viewmodel;

import android.app.Application;
import android.com.jamsand.io.pokemonapp.model.Pokemon;
import android.com.jamsand.io.pokemonapp.repository.PokemonRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class PokemonViewModel  extends AndroidViewModel {
    private PokemonRepository pokemonResponse;
    private LiveData<Pokemon> pokemonLiveData;

    public PokemonViewModel(@NonNull Application application) {
        super(application);
        pokemonResponse = new PokemonRepository();
        this.pokemonLiveData = pokemonResponse.getRepositoryOfPokemons();
    }

    public  LiveData<Pokemon> getPokemonLiveData(){
        return pokemonLiveData;
    }
}
