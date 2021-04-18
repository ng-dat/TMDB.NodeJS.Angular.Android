package com.example.tmdbandroid.screen.components.homeHorizontalMovieList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.tmdbandroid.DTOs.Item;
import com.example.tmdbandroid.R;

import java.util.List;


public class HorizontalRecycleViewAdapter
        extends RecyclerView.Adapter<HorizontalRecycleViewAdapter.MyView> {
    private List<Item> list;
    private Context context;

    public class MyView
            extends RecyclerView.ViewHolder {

        ImageView movieItemImageView;
        ImageButton moreBtn;

        public MyView(View view)
        {
            super(view);
            movieItemImageView = (ImageView) view
                    .findViewById(R.id.homeHorizontalListMovieItemImage);
            moreBtn = (ImageButton) view.findViewById(R.id.homeHorizontalListMovieItemMoreBtn);
        }
    }

    public HorizontalRecycleViewAdapter(Context context, List<Item> horizontalList)
    {
        this.context = context;
        this.list = horizontalList;
    }

    @Override
    public MyView onCreateViewHolder(ViewGroup parent,
                                     int viewType)
    {
        View itemView
                = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.home_movie_item_layout,
                        parent,
                        false);
        return new MyView(itemView);
    }

    @Override
    public void onBindViewHolder(final MyView viewHolder,
                                 final int position)
    {
        final Item sliderItem = list.get(position);
        Glide.with(viewHolder.itemView)
                .load(sliderItem.posterPath)
                .transform(new MultiTransformation<>(
                        new CenterCrop(), new RoundedCorners(20)
                ))
                .into(viewHolder.movieItemImageView);

        viewHolder.moreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(context, viewHolder.moreBtn);

                popupMenu.getMenuInflater().inflate(R.menu.home_movie_item_popup, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        // Toast message on menu item clicked
                        Toast.makeText(context, "You Clicked " + menuItem.getTitle() + " with Id " + menuItem.getItemId(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }
}