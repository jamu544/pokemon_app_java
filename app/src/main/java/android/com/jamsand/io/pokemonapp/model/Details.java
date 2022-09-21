package android.com.jamsand.io.pokemonapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Details {
    @SerializedName("name")
    public String name;
    @SerializedName("url")
    public String url;
    @SerializedName("height")
    public int height;
    @SerializedName("weight")
    public String weight;
    @SerializedName("types")
    public List<Type> types;
    @SerializedName("sprites")
    public Sprites sprites;

    public class Type {
        public PokemonType type;
    }

    public class PokemonType {
        public String name;
    }

    public class Sprites{
        public Other other;
    }

    public class Other {
        public Home home;
    }

    public class Home {
        public String font_default;
    }





}
