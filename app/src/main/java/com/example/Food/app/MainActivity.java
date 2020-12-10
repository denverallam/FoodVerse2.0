package com.example.Food.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.Food.adapter.MyAdapter;
import com.example.Food.R;
import com.example.Food.data.DatabaseHandler;
import com.example.Food.model.Food;
import com.example.Food.model.User;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity{

	RecyclerView recyclerView;
	ImageView account, cook, dish;
//	DatabaseHandler db = new DatabaseHandler(this);
//	Login login = new Login();

	//Store category images in int array
	int images[] = {
			R.drawable.breakfast,R.drawable.lunch,
			R.drawable.dinner,R.drawable.beverage,
			R.drawable.snack};
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

//		User user = db.getUser(		login.userEmail);
//		Toast.makeText(getApplicationContext(),"WELCOME " + user.getFirstName().toUpperCase(), Toast.LENGTH_LONG).show();

		account = findViewById(R.id.account_image);
		cook = findViewById(R.id.cook_image);
		dish = findViewById(R.id.dish_image);
		recyclerView = findViewById(R.id.recyclerView);

		//Pass int array containg category images to RecyclerView
		MyAdapter myAdapter = new MyAdapter(this,images);
		recyclerView.setAdapter(myAdapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));

		account.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				startActivity(new Intent(getApplicationContext(),Account.class));
			}
		});
		dish.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				startActivity(new Intent(getApplicationContext(), Dish.class));
			}
		});
		cook.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				startActivity(new Intent(getApplicationContext(), Cook.class));
			}
		});
	}
}