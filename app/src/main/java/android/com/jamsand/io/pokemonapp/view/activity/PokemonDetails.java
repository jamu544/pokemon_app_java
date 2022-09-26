package android.com.jamsand.io.pokemonapp.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.com.jamsand.io.pokemonapp.R;
import android.com.jamsand.io.pokemonapp.utilities.Utils;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class PokemonDetails extends AppCompatActivity {

    private Context context;
    private String pokemonName="";
    private String pokemonID = "";

    private TextView pokemonTextView;
    private TextView weightTextView;
    private TextView heightTextView;
    private TextView typesTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_details);
        context = this;

        Intent intent = getIntent();
         pokemonName = intent.getStringExtra(Utils.EXTRA_POKEMON_NAME);
         pokemonID = intent.getStringExtra(Utils.EXTRA_POKEMON_ID);
        init();
    }

    private void init(){
//        pokemonTextView = (TextView) findViewById(R.id.pokemonDetailsName);
//        weightTextView = (TextView)  findViewById(R.id.weightTextView);
//        heightTextView = (TextView) findViewById(R.id.heightTextView);
//        typesTextView = (TextView) findViewById(R.id.typeTextView);
    }
}