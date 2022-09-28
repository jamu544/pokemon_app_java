package android.com.jamsand.io.pokemonapp.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.com.jamsand.io.pokemonapp.R;
import android.com.jamsand.io.pokemonapp.adapter.PokemonAdapter;
import android.com.jamsand.io.pokemonapp.databinding.ActivityMainBinding;
import android.com.jamsand.io.pokemonapp.model.Pokemon;
import android.com.jamsand.io.pokemonapp.utilities.Utils;
import android.com.jamsand.io.pokemonapp.viewmodel.PokemonViewModel;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    private static String TAG = MainActivity.class.getSimpleName();
    private Context context;
    private RecyclerView recyclerView;
    private PokemonAdapter adapter;
    private ProgressDialog progressBar;

    private ArrayList<Pokemon.PokemonArray> pokemonArrayList = new ArrayList<>();
    PokemonViewModel pokemonViewModel;


    public ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        context = this;

        init();
        getPokemons();
    }

    private void init(){

        progressBar = new ProgressDialog(context);
        progressBar.setMessage("Loading...");
        progressBar.show();

        adapter = new PokemonAdapter( pokemonArrayList,context);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setAdapter(adapter);

        pokemonViewModel = new ViewModelProvider(this).get(PokemonViewModel.class);

    }

    private void getPokemons() {
        pokemonViewModel.getPokemonLiveData().observe(this, pokemon -> {
            if (pokemon != null && pokemon.results != null && !pokemon.results.isEmpty()) {
                progressBar.dismiss();
                List<Pokemon.PokemonArray> pokemonList = pokemon.results;
                pokemonArrayList.addAll(pokemonList);

                //    binding.setAdapter(adapter);
                adapter.notifyDataSetChanged();

                adapter.setClickListener(new OnItemClickListener() {
                    @Override
                    public void onClick(Pokemon.PokemonArray pokemon) {

                        Intent detailsIntent = new Intent(context, PokemonDetails.class);
                        detailsIntent.putExtra(Utils.EXTRA_POKEMON_NAME, pokemonArrayList.get(pokemon.pokemonID).name);
                        detailsIntent.putExtra(Utils.EXTRA_POKEMON_ID, pokemon.pokemonID);

                        startActivity(detailsIntent);
                        Toast.makeText(context, "" + pokemonArrayList.get(pokemon.pokemonID).name, Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }

    @Override
    public void onClick(Pokemon.PokemonArray pokemon) {
        Intent detailsIntent = new Intent(this, PokemonDetails.class);
        detailsIntent.putExtra(Utils.EXTRA_POKEMON_NAME,pokemonArrayList.get(pokemon.pokemonID).name);
        detailsIntent.putExtra(Utils.EXTRA_POKEMON_ID,pokemon.pokemonID);

        startActivity(detailsIntent);
        Toast.makeText(context,pokemonArrayList.get(pokemon.pokemonID).pokemonID+" "+ pokemonArrayList.get(pokemon.pokemonID).name,Toast.LENGTH_SHORT).show();
    }

}