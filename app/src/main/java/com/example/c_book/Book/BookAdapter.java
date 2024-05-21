package com.example.c_book.Book;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c_book.OnItemClickListener;
import com.example.c_book.R;
import com.example.c_book.database.BookLoadDatasource;
import com.example.c_book.fragment.HomeFragment;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    private List<Book> bookList;
    private OnItemClickListener listener;
    private Context context;

    public BookAdapter(Context context) {
        this.context = context;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setData(List<Book> list){
        this.bookList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = bookList.get(position);
        if (book == null){
            return;
        }

        holder.imgBook.setImageResource(book.getResourceImg());
        holder.tvName.setText(book.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    listener.onItemClick(adapterPosition);
                }
            }
        });
    }

    @Override
    public int getItemCount() {

        if (bookList != null){
           return bookList.size();
        }

        return 0;
    }

    public class BookViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgBook;
        private TextView tvName;
        private BookLoadDatasource bookLoadDatasource;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);

            imgBook =itemView.findViewById(R.id.img_book);
            tvName =itemView.findViewById(R.id.tv_name);

            bookLoadDatasource = new BookLoadDatasource(context);
            bookLoadDatasource.open();

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
