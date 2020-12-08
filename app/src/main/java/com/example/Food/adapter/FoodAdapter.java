package com.example.Food.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Food.R;
import com.example.Food.app.FoodProfile;
import com.example.Food.data.FoodDatabaseHandler;
import com.example.Food.model.Food;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder>{

	List<Food> foodList;
	int images[];
	Context context;

	public FoodAdapter(Context ctx, List<Food> food, int [] images){
		context = ctx;
		this.images = images;
		foodList = food;
	}

	@NonNull
	@Override
	public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.cart,parent,false);
		return new FoodViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull FoodViewHolder holder, int position){
		Food food = foodList.get(position);
		holder.foodText.setText(food.getFoodName());
		holder.foodImage.setImageResource(images[food.getFoodNumber()]); }


	@Override
	public int getItemCount(){
		return foodList.size();
	}


	public class FoodViewHolder extends RecyclerView.ViewHolder{
		ImageView foodImage, delete;
		TextView foodText;
		RelativeLayout recycler;

		public FoodViewHolder(@NonNull final View itemView){
			super(itemView);
			foodImage = itemView.findViewById(R.id.food_image);
			foodText = itemView.findViewById(R.id.food_text);
			recycler = itemView.findViewById(R.id.recycler);

			delete = itemView.findViewById(R.id.delete);

			recycler.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v){
					int position = getAdapterPosition();
					Food food = foodList.get(position);
					Intent intent = new Intent(context, FoodProfile.class);
					intent.putExtra("id", food.getFoodNumber());
					context.startActivity(intent);
				}
			});

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
