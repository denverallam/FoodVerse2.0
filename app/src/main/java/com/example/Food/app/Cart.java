package com.example.Food.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Food.adapter.CartAdapter;
import com.example.Food.R;
import com.example.Food.data.FoodDatabaseHandler;
import com.example.Food.model.Food;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Cart extends AppCompatActivity{
	RecyclerView recyclerView;
	ImageView imageView;
	TextView textView;

	ArrayList<Food> foodArrayList =  new ArrayList<>();

	int images[];

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cart);

		recyclerView = findViewById(R.id.cart_recycler);
		imageView = findViewById(R.id.food_image);
		textView = findViewById(R.id.food_text);
		FoodDatabaseHandler db = new FoodDatabaseHandler(this);
		int position = getIntent().getIntExtra("POSITION",0);

//		switch(category){
//			case 0:
//				foodArray = getResources().getStringArray(R.array.breakfast);
//				switch(position){
//					case 0:
//						image = R.drawable.breakfast_b1;
//						food = foodArray[0];
//						break;
//					case 1:
//						food = foodArray[1];
//						image = R.drawable.breakfast_b2;
//						break;
//					case 2:
//						food = foodArray[2];
//						image = R.drawable.breakfast_b3;
//						break;
//					case 3:
//						food = foodArray[3];
//						image = R.drawable.breakfast_b4;
//						break;
//				}
//				break;
//			case 1:
//				foodArray = getResources().getStringArray(R.array.lunch);
//				switch(position){
//					case 0:
//						food = foodArray[0];
//						image = R.drawable.lunch_l1;
//						break;
//					case 1:
//						food = foodArray[1];
//						image = R.drawable.lunch_l2;
//						break;
//					case 2:
//						food = foodArray[2];
//						image = R.drawable.lunch_l3;
//						break;
//					case 3:
//						food = foodArray[3];
//						image = R.drawable.lunch_l4;
//						break;
//				}
//				break;
//			case 2:
//				foodArray = getResources().getStringArray(R.array.dinner);
//				switch(position){
//					case 0:
//						food = foodArray[0];
//						image = R.drawable.dinner_d1;
//						break;
//					case 1:
//						food = foodArray[1];
//						image = R.drawable.dinner_d2;
//						break;
//					case 2:
//						food = foodArray[2];
//						image = R.drawable.dinner_d3;
//						break;
//					case 3:
//						food = foodArray[3];
//						image = R.drawable.dinner_d4;
//						break;
//				}
//				break;
//			case 3:
//				foodArray = getResources().getStringArray(R.array.beverage);
//				switch(position){
//					case 0:
//						food = foodArray[0];
//						image = R.drawable.beverage_b1;
//						break;
//					case 1:
//						food = foodArray[1];
//						image = R.drawable.beverage_b2;
//						break;
//					case 2:
//						food = foodArray[2];
//						image = R.drawable.beverage_b3;
//						break;
//					case 3:
//						food = foodArray[3];
//						image = R.drawable.beverage_b4;
//						break;
//				}
//				break;
//			case 4:
//				foodArray = getResources().getStringArray(R.array.snack);
//				switch(position){
//					case 0:
//						food = foodArray[0];
//						image = R.drawable.snack_s1;
//						break;
//					case 1:
//						food = foodArray[1];
//						image = R.drawable.snack_s2;
//						break;
//					case 2:
//						food = foodArray[2];
//						image = R.drawable.snack_s3;
//						break;
//					case 3:
//						food = foodArray[3];
//						image = R.drawable.snack_s4;
//						break;
//				}
//				break;
//		}
		switch(position){
			case 0:
				int breakfast[] = {
						R.drawable.breakfast_b1,R.drawable.breakfast_b2,
						R.drawable.breakfast_b3,R.drawable.breakfast_b4,};
				images = breakfast;
				break;
			case 1:
				int lunch[] = {
						R.drawable.lunch_l1,R.drawable.lunch_l2,
						R.drawable.lunch_l3,R.drawable.lunch_l4,};
				images = lunch;
				break;
			case 2:
			int dinner[] = {
					R.drawable.dinner_d1,R.drawable.dinner_d2,
					R.drawable.dinner_d3,R.drawable.dinner_d4,};
			images = dinner;
				break;
			case 3:
				int beverage[] = {
						R.drawable.beverage_b1,R.drawable.beverage_b2,
						R.drawable.beverage_b3,R.drawable.beverage_b4,};
				images = beverage;
				break;
			case 4:
				int snack[] = {
						R.drawable.snack_s1,R.drawable.snack_s2,
						R.drawable.snack_s3,R.drawable.snack_s4};
				images = snack;
				break;
		}
		List<Food> foodList = db.getAllFood();
		for(Food food: foodList){
			foodArrayList.add(food);
		}

		Log.d("Size", String.valueOf(foodArrayList.size()));
		CartAdapter myAdapter = new CartAdapter(this,foodArrayList,images);
		recyclerView.setAdapter(myAdapter);
		recyclerView.setHasFixedSize(true);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
	}
}