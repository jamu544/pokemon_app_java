package android.com.jamsand.io.pokemonapp.adapter;

import android.com.jamsand.io.pokemonapp.databinding.ActivityPokemonDetailsBinding;
import android.com.jamsand.io.pokemonapp.view.activity.OnItemClickListener;
import android.com.jamsand.io.pokemonapp.R;
import android.com.jamsand.io.pokemonapp.model.Pokemon;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonHolder> implements OnItemClickListener{

    public Context context;
    public ArrayList<Pokemon.PokemonArray> pokemonList;



    public OnItemClickListener clickListener;

    public PokemonAdapter(ArrayList<Pokemon.PokemonArray> pokemonList, Context context){
        this.context = context;
        this.pokemonList = pokemonList;
    }
    @NonNull
    @Override
    public PokemonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ActivityPokemonDetailsBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.activity_pokemon_details,parent, false
        );
        return new PokemonHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonHolder holder, int position) {
        Pokemon.PokemonArray pokemon = pokemonList.get(position);
        holder.pokemonDetailsBinding.pokemonName.setText(pokemon.name);
        pokemon.pokemonID = position+1;

       // final String imageUrl = pokemonList.get(position).imageUrl;

        Glide.with(context)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+pokemon.pokemonID+".png")
                .into(holder.pokemonDetailsBinding.imageViewThumbnail);

    }
    public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public int getItemCount() {
        return pokemonList == null? 0:pokemonList.size();
    }

    @Override
    public void onClick(Pokemon.PokemonArray pokemon, int position) {

    }


    public class PokemonHolder extends RecyclerView.ViewHolder {

        public ActivityPokemonDetailsBinding pokemonDetailsBinding;

        public PokemonHolder(ActivityPokemonDetailsBinding pokemonDetailsBinding) {
            super(pokemonDetailsBinding.getRoot());
            this.pokemonDetailsBinding = pokemonDetailsBinding;
        }

    }
}
