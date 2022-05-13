package android.com.jamsand.io.pokemonapp.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Pokemon {
    @Expose
    @SerializedName("results")
    public ArrayList<Results> results = new ArrayList<Results>();

    public class Results {
        @SerializedName("name")
        public String name;
        @SerializedName("url")
        public String imageUrl;
    }
}
