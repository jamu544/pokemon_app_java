package android.com.jamsand.io.pokemonapp.view.activity.adapter;

import android.com.jamsand.io.pokemonapp.OnItemClickListener;
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
import androidx.recyclerview.widget.RecyclerView;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonHolder>{

    public Context context;
    public ArrayList<Pokemon.Results> pokemonList;



    public OnItemClickListener clickListener;

    public PokemonAdapter(ArrayList<Pokemon.Results> pokemonList, Context context){
        this.context = context;
        this.pokemonList = pokemonList;
    }


    @NonNull
    @Override
    public PokemonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.pokemon_list_item,parent,false);
        PokemonHolder holder = new PokemonHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonHolder holder, int position) {
        final String pokemon = pokemonList.get(position).name;
        final String imageUrl = pokemonList.get(position).imageUrl;
        holder.pokemonName.setText(pokemon);
        Glide.with(context)
                .load(imageUrl)
                .into(holder.thumbnail);

    }
    public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public int getItemCount() {
        return pokemonList == null? 0:pokemonList.size();
    }
    public class PokemonHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView pokemonName;
        public ImageView thumbnail;



        public PokemonHolder(@NonNull View itemView) {
            super(itemView);
            this.pokemonName = (TextView) itemView.findViewById(R.id.pokemonName);
            this.thumbnail = (ImageView) itemView.findViewById(R.id.imageViewThumbnail);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            if (clickListener != null)
                clickListener.onClick(view, getAdapterPosition());

        }
    }
}
