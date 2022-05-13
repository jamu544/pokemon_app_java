package android.com.jamsand.io.pokemonapp.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.com.jamsand.io.pokemonapp.OnItemClickListener;
import android.com.jamsand.io.pokemonapp.R;
import android.com.jamsand.io.pokemonapp.view.activity.adapter.PokemonAdapter;
import android.com.jamsand.io.pokemonapp.model.Pokemon;
import android.com.jamsand.io.pokemonapp.network.ApiRequest;
import android.com.jamsand.io.pokemonapp.network.RetrofitRequest;
import android.com.jamsand.io.pokemonapp.utils.AppConstants;
import android.com.jamsand.io.pokemonapp.viewmodel.PokemonViewModel;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    private static String TAG = MainActivity.class.getSimpleName();
    private Context context;
    private RecyclerView recyclerView;
    private PokemonAdapter adapter;
    private ProgressDialog progressBar;

    private ArrayList<Pokemon.Results> pokemonArrayList = new ArrayList<>();
    PokemonViewModel pokemonViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
//        progressBar = new ProgressDialog(context);
//        progressBar.setMessage("Loading...");
//        progressBar.show();

//        loadData();
        init();
        getPokemons();
    }

    private void init(){
        progressBar = new ProgressDialog(context);
        progressBar.setMessage("Loading...");
        progressBar.show();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setHasFixedSize(true);

        adapter = new PokemonAdapter( pokemonArrayList,context);
        recyclerView.setAdapter(adapter);

        // View Model
      //  pokemonViewModel = ViewModelProvider.of(this).get(PokemonViewModel.class);
        pokemonViewModel = new ViewModelProvider(this).get(PokemonViewModel.class);

    }

    private void getPokemons(){
        pokemonViewModel.getPokemonLiveData().observe(this,pokemon -> {
            if (pokemon != null && pokemon.results != null && !pokemon.results.isEmpty()){
                progressBar.dismiss();
                List<Pokemon.Results> pokemonList = pokemon.results;
                pokemonArrayList.addAll(pokemonList);
                adapter.notifyDataSetChanged();

            }
        });
    }

    private void loadData(){
        //Create handle for the RetrofitInstance interface
        ApiRequest service = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        Call<Pokemon> call = service.getAllPokemons();
        call.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                if (response.isSuccessful()) {
                    progressBar.dismiss();
                    progressBar.setCancelable(false);


                    pokemonArrayList = response.body().results;
                    displayListView(pokemonArrayList);
                }
            }
            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                progressBar.dismiss();
            }
        });
    }

    private void displayListView(ArrayList<Pokemon.Results> pokemonList){
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new PokemonAdapter(pokemonList,context);
        adapter.setClickListener(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onClick(View view, int position) {
        Intent detailsIntent = new Intent(this, PokemonDetails.class);
        detailsIntent.putExtra(AppConstants.EXTRA_POKEMON_NAME,pokemonArrayList.get(position).name);
        detailsIntent.putExtra(AppConstants.EXTRA_POKEMON_ID,position);

        startActivity(detailsIntent);
        Toast.makeText(context,""+ pokemonArrayList.get(position).name,Toast.LENGTH_SHORT).show();

    }

}