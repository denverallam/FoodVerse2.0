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
	private EditText name, email;
	Login log = new Login();

	ArrayList<Food> foodArrayList =  new ArrayList<>();

	int images[];
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account);

		recyclerView = findViewById(R.id.like_recycler);
		imageView = findViewById(R.id.like_image);
		textView = findViewById(R.id.like_text);
		FoodDatabaseHandler db = new FoodDatabaseHandler(this);
		DatabaseHandler data = new DatabaseHandler(this);
		int position = getIntent().getIntExtra("POSITION",0);
		save = findViewById(R.id.save_button);
		edit = findViewById(R.id.edit_image);
		name = findViewById(R.id.name_text);

		email = findViewById(R.id.email_text);
		save.setVisibility(View.INVISIBLE);
		User user = data.getUser(log.userEmail);
		name.setText(user.getFirstName());
		email.setText(user.getEmail());

		edit.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				name.setFocusableInTouchMode(true);
				email.setFocusableInTouchMode(true);

				save.setVisibility(View.VISIBLE);
			}
		});

		save.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				name.setText(name.getText());
				email.setText(email.getText());

				name.setFocusable(false);
				email.setFocusable(false);

				save.setVisibility(View.INVISIBLE);
			}
		});
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
		List<Food> foodList = db.getAllFood("denverallam");
		for(Food food: foodList){
			foodArrayList.add(food);
		}

		CartAdapter myAdapter = new CartAdapter(this, foodArrayList,images);
		recyclerView.setAdapter(myAdapter);
		recyclerView.setHasFixedSize(true);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
	}
}