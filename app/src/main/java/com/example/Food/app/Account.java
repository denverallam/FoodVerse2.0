package com.example.Food.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Food.R;
import com.example.Food.adapter.CartAdapter;
import com.example.Food.data.DatabaseHandler;
import com.example.Food.data.FoodDatabaseHandler;
import com.example.Food.model.Food;
import com.example.Food.model.User;

import java.util.ArrayList;
import java.util.List;

public class Account extends AppCompatActivity{
	RecyclerView recyclerView;
	ImageView imageView;
	TextView textView;
	private Button edit,save;
	private EditText firstName, lastName, email;
	String userEmail;
	Login log = new Login();

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
		DatabaseHandler data = new DatabaseHandler(this);

		int position = getIntent().getIntExtra("POSITION",0);

		save = findViewById(R.id.save_button);
		edit = findViewById(R.id.edit_image);
		firstName = findViewById(R.id.firstName_text);
		firstName = findViewById(R.id.lastName_text);

		email = findViewById(R.id.email_text);
		save.setVisibility(View.INVISIBLE);

		User user = data.getUser(log.userEmail);
		firstName.setText(user.getFirstName());
		lastName.setText(user.getLastName());
		email.setText(user.getEmail());

		edit.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				firstName.setFocusableInTouchMode(true);
				lastName.setFocusableInTouchMode(true);
				email.setFocusableInTouchMode(true);
				save.setVisibility(View.VISIBLE);
			}
		});

		save.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				firstName.setText(firstName.getText());
				lastName.setText(firstName.getText());
				email.setText(email.getText());

				firstName.setFocusable(false);
				lastName.setFocusable(false);
				email.setFocusable(false);
				save.setVisibility(View.INVISIBLE);
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
}