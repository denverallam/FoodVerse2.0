package com.example.Food.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Food.R;

import java.util.Random;

public class Dish extends AppCompatActivity{
	String bFood[];
	String lFood[];
	String dFood[];
	String beFood[];
	String sFood[];
	String food[];
	int food_id,food_id2;
	ImageView home, cook, account;
	TextView featuredText,featuredText2;
	ImageView featuredImage,featuredImage2;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fdish);

		account = findViewById(R.id.account_image);
		cook = findViewById(R.id.cook_image);
		home = findViewById(R.id.category_image);

		featuredImage = findViewById(R.id.featuredImage);
		featuredText = findViewById(R.id.featuredText);
		featuredImage2 = findViewById(R.id.featuredImage2);
		featuredText2 = findViewById(R.id.featuredText2);

		int images[] =  {
				R.drawable.breakfast_b1,R.drawable.breakfast_b2,
				R.drawable.breakfast_b3,R.drawable.breakfast_b4,
				R.drawable.lunch_l1,R.drawable.lunch_l2,
				R.drawable.lunch_l2,R.drawable.lunch_l4,
				R.drawable.dinner_d1,R.drawable.dinner_d2,
				R.drawable.dinner_d3,R.drawable.dinner_d4,
				R.drawable.beverage_b1,R.drawable.beverage_b2,
				R.drawable.beverage_b3,R.drawable.beverage_b4,
				R.drawable.snack_s1,R.drawable.snack_s2,
				R.drawable.snack_s3,R.drawable.snack_s4
		};
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
		getRandomId();
		featuredText.setText(food[food_id]);
		featuredImage.setImageResource(images[food_id]);
		featuredText2.setText(food[food_id2]);
		featuredImage2.setImageResource(images[food_id2]);


		account.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				startActivity(new Intent(getApplicationContext(),Account.class));
				finish();
			}
		});
		home.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				startActivity(new Intent(getApplicationContext(), MainActivity.class));
				finish();
			}
		});
		cook.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				startActivity(new Intent(getApplicationContext(), Cook.class));
				finish();
			}
		});

		featuredImage.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				Intent intent = new Intent(getApplicationContext(), FoodProfile.class);
				intent.putExtra("id",food_id);
				startActivity(intent);
				finish();
			}
		});
		featuredImage2.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				Intent intent = new Intent(getApplicationContext(), FoodProfile.class);
				intent.putExtra("id",food_id2);
				startActivity(intent);
				finish();
			}
		});



	}
	void getRandomId(){
		Random random = new Random();
		food_id = random.nextInt(20);
		food_id2 = random.nextInt(20);
		if(food_id==food_id2){
			food_id2 = random.nextInt(20);
		}
	}
}