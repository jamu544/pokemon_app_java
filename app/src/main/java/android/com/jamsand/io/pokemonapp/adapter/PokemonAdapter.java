package android.com.jamsand.io.pokemonapp.adapter;


import android.com.jamsand.io.pokemonapp.BR;
import android.com.jamsand.io.pokemonapp.databinding.PokemonListItemBinding;
import android.com.jamsand.io.pokemonapp.view.activity.OnItemClickListener;
import android.com.jamsand.io.pokemonapp.R;
import android.com.jamsand.io.pokemonapp.model.Pokemon;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import androidx.recyclerview.widget.RecyclerView;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonHolder> implements OnItemClickListener{

    public Context context;
    public List<Pokemon.PokemonArray> pokemonList;



    public OnItemClickListener clickListener;

    public PokemonAdapter(ArrayList<Pokemon.PokemonArray> pokemonList, Context context){
        this.context = context;
        this.pokemonList = pokemonList;
    }

    public PokemonAdapter(Context context){
        this.context = context;
    }
    @NonNull
    @Override
    public PokemonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PokemonListItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.pokemon_list_item,parent, false
        );
        return new PokemonHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonHolder holder, int position) {
        Pokemon.PokemonArray pokemon = pokemonList.get(position);
        holder.itemBinding.pokemonName.setText(pokemon.name);
        pokemon.pokemonID = position+1;

        Picasso.with(context).
                load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"
                        +pokemon.pokemonID+".png")
                .into(holder.itemBinding.imageViewThumbnail);
        holder.itemBinding.setOnItemClick((OnItemClickListener) context);
    }
    public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }
    public void setPokemonList(List<Pokemon.PokemonArray> pokemonList){
        this.pokemonList = pokemonList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return pokemonList == null? 0:pokemonList.size();
    }

    @Override
    public void onClick(Pokemon.PokemonArray pokemon, int position) {

    }


    public class PokemonHolder extends RecyclerView.ViewHolder {

        //public PokemonListItemBinding itemBinding;
        public PokemonListItemBinding itemBinding;

        public PokemonHolder(PokemonListItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }

        public void bind(Object object){
            itemBinding.setVariable(BR.pokemon, object);
            itemBinding.executePendingBindings();
            }

    }
}
