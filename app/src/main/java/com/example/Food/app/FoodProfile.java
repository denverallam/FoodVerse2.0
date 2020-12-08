package com.example.Food.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Food.R;
import com.example.Food.data.FoodDatabaseHandler;
import com.example.Food.model.Food;
import com.google.android.material.snackbar.Snackbar;

public class FoodProfile extends AppCompatActivity{

	String steps[];
	String ingredients[];
	int images [];
	Login log = new Login();
	TextView foodName, foodSteps, foodIngredients;
	ImageView header;
	String bFood[];
	String lFood[];
	String dFood[];
	String beFood[];
	String sFood[];
	String food[];
	int food_id;

	ImageView like, cook;
	FoodDatabaseHandler db = new FoodDatabaseHandler(this);
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_food_profile);

		foodName = findViewById(R.id.foodName);
		foodSteps = findViewById(R.id.food_steps);
		foodIngredients = findViewById(R.id.food_ingredients);
		header = findViewById(R.id.prof_header);

		like = findViewById(R.id.like);
		cook = findViewById(R.id.cook);

		//Store "steps" string array resources from string.xml values to String array
		steps = getResources().getStringArray(R.array.steps);

		//Store "ingredients" string array resources from string.xml values to String array
		ingredients = getResources().getStringArray(R.array.ingredients);



		final String email = log.userEmail;
		bFood = getResources().getStringArray(R.array.breakfast);
		lFood = getResources().getStringArray(R.array.lunch);
		dFood = getResources().getStringArray(R.array.dinner);
		beFood = getResources().getStringArray(R.array.beverage);
		sFood = getResources().getStringArray(R.array.snack);

		food = new String[]{bFood[0],bFood[1],bFood[2],bFood[3],
				lFood[0],lFood[1],lFood[2],lFood[3],
				dFood[0],dFood[1],dFood[2],dFood[3],
				beFood[0],beFood[1],beFood[2],beFood[3],
				sFood[0],sFood[1],sFood[2],sFood[3],
		};
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
		food_id = getIntent().getIntExtra("id",0);
		//Set steps and ingredients text using id ---> id being 0-19
		foodName.setText(food[food_id]);
		foodSteps.setText(steps[food_id]);
		foodIngredients.setText(ingredients[food_id]);

		//Set image using id ---> id being 0-19
		header.setImageResource(images[food_id]);


		cook.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				if(db.checkFood(email,food[food_id])){//if food is aleady added, display snackbar message
					Snackbar.make(v,food[food_id] + " already added", Snackbar.LENGTH_SHORT).show();
				}
				else{//add food to cart database
					db.addFood(new Food(email,food[food_id],steps[food_id],ingredients[food_id],food_id));
					Snackbar.make(v, food[food_id] + " added!", Snackbar.LENGTH_SHORT).show();
				}
			}
		});
		like.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				if(db.checkLikes(email,food[food_id])){//if food is aleady added, display snackbar message
					Snackbar.make(v,food[food_id] + " already added", Snackbar.LENGTH_SHORT).show();
				}
				else{//add food to cart database
					db.addFoodToLikes(new Food(email,food[food_id],food_id));
					Snackbar.make(v, food[food_id] + " added!", Snackbar.LENGTH_SHORT).show();
				}
			}
		});
	}
}