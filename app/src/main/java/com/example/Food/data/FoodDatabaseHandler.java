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

import java.util.ArrayList;
import java.util.List;

public class FoodDatabaseHandler extends SQLiteOpenHelper{

	public FoodDatabaseHandler(@Nullable Context context){
		super(context, FoodUtil.DATABASE_NAME, null, FoodUtil.DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db){
		String CREATE_FOOD_TABLE = "CREATE TABLE " + FoodUtil.TABLE_NAME + "(" + FoodUtil.KEY_ID + " INTEGER PRIMARY KEY, "
				+ FoodUtil.FOOD_NAME + " TEXT, " + FoodUtil.FOOD_NUMBER + " INTEGER" + ")";

		db.execSQL(CREATE_FOOD_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
		db.execSQL("DROP TABLE IF EXISTS " +  FoodUtil.TABLE_NAME);

		//Create a table again
		onCreate(db);
	}
	public void addFood(Food food){
		SQLiteDatabase db = this.getWritableDatabase();
		Log.d("Database", "onCreate: Added");
		ContentValues values = new ContentValues();
		values.put(FoodUtil.FOOD_NAME, food.getFoodName());
		values.put(FoodUtil.FOOD_NUMBER, food.getFoodNumber());

		//Insert to row
		db.insert(FoodUtil.TABLE_NAME, null, values);
		db.close(); //close db connection
	}
	public List<Food> getAllFood(){
		List<Food> foodList = new ArrayList<>();
		SQLiteDatabase db = this.getReadableDatabase();
		//Select all contacts
		String selectAll = "SELECT * FROM " + FoodUtil.TABLE_NAME;
		Cursor cursor = db.rawQuery(selectAll, null);

		//Loop through the data
		if(cursor.moveToFirst()){
			do{
				Food food = new Food();
				food.setId(Integer.parseInt(cursor.getString(0)));
				food.setFoodName(cursor.getString(1));
				food.setFoodNumber(cursor.getInt(2));

				//add contact objects to the list
				foodList.add(food);
			}
			while(cursor.moveToNext());
		}
		return foodList;
	}
	public void deleteFood(int id){
		SQLiteDatabase db = this.getReadableDatabase();
		db.delete(FoodUtil.TABLE_NAME, FoodUtil.KEY_ID + "=?",
				new String[]{String.valueOf(id)});
		db.close();
	}
}
