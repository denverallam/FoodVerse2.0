package com.example.Food.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Food.R;
import com.example.Food.adapter.CookAdapter;
import com.example.Food.data.DatabaseHandler;
import com.example.Food.data.FoodDatabaseHandler;
import com.example.Food.model.Food;
import com.example.Food.model.User;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class Account extends AppCompatActivity{
	RecyclerView recyclerView;
	ImageView imageView;
	ImageView home, cook, dish;
	TextView textView;
	private Button edit,save,cancel,logout;
	private EditText firstName, lastName;
	TextView email;
	String userEmail;
	Login log = new Login();
	DatabaseHandler data = new DatabaseHandler(this);

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
	//Instantiate an arraylist that contains list of food object
	ArrayList<Food> foodArrayList =  new ArrayList<>();
	@Override

	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account);

		Login login = new Login();

		//Get user email from Login Activity
		userEmail = login.userEmail;

		recyclerView = findViewById(R.id.like_recycler);
		imageView = findViewById(R.id.like_image);
		textView = findViewById(R.id.like_text);

		//Instantiate an object of database
		FoodDatabaseHandler db = new FoodDatabaseHandler(this);

		cancel = findViewById(R.id.cancel_button);
		save = findViewById(R.id.save_button);
		edit = findViewById(R.id.edit_image);
		logout = findViewById(R.id.logout);
		firstName = findViewById(R.id.firstName_text);
		lastName = findViewById(R.id.lastName_text);
		email = findViewById(R.id.email_text);

		//Save and Cancel button is set to invisibile by default
		save.setVisibility(View.INVISIBLE);
		cancel.setVisibility(View.INVISIBLE);

		//Fetch user details from Login Activity
		final User user = data.getUser(log.userEmail);

		//Display user details
		firstName.setText(user.getFirstName());
		lastName.setText(user.getLastName());
		email.setText(user.getEmail());

		home = findViewById(R.id.category_image);
		cook = findViewById(R.id.cook_image);
		dish = findViewById(R.id.dish_image);

		home.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				startActivity(new Intent(getApplicationContext(),MainActivity.class));
			}
		});
		dish.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				Snackbar.make(v, "Wala pang feature!!!!", Snackbar.LENGTH_SHORT).show();
			}
		});
		cook.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				Snackbar.make(v, "Wala pang feature!!!!", Snackbar.LENGTH_SHORT).show();
			}
		});
		edit.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				//Enable editing
				firstName.setFocusableInTouchMode(true);
				lastName.setFocusableInTouchMode(true);;

				//Show save and cancel button
				save.setVisibility(View.VISIBLE);
				cancel.setVisibility(View.VISIBLE);
				Log.d("Edit", "working");
			}
		});
		logout.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				startActivity(new Intent(getApplicationContext(), Login.class));
				finish();
			}
		});
		save.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				User newUser = new User();

				//Store new user details to newUser object
				newUser.setEmail(email.getText().toString());
				newUser.setFirstName(firstName.getText().toString());
				newUser.setLastName(lastName.getText().toString());
				newUser.setPassword(user.getPassword());

				//Check if user input is valid
				int isVerified =  verifyAccount(newUser.getFirstName(), newUser.getLastName());
				switch(isVerified){
					case 0: //Invalid input
						Snackbar.make(v,"Fields are empty!", Snackbar.LENGTH_SHORT).show();
						break;
					case 1: //Valid input
						//Update User's first name and last name
						data.updateUser(newUser);

						//Display updated profile
						firstName.setText(newUser.getFirstName());
						lastName.setText(newUser.getLastName());

						//Disable EditText for name
						firstName.setFocusable(false);
						lastName.setFocusable(false);

						//Hide save and cancel button
						save.setVisibility(View.INVISIBLE);
						cancel.setVisibility(View.INVISIBLE);
						break;
				}

			}
		});
		cancel.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				//Disable EditText for name
				firstName.setFocusable(false);
				lastName.setFocusable(false);

				//Hide save and cancel button
				save.setVisibility(View.INVISIBLE);
				cancel.setVisibility(View.INVISIBLE);
			}
		});

		//Store all food from cart and pass it to foodArrayList
		List<Food> foodList = db.getAllFood(userEmail);
		for(Food food: foodList){
			foodArrayList.add(food);
		}

		//Inflate the data of foodArrayList to Recyclervieww Adapter
		CookAdapter myAdapter = new CookAdapter(this, foodArrayList,images);
		recyclerView.setAdapter(myAdapter);
		recyclerView.setHasFixedSize(true);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
	}

	//Check if user input has string
	public int verifyAccount(String firstname, String lastname){
		if(firstname.equals("") || lastname.equals("")){
			return 0; //Invalid input
		}
		else{
			return 1; //Valid Input
		}
	}
}