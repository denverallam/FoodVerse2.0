package com.example.Food.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Food.R;
import com.example.Food.data.FoodDatabaseHandler;
import com.example.Food.model.Food;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class Grid extends AppCompatActivity implements View.OnClickListener{

	ImageView header, food1_image, food2_image, food3_image, food4_image, cook;
	ImageView account, home,dish;
	Button food_cart1, food_cart2, food_cart3, food_cart4;
	TextView food1_text, food2_text, food3_text, food4_text;
	FoodDatabaseHandler db = new FoodDatabaseHandler(this);
	ArrayList<Food> foodArrayList = new ArrayList<>();
	String food[];
	String email;
	int food_pos = 10;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grid);

		int position = getIntent().getIntExtra("CATEGORY",0);
		Login login = new Login();
		email = login.userEmail;

		header = findViewById(R.id.imageView);
		food1_image = findViewById(R.id.food1_image);
		food2_image = findViewById(R.id.food2_image);
		food3_image = findViewById(R.id.food3_image);
		food4_image = findViewById(R.id.food4_image);

		food_cart1 = findViewById(R.id.food_cart1);
		food_cart2 = findViewById(R.id.food_cart2);
		food_cart3 = findViewById(R.id.food_cart3);
		food_cart4 = findViewById(R.id.food_cart4);

		account = findViewById(R.id.account_image);
		home = findViewById(R.id.category_image);
		cook = findViewById(R.id.cook_image);
		dish = findViewById(R.id.dish_image);

		food1_text = findViewById(R.id.food1_text);
		food2_text = findViewById(R.id.food2_text);
		food3_text = findViewById(R.id.food3_text);
		food4_text = findViewById(R.id.food4_text);

		food_cart1.setOnClickListener(this);
		food_cart2.setOnClickListener(this);
		food_cart3.setOnClickListener(this);
		food_cart4.setOnClickListener(this);

		food1_image.setOnClickListener(this);
		food2_image.setOnClickListener(this);
		food3_image.setOnClickListener(this);
		food4_image.setOnClickListener(this);

		account.setOnClickListener(this);
		home.setOnClickListener(this);
		dish.setOnClickListener(this);
		cook.setOnClickListener(this);

		//Store all food from database and store it to foodList
		List<Food> foodList = db.getAllFood(email);
		for(Food food: foodList){
			foodArrayList.add(food);
		}

		//Use different image and text resources depending on category clicked
		switch(position){ //position = category
			case 0: //breakfast
				//set breakfast image from drawable
				header.setImageResource(R.drawable.breakfast_header);
				food1_image.setImageResource(R.drawable.breakfast_b1);
				food2_image.setImageResource(R.drawable.breakfast_b2);
				food3_image.setImageResource(R.drawable.breakfast_b3);
				food4_image.setImageResource(R.drawable.breakfast_b4);

				//get breakfast string array value from string.xml
				food = getResources().getStringArray(R.array.breakfast);
				food1_text.setText(food[0]);
				food2_text.setText(food[1]);
				food3_text.setText(food[2]);
				food4_text.setText(food[3]);
				break;
			case 1: //lunch

				//set lunch image from drawable
				header.setImageResource(R.drawable.lunch_header);
				food1_image.setImageResource(R.drawable.lunch_l1);
				food2_image.setImageResource(R.drawable.lunch_l2);
				food3_image.setImageResource(R.drawable.lunch_l3);
				food4_image.setImageResource(R.drawable.lunch_l4);
				//get lunch string array value from string.xml
				food = getResources().getStringArray(R.array.lunch);
				food1_text.setText(food[0]);
				food2_text.setText(food[1]);
				food3_text.setText(food[2]);
				food4_text.setText(food[3]);

				break;
			case 2: //dinner
				//set dinner image from drawable
				header.setImageResource(R.drawable.dinner_header);
				food1_image.setImageResource(R.drawable.dinner_d1);
				food2_image.setImageResource(R.drawable.dinner_d2);
				food3_image.setImageResource(R.drawable.dinner_d3);
				food4_image.setImageResource(R.drawable.dinner_d4);
				//get dinner string array value from string.xml
				food = getResources().getStringArray(R.array.dinner);
				food1_text.setText(food[0]);
				food2_text.setText(food[1]);
				food3_text.setText(food[2]);
				food4_text.setText(food[3]);
				break;
			case 3: //beverage
				//set beverage image from drawable
				header.setImageResource(R.drawable.beverage_header);
				food1_image.setImageResource(R.drawable.beverage_b1);
				food2_image.setImageResource(R.drawable.beverage_b2);
				food3_image.setImageResource(R.drawable.beverage_b3);
				food4_image.setImageResource(R.drawable.beverage_b4);
				//get beverage string array value from string.xml
				food = getResources().getStringArray(R.array.beverage);
				food1_text.setText(food[0]);
				food2_text.setText(food[1]);
				food3_text.setText(food[2]);
				food4_text.setText(food[3]);
				break;
			case 4: //snack
				//set snack image from drawable
				header.setImageResource(R.drawable.snack_header);
				food1_image.setImageResource(R.drawable.snack_s1);
				food2_image.setImageResource(R.drawable.snack_s2);
				food3_image.setImageResource(R.drawable.snack_s3);
				food4_image.setImageResource(R.drawable.snack_s4);
				//get snack string array value from string.xml
				food = getResources().getStringArray(R.array.snack);
				food1_text.setText(food[0]);
				food2_text.setText(food[1]);
				food3_text.setText(food[2]);
				food4_text.setText(food[3]);
				break;
		}

		home.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				startActivity(new Intent(getApplicationContext(), MainActivity.class));
				finish();
			}
		});
		account.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				startActivity(new Intent(getApplicationContext(), Account.class));
				finish();
			}
		});

	}
	@Override
	public void onClick(View v){
		Intent intent = new Intent(Grid.this, Cart.class);
		//get value passed from MainActivity
		int category = getIntent().getIntExtra("CATEGORY",0);
		int position;
		switch(category){ //set id depending on category
			case 0://breakfast
				//id will start at 0
				food_pos = 0;
				//id will be 0-3
				break;
			case 1://lunch
				//id will start at 4
				food_pos = 4;
				//id will be 4-7
				break;
			case 2://breakfast
				//id will start at 8
				food_pos = 8;
				//id will be 8-9
				break;//breakfast
			//id will start at 0
			case 3://breakfast
				//id will start at 12
				food_pos = 12;
				//id will be 12-15
				break;
			case 4://breakfast
				//id will start at 16
				food_pos = 16;
				//id will be 16-19
				break;
		}
		openFoodProfile(v,food_pos);
		switch(v.getId()){
			case R.id.food_cart1:
				//if first food image is clicked, id == food_pos
				position = food_pos;
				if(db.checkFood(email,food[0])){//if food is aleady added, display snackbar message
					Snackbar.make(v,food[0] + " already in cart!", Snackbar.LENGTH_SHORT).show();
				}
				else{//add food to cart database
					db.addFood(new Food(email, food[0],position));
					Snackbar.make(v, food[0] + " added to cart!", Snackbar.LENGTH_SHORT).show();
				}
				break;
			case R.id.food_cart2:
				//if second food image is clicked, id == food_pos+1
				position = food_pos + 1;
				if(db.checkFood(email,food[1])){//if food is aleady added, display snackbar message
					Snackbar.make(v,food[1] + " already in cart!", Snackbar.LENGTH_SHORT).show();
				}
				else{//add food to cart database
					db.addFood(new Food(email, food[1], position));
					Snackbar.make(v, food[1] + " added to cart!", Snackbar.LENGTH_SHORT).show();
				}
				break;
			case R.id.food_cart3:
				//if third food image is clicked, id == food_pos + 2
				position = food_pos + 2;
				if(db.checkFood(email,food[2])){//if food is aleady added, display snackbar message
					Snackbar.make(v,food[2] + " already in cart!", Snackbar.LENGTH_SHORT).show();
				}
				else{//add food to cart database
					db.addFood(new Food(email, food[2], position));
					Snackbar.make(v, food[2] + " added to cart!", Snackbar.LENGTH_SHORT).show();
				}
				break;
			case R.id.food_cart4:
				//if fourth food image is clicked, id == food_pos + 3
				position = food_pos + 3;
				if(db.checkFood(email,food[3])){ //if food is aleady added, display snackbar message
					Snackbar.make(v,food[3] + " already in cart!", Snackbar.LENGTH_SHORT).show();
				}
				else{//add food to cart database
					db.addFood(new Food(email, food[3], position));
					Snackbar.make(v, food[3] + " added to cart!", Snackbar.LENGTH_SHORT).show();
				}
				break;
			case R.id.cook_image:
				startActivity(intent);
				break;
			case R.id.category_image:
				startActivity(new Intent(getApplicationContext(), MainActivity.class));
				finish();
				break;
			case R.id.dish_image:
				Snackbar.make(v, "Wala pang feature!!!!", Snackbar.LENGTH_SHORT).show();
				break;
			case R.id.account_image:
				startActivity(new Intent(getApplicationContext(), Account.class));
				break;
		}


	}
	public void openFoodProfile(View v, int food_pos){
		Intent intent = new Intent(getApplicationContext(), FoodProfile.class);
		switch(v.getId()){
			//opens FoodCategory class and display food profile for each food
			//passes id to be used for image and string values stored in FoodCategory class
			case R.id.food1_image: //pass food_pas value to FoodCategory class
				intent.putExtra("id", food_pos);
				startActivity(intent);
				break;
			case R.id.food2_image://pass food_pas+1 value to FoodCategory class
				intent.putExtra("id", food_pos + 1);
				startActivity(intent);
				break;
			case R.id.food3_image://pass food_pas+2 value to FoodCategory class
				intent.putExtra("id", food_pos + 2);
				startActivity(intent);
				break;
			case R.id.food4_image://pass food_pas+3 value to FoodCategory class
				intent.putExtra("id", food_pos + 3);
				startActivity(intent);
				break;
		}
	}
}