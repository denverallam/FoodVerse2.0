package com.example.Food.model;

public class Food{
	private int id;
	private String foodName;
	private String foodEmail;
	private int foodNumber;
	private String foodSteps;
	private String foodIngredients;

	public Food(){
	}

	public Food(String foodName, int foodNumber){
		this.foodName = foodName;
		this.foodNumber = foodNumber;
	}
	public Food(String foodEmail,String foodName, String foodSteps, String foodIngredients, int foodNumber){
		this.foodEmail = foodEmail;
		this.foodName = foodName;
		this.foodSteps = foodSteps;
		this.foodIngredients = foodIngredients;
		this.foodNumber = foodNumber;
	}
	public Food(String email, String foodName, int foodNumber){
		this.foodEmail = email;
		this.foodName = foodName;
		this.foodNumber = foodNumber;
	}

	public Food(int id, String foodName, int foodNumber){
		this.id = id;
		this.foodName = foodName;
		this.foodNumber = foodNumber;
	}
	public Food(int id, String foodName, String email, int foodNumber){
		this.id = id;
		this.foodEmail = email;
		this.foodName = foodName;
		this.foodNumber = foodNumber;
	}
	public String getFoodEmail(){
		return foodEmail;
	}

	public void setFoodEmail(String foodEmail){
		this.foodEmail = foodEmail;
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

	public String getFoodSteps(){
		return foodSteps;
	}

	public void setFoodSteps(String foodSteps){
		this.foodSteps = foodSteps;
	}

	public String getFoodIngredients(){
		return foodIngredients;
	}

	public void setFoodIngredients(String foodIngredients){
		this.foodIngredients = foodIngredients;
	}
}
