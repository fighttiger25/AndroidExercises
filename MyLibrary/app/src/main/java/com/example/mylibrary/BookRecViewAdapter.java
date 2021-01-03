package com.example.mylibrary;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookRecViewAdapter extends RecyclerView.Adapter<BookRecViewAdapter.ViewHolder> {
    private static final String TAG = "BookRecViewAdapter";

    private ArrayList<Book> books = new ArrayList<>();
    private Context mContext;

    public BookRecViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView parent;
        private ImageView imgBook;
        private TextView txtBookName;

        private ImageView upArrow, downArrow;
        private RelativeLayout collapsedRelLayout, expandedRelLayout;
        private TextView txtAuthor, txtDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            imgBook = itemView.findViewById(R.id.imgBook);
            txtBookName = itemView.findViewById(R.id.txtBookName);
            txtAuthor = itemView.findViewById(R.id.txtAuthorName);
            txtDescription = itemView.findViewById(R.id.txtDesContent);

            upArrow = itemView.findViewById(R.id.btnCollapse);
            downArrow = itemView.findViewById(R.id.btnExpand);
            collapsedRelLayout = itemView.findViewById(R.id.collapsedRelLayout);
            expandedRelLayout = itemView.findViewById(R.id.expandedRelLayout);
        }
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_book, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: started");
        holder.txtBookName.setText(books.get(position).getName());
        Glide.with(mContext).asBitmap().load(books.get(position).getImageUrl()).into(holder.imgBook);
        holder.txtAuthor.setText(books.get(position).getAuthor());
        holder.txtDescription.setText(books.get(position).getShortDesc());

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, books.get(position).getName() + " clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, BookActivity.class);
                mContext.startActivity(intent);
            }
        });

        holder.downArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = books.get(position);
                books.get(position).setExpanded(!book.isExpanded());
                notifyItemChanged(position);
            }
        });

        holder.upArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = books.get(position);
                books.get(position).setExpanded(!book.isExpanded());
                notifyItemChanged(position);
            }
        });

        if (books.get(position).isExpanded()){
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedRelLayout.setVisibility(View.VISIBLE);
            holder.downArrow.setVisibility(View.GONE);
        }
        else {
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedRelLayout.setVisibility(View.GONE);
            holder.upArrow.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return books.size();
    }
}
