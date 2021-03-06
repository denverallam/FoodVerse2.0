package com.example.Food.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Food.R;
import com.example.Food.data.DatabaseHandler;
import com.example.Food.model.User;
import com.google.android.material.snackbar.Snackbar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Signup extends AppCompatActivity{
	private Button createAccountButton;
	private EditText emailText;
	private EditText firstNameText;
	private EditText lastNameText;
	private EditText passwordText;
	private EditText confirmText;

	DatabaseHandler db;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);

		createAccountButton = findViewById(R.id.button_createAccount);
		emailText = findViewById(R.id.register_email);
		firstNameText = findViewById(R.id.register_fname);
		lastNameText = findViewById(R.id.register_lname);
		passwordText = findViewById(R.id.register_password);
		confirmText = findViewById(R.id.register_confirm);
		db = new DatabaseHandler(this);


		createAccountButton.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				String email = emailText.getText().toString();
				String password = passwordText.getText().toString();
				String firstName = firstNameText.getText().toString();
				String lastName = lastNameText.getText().toString();
				String confirmPassword = confirmText.getText().toString();

				//Check if user input is empty
				if(email.equals("") || password.equals("") || firstName.equals("") || lastName.equals("") || confirmPassword.equals("")){
					Toast.makeText(getApplicationContext(), "Fields are empty!", Toast.LENGTH_SHORT).show();
				}
				else{//if not empty
					if(!isEmailValid(email) || !isPasswordValid(password)){//check if both email and password are valid
						if(!isEmailValid(email)){//if email is not valid
							Snackbar.make(v, "\"Username must have at least 6 alphanumeric values!\"", Snackbar.LENGTH_LONG).show();
						}
						else{//if password is not valid
							Snackbar.make(v, "Password must have at least 8 alphanumeric values!", Snackbar.LENGTH_LONG).show();
						}
					}
					else{//if user input is valid, and both email and password are valid
						if(password.equals(confirmPassword)){//check if 2 passwords match
							Boolean checkEmail = db.checkEmail(email);
							if(checkEmail==true){//check if email already exists
								User user = new User();
								user.setEmail(email);
								user.setFirstName(firstName);
								user.setLastName(lastName);
								user.setPassword(password);
								db.addUser(user);
								Snackbar.make(v, "Account Registered!", Snackbar.LENGTH_SHORT).show();
								finish();
							}
							else{//email is already used
								Snackbar.make(v, "Email is already used!", Snackbar.LENGTH_SHORT).show();
							}
						}
						else {//password does not match
							Snackbar.make(v, "Password do not match!", Snackbar.LENGTH_SHORT).show();
						}
					}
				}
			}
		});
	}
	public boolean isPasswordValid(String password){
		//		Regex for valid password
		//		^(?=.*[0-9]) = Password must contain a number
		//		(?=.*[a-z])(?=.*[A-Z]) = Password must contain a lower case and upper case alphabet
		//		{8,20} = Password must be at least 8 characters and at most 20
		String regex = "^(?=.*[0-9])"+"(?=.*[a-z])"+".{8,20}$";

		//Compile the REGEX
		Pattern pattern = Pattern.compile(regex);
		Matcher match = pattern.matcher(password);
		return	match.matches();
	}
	//		Function to check if Email conforms with REGEX
	public boolean isEmailValid(String email){
		//		Regex for valid email
		String regex = "^(?=.*[0-9])"+"(?=.*[a-z])"+".{6,20}$";

		//Compile the REGEX
		Pattern pattern = Pattern.compile(regex);
		Matcher match = pattern.matcher(email);
		return	match.matches();
	}
}