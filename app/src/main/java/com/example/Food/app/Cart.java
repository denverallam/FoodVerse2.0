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

	//Store images in int array
	//Alt + Click to view image
	int images[] =  {
			R.drawable.breakfast_b1,R.drawable.breakfast_b2,
			R.drawable.breakfast_b3,R.drawable.breakfast_b4,
			R.drawable.lunch_l1,R.drawable.lunch_l2,
			R.drawable.lunch_l3,R.drawable.lunch_l4,
			R.drawable.dinner_d1,R.drawable.dinner_d2,
			R.drawable.dinner_d3,R.drawable.dinner_d4,
			R.drawable.beverage_b1,R.drawable.beverage_b2,
			R.drawable.beverage_b3,R.drawable.beverage_b4,
			R.drawable.snack_s1,R.drawable.snack_s2,
			R.drawable.snack_s3,R.drawable.snack_s4
	};

	String email;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cart);

		Login login = new Login();
		email = login.userEmail;

		recyclerView = findViewById(R.id.cart_recycler);
		imageView = findViewById(R.id.food_image);
		textView = findViewById(R.id.food_text);

		FoodDatabaseHandler db = new FoodDatabaseHandler(this);

		List<Food> foodList = db.getAllFood(email);
		foodArrayList.addAll(foodList);

		CartAdapter myAdapter = new CartAdapter(this,foodArrayList,images);
		recyclerView.setAdapter(myAdapter);
		recyclerView.setHasFixedSize(true);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
	}
}