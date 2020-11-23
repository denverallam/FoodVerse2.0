package com.example.Food.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Food.R;

public class FoodProfile extends AppCompatActivity{

	String steps[];
	String ingredients[];
	int images [];
	TextView foodSteps, foodIngredients;
	ImageView header;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_food_profile);

		foodSteps = findViewById(R.id.food_steps);
		foodIngredients = findViewById(R.id.food_ingredients);
		header = findViewById(R.id.prof_header);

		//Store "steps" string array resources from string.xml values to String array
		steps = getResources().getStringArray(R.array.steps);

		//Store "ingredients" string array resources from string.xml values to String array
		ingredients = getResources().getStringArray(R.array.ingredients);

		//Store images in int array
		//Alt + Click to view image
		//20 images --> id will be from 0-19
		images = new int[]{R.drawable.header_b1, R.drawable.header_b2,
				R.drawable.header_b3, R.drawable.header_b4,
				R.drawable.header_l1, R.drawable.header_l2,
				R.drawable.header_l3, R.drawable.header_l4,
				R.drawable.header_l1, R.drawable.header_l1,
				R.drawable.header_l1, R.drawable.header_l1,
				R.drawable.header_be1, R.drawable.header_be2,
				R.drawable.header_be3, R.drawable.header_be4,
				R.drawable.header_s1, R.drawable.header_s2,
				R.drawable.header_s3, R.drawable.header_s4,};

		//Fetch id of clicked image from Grid.class
		int food_id = getIntent().getIntExtra("id",0);
		//Set steps and ingredients text using id ---> id being 0-19
		foodSteps.setText(steps[food_id]);
		foodIngredients.setText(ingredients[food_id]);

		//Set image using id ---> id being 0-19
		header.setImageResource(images[food_id]);
	}
}