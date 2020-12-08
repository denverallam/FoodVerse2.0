package com.example.Food.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.Food.model.Food;
import com.example.Food.util.FoodUtil;
import com.example.Food.util.Util;

import java.util.ArrayList;
import java.util.List;

public class FoodDatabaseHandler extends SQLiteOpenHelper{

	public FoodDatabaseHandler(@Nullable Context context){
		super(context, FoodUtil.DATABASE_NAME, null, FoodUtil.DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db){

		db.execSQL("CREATE TABLE " + FoodUtil.TABLE_NAME + "(" + FoodUtil.KEY_ID + " INTEGER PRIMARY KEY, " +
				FoodUtil.FOOD_EMAIL + " TEXT, " + FoodUtil.FOOD_NAME +  " TEXT, " +
				FoodUtil.FOOD_STEPS + " TEXT, " + FoodUtil.FOOD_INGREDIENTS + " TEXT, " +
				FoodUtil.FOOD_NUMBER + " INTEGER" + ")");
		db.execSQL("CREATE TABLE " + FoodUtil.TABLE_NAME_2 + "(" + FoodUtil.KEY_ID + " INTEGER PRIMARY KEY, " +
				FoodUtil.FOOD_EMAIL + " TEXT, " + FoodUtil.FOOD_NAME +  " TEXT, " +
				FoodUtil.FOOD_NUMBER + " INTEGER" + ")");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
		db.execSQL("DROP TABLE IF EXISTS " +  FoodUtil.TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " +  FoodUtil.TABLE_NAME_2);
		onCreate(db);

	}

	//Add food to cart
	public void addFood(Food food){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(FoodUtil.FOOD_EMAIL, food.getFoodEmail());
		values.put(FoodUtil.FOOD_NAME, food.getFoodName());
		values.put(FoodUtil.FOOD_STEPS, food.getFoodSteps());
		values.put(FoodUtil.FOOD_INGREDIENTS, food.getFoodIngredients());
		values.put(FoodUtil.FOOD_NUMBER, food.getFoodNumber());
		//Insert to row
		db.insert(FoodUtil.TABLE_NAME, null, values);
		db.close(); //close db connection
	}
	public void addFoodToLikes(Food food){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(FoodUtil.FOOD_EMAIL, food.getFoodEmail());
		values.put(FoodUtil.FOOD_NAME, food.getFoodName());
		values.put(FoodUtil.FOOD_NUMBER, food.getFoodNumber());
		//Insert to row
		db.insert(FoodUtil.TABLE_NAME_2, null, values);
		db.close(); //close db connection
	}

	//check if food exists
	public Boolean checkFood(String email, String name){
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM " + FoodUtil.TABLE_NAME + " WHERE " + FoodUtil.FOOD_EMAIL + "=?" + " AND " + FoodUtil.FOOD_NAME + "=?",
				new String[]{email,name});
		if(cursor.getCount()>0)
			return true;
		else
			return false;
	}
	public Boolean checkLikes(String email, String name){
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM " + FoodUtil.TABLE_NAME_2 + " WHERE " + FoodUtil.FOOD_EMAIL + "=?" + " AND " + FoodUtil.FOOD_NAME + "=?",
				new String[]{email,name});
		if(cursor.getCount()>0)
			return true;
		else
			return false;
	}

	//get all food added by the user
	public List<Food> getAllFood(String email){
		List<Food> foodList = new ArrayList<>();
		SQLiteDatabase db = this.getReadableDatabase();
		//Select all contacts
		String selectAll = "SELECT * FROM " + FoodUtil.TABLE_NAME + " WHERE " + FoodUtil.FOOD_EMAIL + "=?" + " ORDER BY " + FoodUtil.KEY_ID + " DESC";
		Cursor cursor = db.rawQuery(selectAll, new String[]{email} );

		//Loop through the data
		if(cursor.moveToFirst()){
			do{
				Food food = new Food();
				food.setId(Integer.parseInt(cursor.getString(0)));
				food.setFoodEmail(cursor.getString(1));
				food.setFoodName(cursor.getString(2));
				food.setFoodSteps(cursor.getString(3));
				food.setFoodIngredients(cursor.getString(4));
				food.setFoodNumber(cursor.getInt(5));

				//add contact objects to the list
				foodList.add(food);
			}
			while(cursor.moveToNext());
		}
		return foodList;
	}
	public List<Food> getAllLikes(String email){
		List<Food> foodList = new ArrayList<>();
		SQLiteDatabase db = this.getReadableDatabase();
		//Select all contacts
		String selectAll = "SELECT * FROM " + FoodUtil.TABLE_NAME_2 + " WHERE " + FoodUtil.FOOD_EMAIL + "=?" + " ORDER BY " + FoodUtil.KEY_ID + " DESC";
		Cursor cursor = db.rawQuery(selectAll, new String[]{email} );

		//Loop through the data
		if(cursor.moveToFirst()){
			do{
				Food food = new Food();
				food.setId(Integer.parseInt(cursor.getString(0)));
				food.setFoodEmail(cursor.getString(1));
				food.setFoodName(cursor.getString(2));
				food.setFoodNumber(cursor.getInt(3));

				//add contact objects to the list
				foodList.add(food);
			}
			while(cursor.moveToNext());
		}
		return foodList;
	}
	//delete food
	public void deleteFood(int id){
		SQLiteDatabase db = this.getReadableDatabase();
		db.delete(FoodUtil.TABLE_NAME, FoodUtil.KEY_ID + "=?",
				new String[]{String.valueOf(id)});
		db.close();
	}
	public void deleteLike(int id){
		SQLiteDatabase db = this.getReadableDatabase();
		db.delete(FoodUtil.TABLE_NAME_2, FoodUtil.KEY_ID + "=?",
				new String[]{String.valueOf(id)});
		db.close();
	}
	public int getLikesCount(){
		String countQuery = "SELECT * FROM " + FoodUtil.TABLE_NAME_2;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery,null);
		return cursor.getCount();
	}
}
