package com.example.Food.model;

public class Food{
	private int id;
	private String foodName;
	private int foodNumber;

	public Food(){
	}

	public Food(String foodName, int foodNumber){
		this.foodName = foodName;
		this.foodNumber = foodNumber;
	}

	public Food(int id, String foodName, int foodNumber){
		this.id = id;
		this.foodName = foodName;
		this.foodNumber = foodNumber;
	}

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	public String getFoodName(){
		return foodName;
	}

	public void setFoodName(String foodName){
		this.foodName = foodName;
	}

	public int getFoodNumber(){
		return foodNumber;
	}

	public void setFoodNumber(int foodNumber){
		this.foodNumber = foodNumber;
	}
}
