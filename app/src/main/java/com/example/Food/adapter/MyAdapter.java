package com.example.Food.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Food.app.Grid;
import com.example.Food.R;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
	int images[];
	Context context;
	public MyAdapter(Context ctx,int img[]){
		context = ctx;
		images = img;
	}
	@NonNull
	@Override
	public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.row,parent,false);
		return new MyViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull final MyViewHolder holder, int position){
		holder.imageView.setImageResource(images[position]);
	}

	@Override
	public int getItemCount(){
		return images.length;
	}

	public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

		ImageView imageView;

		public MyViewHolder(@NonNull View itemView){
			super(itemView);
			imageView = itemView.findViewById(R.id.myImageView);
			imageView.setOnClickListener(this);
		}

		@Override
		public void onClick(View v){
			int position = getAdapterPosition();
			Intent intent = new Intent(context, Grid.class);
			intent.putExtra("CATEGORY",position);
			context.startActivity(intent);
		}
	}
}
