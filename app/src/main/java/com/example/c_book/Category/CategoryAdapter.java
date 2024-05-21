package com.example.c_book.Category;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c_book.Book.Book;
import com.example.c_book.Book.BookAdapter;
import com.example.c_book.BookInfo;
import com.example.c_book.OnItemClickListener;
import com.example.c_book.R;


import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>{

    private Context context;
    private List<Category> categoryList;

    public CategoryAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Category> list){
        this.categoryList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_catery, parent, false);
        return new CategoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = categoryList.get(position);
        if (category == null){
            return;
        }

        holder.tvNameCategory.setText(category.getNameCategory());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
        holder.rcvBook.setLayoutManager(linearLayoutManager);


        BookAdapter bookAdapter = new BookAdapter(context);
        bookAdapter.setData(category.getBookList());
        holder.rcvBook.setAdapter(bookAdapter);

        bookAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {

                    Book selectedBook = category.getBookList().get(position);
                    String category1 = category.getNameCategory();

                    Intent intent = new Intent(context, BookInfo.class);

                    intent.putExtra("id", selectedBook.getId());
                    intent.putExtra("name", selectedBook.getName());
                    intent.putExtra("author", selectedBook.getAuthor());
                    intent.putExtra("script", selectedBook.getScript());
                    intent.putExtra("resourceImg", selectedBook.getResourceImg());

                    intent.putExtra("categoryName", category1);

                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (categoryList != null){
            return categoryList.size();
        }
        return 0;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNameCategory;
        private RecyclerView rcvBook;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNameCategory = itemView.findViewById(R.id.tv_name_category);
            rcvBook = itemView.findViewById(R.id.rcv_book);
        }
    }

    public void setBooksForCategory(int categoryId, List<Book> books) {
        for (Category category : categoryList) {
            if (category.getIdCategory() == categoryId) {
                category.setBookList(books);
                notifyDataSetChanged();
                return;
            }
        }
    }
}
