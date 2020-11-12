package com.example.Food;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Food.app.Cart;

public class Grid extends AppCompatActivity implements View.OnClickListener{

	ImageView header, food1_image, food2_image, food3_image, food4_image;
	Button food_cart1, food_cart2, food_cart3, food_cart4,cart;
	TextView food1_text, food2_text, food3_text, food4_text;
	String food[];
	int food_pos;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grid);

		int position = getIntent().getIntExtra("POSITION",0);
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
		int position = getIntent().getIntExtra("POSITION",0);
		intent.putExtra("category",position);
		switch(v.getId()){
			case R.id.food_cart1:
				food_pos = 0;
				break;
			case R.id.food_cart2:
				food_pos = 1;
				break;
			case R.id.food_cart3:
				food_pos = 2;
				break;
			case R.id.food_cart4:
				food_pos = 3;
				break;
			case R.id.cart:
				intent.putExtra("food_position",food_pos);
				startActivity(intent);
				break;
		}

	}
}