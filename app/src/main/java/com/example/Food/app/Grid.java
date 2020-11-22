package com.example.Food.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.Food.R;
import com.example.Food.data.FoodDatabaseHandler;
import com.example.Food.model.Food;

import java.util.ArrayList;
import java.util.List;

public class Grid extends AppCompatActivity implements View.OnClickListener{

	ImageView header, food1_image, food2_image, food3_image, food4_image;
	Button food_cart1, food_cart2, food_cart3, food_cart4,cart;
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
		cart = findViewById(R.id.cart);

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

		List<Food> foodList = db.getAllFood(email);
		for(Food food: foodList){
			foodArrayList.add(food);
		}
		cart.setOnClickListener(this);

		switch(position){
			case 0:
				header.setImageResource(R.drawable.breakfast_header);
				food1_image.setImageResource(R.drawable.breakfast_b1);
				food2_image.setImageResource(R.drawable.breakfast_b2);
				food3_image.setImageResource(R.drawable.breakfast_b3);
				food4_image.setImageResource(R.drawable.breakfast_b4);

				food = getResources().getStringArray(R.array.breakfast);
				food1_text.setText(food[0]);
				food2_text.setText(food[1]);
				food3_text.setText(food[2]);
				food4_text.setText(food[3]);
				break;
			case 1:
				header.setImageResource(R.drawable.lunch_header);
				food1_image.setImageResource(R.drawable.lunch_l1);
				food2_image.setImageResource(R.drawable.lunch_l2);
				food3_image.setImageResource(R.drawable.lunch_l3);
				food4_image.setImageResource(R.drawable.lunch_l4);

				food = getResources().getStringArray(R.array.lunch);
				food1_text.setText(food[0]);
				food2_text.setText(food[1]);
				food3_text.setText(food[2]);
				food4_text.setText(food[3]);

				break;
			case 2:
				header.setImageResource(R.drawable.dinner_header);
				food1_image.setImageResource(R.drawable.dinner_d1);
				food2_image.setImageResource(R.drawable.dinner_d2);
				food3_image.setImageResource(R.drawable.dinner_d3);
				food4_image.setImageResource(R.drawable.dinner_d4);

				food = getResources().getStringArray(R.array.dinner);
				food1_text.setText(food[0]);
				food2_text.setText(food[1]);
				food3_text.setText(food[2]);
				food4_text.setText(food[3]);
				break;
			case 3:
				header.setImageResource(R.drawable.beverage_header);
				food1_image.setImageResource(R.drawable.beverage_b1);
				food2_image.setImageResource(R.drawable.beverage_b2);
				food3_image.setImageResource(R.drawable.beverage_b3);
				food4_image.setImageResource(R.drawable.beverage_b4);

				food = getResources().getStringArray(R.array.beverage);
				food1_text.setText(food[0]);
				food2_text.setText(food[1]);
				food3_text.setText(food[2]);
				food4_text.setText(food[3]);
				break;
			case 4:
				header.setImageResource(R.drawable.snack_header);
				food1_image.setImageResource(R.drawable.snack_s1);
				food2_image.setImageResource(R.drawable.snack_s2);
				food3_image.setImageResource(R.drawable.snack_s3);
				food4_image.setImageResource(R.drawable.snack_s4);

				food = getResources().getStringArray(R.array.snack);
				food1_text.setText(food[0]);
				food2_text.setText(food[1]);
				food3_text.setText(food[2]);
				food4_text.setText(food[3]);
				break;
		}
	}
	@Override
	public void onClick(View v){
		Intent intent = new Intent(Grid.this, Cart.class);
		int category = getIntent().getIntExtra("CATEGORY",0);
		int position;
		switch(category){
			case 0:
				food_pos = 0;
				break;
			case 1:
				food_pos = 4;
				break;
			case 2:
				food_pos = 8;
				break;
			case 3:
				food_pos = 12;
				break;
			case 4:
				food_pos = 16;
				break;
		}
		openFoodProfile(v,food_pos);
		switch(v.getId()){
			case R.id.food_cart1:
				position = food_pos;
				if(db.checkFood(email,food[0])){
					Toast.makeText(getApplicationContext(),"Food already in cart!", Toast.LENGTH_SHORT).show();
				}
				else{
					db.addFood(new Food(email, food[0],position));
					Log.d("Position", String.valueOf(position) + " " + food[0]);
				}
				break;
			case R.id.food_cart2:
				position = food_pos + 1;
				if(db.checkFood(email,food[1])){
					Toast.makeText(getApplicationContext(),"Food already in cart!", Toast.LENGTH_SHORT).show();
				}
				else{
					db.addFood(new Food(email, food[1], position));
					Log.d("Position", String.valueOf(position) + " " + food[1]);
				}
				break;
			case R.id.food_cart3:
				position = food_pos + 2;
				if(db.checkFood(email,food[2])){
					Toast.makeText(getApplicationContext(),"Food already in cart!", Toast.LENGTH_SHORT).show();
				}
				else{
					db.addFood(new Food(email, food[2], position));
					Log.d("Position", String.valueOf(position) + " " + food[2]);
				}
				break;
			case R.id.food_cart4:
				position = food_pos + 3;
				if(db.checkFood(email,food[3])){
					Toast.makeText(getApplicationContext(),"Food already in cart!", Toast.LENGTH_SHORT).show();
				}
				else{
					db.addFood(new Food(email, food[3], position));
					Log.d("Position", String.valueOf(position) + " " + food[3]);
				}
				break;
			case R.id.cart:
				Log.d("Size", String.valueOf(foodArrayList.size()));
				startActivity(intent);
				break;
		}


	}
	public void openFoodProfile(View v, int food_pos){
		Intent intent = new Intent(getApplicationContext(), FoodProfile.class);
		switch(v.getId()){
			case R.id.food1_image:
				intent.putExtra("id", food_pos);
				Log.d("Position", String.valueOf(food_pos));
				startActivity(intent);
				break;
			case R.id.food2_image:
				intent.putExtra("id", food_pos + 1);
				Log.d("Position", String.valueOf(food_pos + 1));
				startActivity(intent);
				break;
			case R.id.food3_image:
				intent.putExtra("id", food_pos + 2);
				Log.d("Position", String.valueOf(food_pos + 2));
				startActivity(intent);
				break;
			case R.id.food4_image:
				intent.putExtra("id", food_pos + 3);
				Log.d("Position", String.valueOf(food_pos + 3));
				startActivity(intent);
				break;
		}
	}
}