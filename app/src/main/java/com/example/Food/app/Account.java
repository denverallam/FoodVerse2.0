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
import android.widget.Toast;

import com.example.Food.R;
import com.example.Food.adapter.CartAdapter;
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
	Signup signup = new Signup();
	DatabaseHandler data = new DatabaseHandler(this);
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
	ArrayList<Food> foodArrayList =  new ArrayList<>();
	@Override

	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account);

		Login login = new Login();
		userEmail = login.userEmail;

		recyclerView = findViewById(R.id.like_recycler);
		imageView = findViewById(R.id.like_image);
		textView = findViewById(R.id.like_text);

		FoodDatabaseHandler db = new FoodDatabaseHandler(this);

		cancel = findViewById(R.id.cancel_button);
		save = findViewById(R.id.save_button);
		edit = findViewById(R.id.edit_image);
		logout = findViewById(R.id.logout);
		firstName = findViewById(R.id.firstName_text);
		lastName = findViewById(R.id.lastName_text);
		email = findViewById(R.id.email_text);

		save.setVisibility(View.INVISIBLE);
		cancel.setVisibility(View.INVISIBLE);

		final User user = data.getUser(log.userEmail);
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
				firstName.setFocusableInTouchMode(true);
				lastName.setFocusableInTouchMode(true);;
				save.setVisibility(View.VISIBLE);
				cancel.setVisibility(View.VISIBLE);
				Log.d("Edit", "working");
			}
		});
		logout.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				startActivity(new Intent(getApplicationContext(), Login.class));
			}
		});
		save.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				User newUser = new User();

				newUser.setEmail(email.getText().toString());
				newUser.setFirstName(firstName.getText().toString());
				newUser.setLastName(lastName.getText().toString());
				newUser.setPassword(user.getPassword());

				int isVerified =  verifyAccount(newUser.getFirstName(), newUser.getLastName());
				switch(isVerified){
					case 0:
						Snackbar.make(v,"Fields are empty!", Snackbar.LENGTH_SHORT).show();
						break;
					case 1:
						data.updateUser(newUser);

						firstName.setText(newUser.getFirstName());
						lastName.setText(newUser.getLastName());

						Log.d("Account", newUser.getEmail() + "\n" + newUser.getFirstName()
								+ "\n" + newUser.getLastName());

						firstName.setFocusable(false);
						lastName.setFocusable(false);
						save.setVisibility(View.INVISIBLE);
						cancel.setVisibility(View.INVISIBLE);
						break;
				}

			}
		});
		cancel.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				firstName.setFocusable(false);
				lastName.setFocusable(false);
				save.setVisibility(View.INVISIBLE);
				cancel.setVisibility(View.INVISIBLE);
			}
		});

		List<Food> foodList = db.getAllFood(userEmail);
		for(Food food: foodList){
			foodArrayList.add(food);
		}

		CartAdapter myAdapter = new CartAdapter(this, foodArrayList,images);
		recyclerView.setAdapter(myAdapter);
		recyclerView.setHasFixedSize(true);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
	}

	public int verifyAccount(String firstname, String lastname){
		if(firstname.equals("") || lastname.equals("")){
			return 0;
		}
		else{
			return 1;
		}
	}
}