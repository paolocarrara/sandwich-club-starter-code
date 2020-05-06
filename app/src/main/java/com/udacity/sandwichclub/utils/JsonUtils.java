package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        JSONObject jsonSandwich = null;
        JSONObject name = null;
        String mainName = null;
        ArrayList<String> alsoKnownAs = new ArrayList<>();
        String placeOfOrigin = null;
        String description = null;
        String image = null;
        ArrayList<String> ingredients = new ArrayList<>();


        try {
            jsonSandwich = new JSONObject(json);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (jsonSandwich != null) {
            try {
                name = jsonSandwich.getJSONObject("name");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (name != null) {
            try {
                mainName = name.getString("mainName");
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                JSONArray alsoKnownAsJSONArray = name.getJSONArray("alsoKnownAs");
                for (int i = 0; i < alsoKnownAsJSONArray.length(); i++) {
                    alsoKnownAs.add(alsoKnownAsJSONArray.get(i).toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (jsonSandwich != null) {
            try {
                placeOfOrigin = jsonSandwich.getString("placeOfOrigin");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (jsonSandwich != null) {
            try {
                description = jsonSandwich.getString("description");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (jsonSandwich != null) {
            try {
                image = jsonSandwich.getString("image");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (jsonSandwich != null) {
            try {
                JSONArray ingredientsJSONArray = jsonSandwich.getJSONArray("ingredients");
                for (int i = 0; i < ingredientsJSONArray.length(); i++) {
                    ingredients.add(ingredientsJSONArray.get(i).toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Sandwich sandwich = new Sandwich();
        sandwich.setMainName(mainName);
        sandwich.setAlsoKnownAs(alsoKnownAs);
        sandwich.setPlaceOfOrigin(placeOfOrigin);
        sandwich.setDescription(description);
        sandwich.setImage(image);
        sandwich.setIngredients(ingredients);

        return sandwich;
    }
}
