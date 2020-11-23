package com.example.Food.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Food.R;
import com.example.Food.data.DatabaseHandler;
import com.example.Food.model.User;
import com.google.android.material.snackbar.Snackbar;

public class Login extends AppCompatActivity{
	public static String userEmail;
	private TextView signup;
	private Button login;
	private EditText emailText;
	private EditText passwordText;
	DatabaseHandler db;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		login = findViewById(R.id.button_login);
		signup = findViewById(R.id.text_signup);
		emailText = findViewById(R.id.login_email);
		passwordText = findViewById(R.id.login_password);
		db = new DatabaseHandler(this);

		signup.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				startActivity(new Intent(Login.this, Signup.class));
			}
		});

		login.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				String email = emailText.getText().toString();
				String password = passwordText.getText().toString();

				Boolean userExists = db.checkUser(email,password);
				//If email and password is correct (email and password exist in database); proceed
				if(userExists==true){

					//Get email of the user - to be used in database
					userEmail = email;
					//get user with email == input of user
					User user = db.getUser(email);
					user.setEmail(email);

					Snackbar.make(v, "WELCOME " + user.getFirstName().toUpperCase(), Snackbar.LENGTH_LONG).show();
					startActivity(new Intent(Login.this,MainActivity.class));
					finish();
				}
				else{
					//If email or password is incorrect, show snackbar message
					Snackbar.make(v, "Email/Password incorrect!!", Snackbar.LENGTH_SHORT).show();
					emailText.setText("");
					passwordText.setText("");
				}
			}
		});
	}
}