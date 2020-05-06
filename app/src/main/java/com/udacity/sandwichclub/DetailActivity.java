package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);

        if (position >= sandwiches.length) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        populateAlsoKnownAsTextViewUI(sandwich.getAlsoKnownAs());
        populateDescriptionTextViewUI(sandwich.getDescription());
        populatePlaceOfOriginTextViewUI(sandwich.getPlaceOfOrigin());
        populateIngredientsTextViewUI(sandwich.getIngredients());
    }

    private void populateAlsoKnownAsTextViewUI (List<String> alsoKnownAs) {
        TextView alsoKnownAsTextView = findViewById(R.id.also_known_tv);
        TextView alsoKnownAsLabelTextView = findViewById(R.id.detail_also_known_as_label);

        if (alsoKnownAs == null || alsoKnownAs.isEmpty()) {
            alsoKnownAsTextView.setVisibility(View.GONE);
            alsoKnownAsLabelTextView.setVisibility(View.GONE);
        } else {
            alsoKnownAsTextView.setText("");

            int size = alsoKnownAs.size();
            for (int i = 0; i < size; i++) {
                alsoKnownAsTextView.append(alsoKnownAs.get(i));

                if (i != size - 1) {
                    alsoKnownAsTextView.append(", ");
                }
            }
        }
    }

    private void populateDescriptionTextViewUI (String description) {
        TextView descriptionTextView = findViewById(R.id.description_tv);
        TextView descriptionLabelTextView = findViewById(R.id.detail_description_label);

        if (description == null || description.isEmpty()) {
            descriptionTextView.setVisibility(View.GONE);
            descriptionLabelTextView.setVisibility(View.GONE);
        } else {
            descriptionTextView.setText(description);
        }
    }

    private void populatePlaceOfOriginTextViewUI (String placeOfOrigin) {
        TextView placeOfOriginTextView = findViewById(R.id.origin_tv);
        TextView placeOfOriginLabelTextView = findViewById(R.id.detail_place_of_origin_label);

        if (placeOfOrigin == null || placeOfOrigin.isEmpty()) {
            placeOfOriginTextView.setVisibility(View.GONE);
            placeOfOriginLabelTextView.setVisibility(View.GONE);
        } else {
            placeOfOriginTextView.setText(placeOfOrigin);
        }
    }

    private void populateIngredientsTextViewUI (List<String> ingredients) {
        TextView ingredientsTextView = findViewById(R.id.ingredients_tv);
        TextView ingredientsLabelTextView = findViewById(R.id.detail_ingredients_label);

        if (ingredients == null || ingredients.isEmpty()) {
            ingredientsTextView.setVisibility(View.GONE);
            ingredientsLabelTextView.setVisibility(View.GONE);
        } else {
            ingredientsTextView.setText("");

            int size = ingredients.size();
            for (int i = 0; i < size; i++) {
                ingredientsTextView.append(ingredients.get(i));

                if (i != size - 1) {
                    ingredientsTextView.append(", ");
                }
            }
        }
    }
}
