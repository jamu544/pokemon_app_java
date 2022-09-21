package android.com.jamsand.io.pokemonapp.view.activity;

import android.com.jamsand.io.pokemonapp.model.Pokemon;
import android.view.View;

public interface OnItemClickListener {
    public void onClick(Pokemon.PokemonArray pokemon, int position);
}
