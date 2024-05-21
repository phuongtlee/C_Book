package com.example.c_book.Favorite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c_book.R;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>{

    private List<Favorite> favoriteList;
    private Context context;
    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    public FavoriteAdapter(Context context) {
        this.context = context;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }


    public void setData(List<Favorite> list){
        this.favoriteList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        return new FavoriteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        Favorite favorite = favoriteList.get(position);
        if (favorite == null){
            return;
        }

        holder.imgBook.setImageResource(favorite.getResourceImg());
        holder.tvName.setText(favorite.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    listener.onItemClick(adapterPosition);
                }
            }
        });
    }

    @Override
    public int  getItemCount() {
        if (favoriteList != null){
            return favoriteList.size();
        }

        return 0;
    }

    public class FavoriteViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgBook;
        private TextView tvName;

        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);

            imgBook =itemView.findViewById(R.id.img_book);
            tvName =itemView.findViewById(R.id.tv_name);
        }
    }

    public Favorite getFavoriteAt(int position) {
        if (position >= 0 && position < favoriteList.size()) {
            return favoriteList.get(position);
        }
        return null;
    }
}
