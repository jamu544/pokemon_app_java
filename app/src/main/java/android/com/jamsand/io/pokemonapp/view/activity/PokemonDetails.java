package android.com.jamsand.io.pokemonapp.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.app.ProgressDialog;
import android.com.jamsand.io.pokemonapp.R;
import android.com.jamsand.io.pokemonapp.databinding.ActivityPokemonDetailsBinding;
import android.com.jamsand.io.pokemonapp.model.Details;
import android.com.jamsand.io.pokemonapp.network.APIInterface;
import android.com.jamsand.io.pokemonapp.utilities.Utils;
import android.com.jamsand.io.pokemonapp.viewmodel.PokemonViewModel;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PokemonDetails extends AppCompatActivity {

    private Context context;





    private static String TAG = MainActivity.class.getSimpleName();
    private String pokemonName="";
    private String pokemonID = "";
    private ActivityPokemonDetailsBinding binding;
    PokemonViewModel pokemonViewModel;
    private ProgressDialog progressBar;
    APIInterface apiInterface;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_pokemon_details);
        context = this;
        Intent intent = getIntent();
        pokemonName = intent.getStringExtra(Utils.EXTRA_POKEMON_NAME);
        //   pokemonID = String.valueOf(intent.getStringExtra(Utils.EXTRA_POKEMON_ID));


          init();
        binding.pokemonDetailsName.setText(pokemonName);

        getPokemonDetailsFromAPIRequest(pokemonName);
    }

    private void init(){

        progressBar = new ProgressDialog(context);
        progressBar.setMessage("Loading...");
        progressBar.show();
        pokemonViewModel = new ViewModelProvider(this).get(PokemonViewModel.class);
    }

    private void getPokemonDetailsFromAPIRequest(String pokemonName){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Utils.BASE_URL2)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        APIInterface client = retrofit.create(APIInterface.class);
        Call<Details> call = client.getPokemonDetailsFromApiRequest( pokemonName);
        progressBar.show();
        call.enqueue(new Callback<Details>() {
            @Override
            public void onResponse(Call<Details> call, Response<Details> response) {

                binding.heightTextView.setText("Height: "+response.body().height+" cm");
                binding.weightTextView.setText("Weight: "+response.body().weight+" kg");

                ArrayList<String> listOftypes = new  ArrayList<String>();
                StringBuilder stringBuilder = new StringBuilder();
                for(Details.Type type : response.body().types  ){
                    listOftypes.add(type.type.name);
                    stringBuilder.append(type.type.name+" ");
                }

                binding.typeTextView.setText("Type : "+stringBuilder);

                Glide.with(context)
                        .load(response.body().sprites.other.home.front_default)
                        .into(binding.imageView);

                progressBar.dismiss();
            }

            @Override
            public void onFailure(Call<Details> call, Throwable t) {

            }
        });




    }

}