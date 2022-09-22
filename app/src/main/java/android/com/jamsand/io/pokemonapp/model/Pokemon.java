package android.com.jamsand.io.pokemonapp.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Pokemon {
    @SerializedName("count")
    public String count;
    @SerializedName("next")
    public String next;


    @SerializedName("results")
    public ArrayList<PokemonArray> results;

    public class PokemonArray {
        @SerializedName("pokemonID")
        public int pokemonID;
        @SerializedName("url")
        public String url;
        @SerializedName("name")
        public String name;
    }
}
