package android.com.jamsand.io.pokemonapp.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.app.ProgressDialog;
import android.com.jamsand.io.pokemonapp.R;
import android.com.jamsand.io.pokemonapp.databinding.ActivityPokemonDetailsBinding;
import android.com.jamsand.io.pokemonapp.model.Pokemon;
import android.com.jamsand.io.pokemonapp.utilities.Utils;
import android.com.jamsand.io.pokemonapp.viewmodel.PokemonViewModel;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class PokemonDetails extends AppCompatActivity {

    private Context context;





    private static String TAG = MainActivity.class.getSimpleName();
    private String pokemonName="";
    private String pokemonID = "";
    private ActivityPokemonDetailsBinding binding;
    PokemonViewModel pokemonViewModel;
    private ProgressDialog progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_pokemon_details);
        context = this;


          init();
        binding.pokemonDetailsName.setText(pokemonName);


    }

    private void init(){
        Intent intent = getIntent();
        pokemonName = intent.getStringExtra(Utils.EXTRA_POKEMON_NAME);
        pokemonID = String.valueOf(intent.getStringExtra(Utils.EXTRA_POKEMON_ID));
        progressBar = new ProgressDialog(context);
        progressBar.setMessage("Loading...");
        progressBar.show();
        pokemonViewModel = new ViewModelProvider(this).get(PokemonViewModel.class);
        getPokemonDetails(pokemonName);
    }

    public void getPokemonDetails(String pokemonName){
        pokemonViewModel.getPokemonDetailsLiveData().observe(this,details -> {
            if (details != null && details.name != null && details.name != "") {
                progressBar.dismiss();
//                Picasso.with(context).
//                        load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"
//                                +pokemon.pokemonID+".png")
//                        .into(holder.itemBinding.imageViewThumbnail);
                binding.heightTextView.setText(pokemonName);
                binding.weightTextView.setText(pokemonName);



                //    binding.setAdapter(adapter);


            }
        });


    }
}