package android.com.jamsand.io.pokemonapp.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.com.jamsand.io.pokemonapp.R;
import android.com.jamsand.io.pokemonapp.databinding.ActivityPokemonDetailsBinding;
import android.com.jamsand.io.pokemonapp.utilities.Utils;
import android.com.jamsand.io.pokemonapp.viewmodel.PokemonViewModel;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class PokemonDetails extends AppCompatActivity {

    private Context context;





    private static String TAG = MainActivity.class.getSimpleName();
    private String pokemonName="";
    private String pokemonID = "";
    private ActivityPokemonDetailsBinding binding;
    PokemonViewModel pokemonViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_pokemon_details);
        context = this;

        Intent intent = getIntent();
         pokemonName = intent.getStringExtra(Utils.EXTRA_POKEMON_NAME);
         pokemonID = String.valueOf(intent.getStringExtra(Utils.EXTRA_POKEMON_ID));
          init();
        binding.pokemonDetailsName.setText(pokemonName);
        pokemonViewModel = new ViewModelProvider(this).get(PokemonViewModel.class);

    }

    private void init(){
 //       pokemonTextView = (TextView) findViewById(R.id.pokemonDetailsName);
//        weightTextView = (TextView)  findViewById(R.id.weightTextView);
//        heightTextView = (TextView) findViewById(R.id.heightTextView);
//        typesTextView = (TextView) findViewById(R.id.typeTextView);
    }

    public void getPokemonDetails(String pokemonName){


    }
}