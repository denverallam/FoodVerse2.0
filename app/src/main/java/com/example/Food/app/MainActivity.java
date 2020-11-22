package com.example.Food.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.Food.adapter.MyAdapter;
import com.example.Food.R;

public class MainActivity extends AppCompatActivity{

	RecyclerView recyclerView;
	ImageView account;
	int images[] = {
			R.drawable.breakfast,R.drawable.lunch,
			R.drawable.dinner,R.drawable.beverage,
			R.drawable.snack};
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		account = findViewById(R.id.account_image);
		recyclerView = findViewById(R.id.recyclerView);

		MyAdapter myAdapter = new MyAdapter(this,images);
		recyclerView.setAdapter(myAdapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));

		account.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				startActivity(new Intent(getApplicationContext(),Account.class));
				finish();
			}
		});
	}
}