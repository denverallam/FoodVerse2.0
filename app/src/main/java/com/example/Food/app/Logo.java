package com.example.Food.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


import com.example.Food.R;

public class Logo extends AppCompatActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logo);
		Handler handler = new Handler();

		handler.postDelayed(new Runnable() {
			public void run() {
				startActivity(new Intent(getApplicationContext(), Login.class));
				finish();
			}
		}, 1500);
	}
}