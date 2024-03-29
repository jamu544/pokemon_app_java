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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    private Context context;
    private RecyclerView recyclerView;
    private PokemonAdapter adapter;
    private ProgressDialog progressBar;
    private ArrayList<Pokemon.PokemonArray> pokemonArrayList = new ArrayList<>();
    private PokemonViewModel pokemonViewModel;
    public ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        init();
        getPokemons();
    }

    private void init(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        context = this;
        progressBar = new ProgressDialog(context);
        progressBar.setMessage(getString(R.string.loading));
        progressBar.show();



        pokemonViewModel = new ViewModelProvider(this).get(PokemonViewModel.class);

    }

    private void getPokemons() {
        pokemonViewModel.getPokemonListLiveData().observe(this, pokemon -> {
            if (pokemon != null && pokemon.results != null && !pokemon.results.isEmpty()) {
                progressBar.dismiss();
                List<Pokemon.PokemonArray> pokemonList = pokemon.results;
                pokemonArrayList.addAll(pokemonList);
                adapter = new PokemonAdapter( pokemonArrayList,context);
                recyclerView = findViewById(R.id.recyclerview);
                recyclerView.setAdapter(adapter); //testing
            }
        });
    }

    @Override
    public void onClick(Pokemon.PokemonArray pokemon) {
        Intent detailsIntent = new Intent(this, PokemonDetails.class);
        detailsIntent.putExtra(Utils.EXTRA_POKEMON_NAME,pokemonArrayList.get(pokemon.pokemonID).name);
        detailsIntent.putExtra(Utils.EXTRA_POKEMON_ID,pokemon.pokemonID);

        startActivity(detailsIntent);
        Toast.makeText(context, pokemonArrayList.get(pokemon.pokemonID).name,Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search_option, menu);

        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String searchQuery) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

               filter(newText);
                return false;
            }
        });
        return true;
    }

    private void filter(String newtext){
        ArrayList<Pokemon.PokemonArray> filteredList = new ArrayList<Pokemon.PokemonArray>();
        for (Pokemon.PokemonArray item: pokemonArrayList) {
            if(item.name.toLowerCase().contains(newtext.toLowerCase())){
                filteredList.add(item);
            }
        }
        if(filteredList.isEmpty()){
            Toast.makeText(context,"No Data Found..",Toast.LENGTH_SHORT).show();
        }
        else {
            adapter.filterList(filteredList);
        }

    }
}