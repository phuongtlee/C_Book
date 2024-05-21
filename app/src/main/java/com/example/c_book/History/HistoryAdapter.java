package com.example.c_book.History;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c_book.Favorite.Favorite;
import com.example.c_book.Favorite.FavoriteAdapter;
import com.example.c_book.R;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    private List<History> historyList;
    private Context context;
    private HistoryAdapter.OnItemClickListener listener;

    public void setOnItemClickListener(HistoryAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }


    public HistoryAdapter(Context context) {
        this.context = context;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }


    public void setData(List<History> list){
        this.historyList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HistoryAdapter.HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        return new HistoryAdapter.HistoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        History history = historyList.get(position);
        if (history == null){
            return;
        }

        holder.imgBook.setImageResource(history.getResourceImg());
        holder.tvName.setText(history.getName());

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
    public int getItemCount() {
        if (historyList != null){
            return historyList.size();
        }

        return 0;
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgBook;
        private TextView tvName;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);

            imgBook =itemView.findViewById(R.id.img_book);
            tvName =itemView.findViewById(R.id.tv_name);
        }
    }

    public History getHistoryAt(int position) {
        if (position >= 0 && position < historyList.size()) {
            return historyList.get(position);
        }
        return null;
    }
}
