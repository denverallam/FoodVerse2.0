package com.example.Food.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Food.R;
import com.example.Food.data.FoodDatabaseHandler;
import com.example.Food.model.Food;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{

	List<Food> foodList;
	Context context;
	public CartAdapter(Context ctx, List<Food> food){
		context = ctx;
		foodList = food;
	}
	@NonNull
	@Override
	public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.cart,parent,false);
		return new CartViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull CartViewHolder holder, int position){
		Food food = foodList.get(position);
		holder.foodImage.setImageResource(food.getFoodNumber());
		holder.foodText.setText(food.getFoodName());
	}

	@Override
	public int getItemCount(){
		return foodList.size();
	}

	public class CartViewHolder extends RecyclerView.ViewHolder{
		ImageView foodImage, delete;
		TextView foodText;

		public CartViewHolder(@NonNull View itemView){
			super(itemView);
			foodImage = itemView.findViewById(R.id.food_image);
			foodText = itemView.findViewById(R.id.food_text);
			delete = itemView.findViewById(R.id.delete);

			delete.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v){
					int position = getAdapterPosition();
					Food food = foodList.get(position);
					FoodDatabaseHandler db = new FoodDatabaseHandler(context);
					db.deleteFood(food.getId());
					foodList.remove(getAdapterPosition());
					notifyDataSetChanged();
				}
			});
		}
	}
}
