package com.example.Food.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


import com.example.Food.model.User;
import com.example.Food.util.Util;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper{

	public DatabaseHandler(@Nullable Context context){
		super(context, Util.DATABASE_NAME,null, Util.DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db){
		db.execSQL("CREATE TABLE " + Util.TABLE_NAME + "("
				+ Util.KEY_EMAIL + " TEXT PRIMARY KEY, "
				+ Util.KEY_FIRST_NAME + " TEXT, "
				+ Util.KEY_LAST_NAME + " TEXT, "
				+ Util.KEY_PASSWORD + " TEXT);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
		db.execSQL("DROP TABLE IF EXISTS " +  Util.TABLE_NAME);
		onCreate(db);
	}

	//Add user
	public void addUser(User user){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(Util.KEY_EMAIL, user.getEmail());
		values.put(Util.KEY_FIRST_NAME, user.getFirstName());
		values.put(Util.KEY_LAST_NAME, user.getLastName());
		values.put(Util.KEY_PASSWORD, user.getPassword());

		db.insert(Util.TABLE_NAME,null,values);
		db.close();
	}
	//Check if email exists
	public Boolean checkEmail(String email){
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("Select * from " + Util.TABLE_NAME + " where " + Util.KEY_EMAIL + "=?",
				new String[]{email});
		if(cursor.getCount()>0)
			return false;
		else
			return true;
	}
	//check if credentials are correct
	public Boolean checkUser(String email, String password){
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("Select * from " + Util.TABLE_NAME + " where " + Util.KEY_EMAIL + "=?" + " and " + Util.KEY_PASSWORD + "=?",
				new String[]{email,password});
		if(cursor.getCount()>0)
			return true;
		else
			return false;
	}
	//Delete Contact
	public void deleteUser(String email){
		SQLiteDatabase db = this.getWritableDatabase();

		db.delete(Util.TABLE_NAME,  Util.KEY_EMAIL +"=?",
				new String[]{email});
		db.close();
	}
	public int updateUser(User user){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();

		values.put(Util.KEY_EMAIL, user.getEmail());
		values.put(Util.KEY_FIRST_NAME, user.getFirstName());
		values.put(Util.KEY_LAST_NAME, user.getLastName());
		values.put(Util.KEY_PASSWORD, user.getPassword());

		return db.update(Util.TABLE_NAME, values, Util.KEY_EMAIL + "+?",
				new String[]{String.valueOf(user.getEmail())});
	}
	//Get User
	public User getUser(String email){
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(Util.TABLE_NAME,
				new String[]{Util.KEY_EMAIL,
						Util.KEY_FIRST_NAME,
						Util.KEY_LAST_NAME,
						Util.KEY_PASSWORD},
				Util.KEY_EMAIL +"=?",
				new String[]{email}, null, null, null, null);

		User user = new User();
		if(cursor != null){
			cursor.moveToFirst();
			user.setEmail(cursor.getString(cursor.getColumnIndex(Util.KEY_EMAIL)));
			user.setFirstName(cursor.getString(cursor.getColumnIndex(Util.KEY_FIRST_NAME)));
			user.setLastName(cursor.getString(cursor.getColumnIndex(Util.KEY_LAST_NAME)));
			user.setPassword(cursor.getString(cursor.getColumnIndex(Util.KEY_PASSWORD)));
		}
		db.close();
		return user;
	}
	//Get All Users
	public List<User> getAllUsers( ){
		SQLiteDatabase db = this.getReadableDatabase();
		List<User> userList = new ArrayList<>();
		Cursor cursor = db.query(Util.TABLE_NAME,
				new String[]{Util.KEY_EMAIL,
						Util.KEY_FIRST_NAME,
						Util.KEY_LAST_NAME,
						Util.KEY_PASSWORD}, null,
				null,null,null,null);

		if(cursor.moveToFirst()){
			do{
				User user = new User();
				cursor.moveToFirst();
				user.setEmail(cursor.getString(cursor.getColumnIndex(Util.KEY_EMAIL)));
				user.setFirstName(cursor.getString(cursor.getColumnIndex(Util.KEY_FIRST_NAME)));
				user.setLastName(cursor.getString(cursor.getColumnIndex(Util.KEY_LAST_NAME)));
				user.setPassword(cursor.getString(cursor.getColumnIndex(Util.KEY_PASSWORD)));
				userList.add(user);
			}while(cursor.moveToNext());

		}
		return userList;
	}
}

