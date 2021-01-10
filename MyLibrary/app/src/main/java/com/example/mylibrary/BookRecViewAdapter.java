package com.example.mylibrary;

import android.content.Context;
import android.content.DialogInterface;
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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static android.os.Debug.waitForDebugger;
import static com.example.mylibrary.BookActivity.BOOK_ID_KEY;

public class BookRecViewAdapter extends RecyclerView.Adapter<BookRecViewAdapter.ViewHolder> {
    private static final String TAG = "BookRecViewAdapter";

    private ArrayList<Book> books = new ArrayList<>();
    private AppCompatActivity mContext;
    private String parentActivity;
    private UpdateCallBack updateCallBack;

    public BookRecViewAdapter(AppCompatActivity mActivity, String parentActivity, UpdateCallBack updateCallBack) {
        this.mContext = mActivity;
        this.parentActivity = parentActivity;
        this.updateCallBack = updateCallBack;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView parent;
        private ImageView imgBook;
        private TextView txtBookName;

        private ImageView upArrow, downArrow;
        private RelativeLayout collapsedRelLayout, expandedRelLayout;
        private TextView txtAuthor, txtDescription, delete;

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
            delete = itemView.findViewById(R.id.btnDelete);
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
                intent.putExtra(BOOK_ID_KEY, books.get(position).getId());
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
            if (parentActivity.equals("allBooks")){
                holder.delete.setVisibility(View.GONE);
            }
            else if (parentActivity.equals("alreadyRead")){
                holder.delete.setVisibility(View.VISIBLE);
                holder.delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setMessage("Are you sure you want to delete " + books.get(position).getName() + "?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (Utils.getInstance(mContext).removeAlreadyReadBook(books.get(position))){
                                    Toast.makeText(mContext, "removed", Toast.LENGTH_SHORT).show(); // asynchronous
                                    updateCallBack.updateView(mContext);
                                    notifyDataSetChanged();
                                }else{
                                    Toast.makeText(mContext, "Error, please try again!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.create().show();

                    }
                });
            }
            else if (parentActivity.equals("currentlyRead")){
                holder.delete.setVisibility(View.VISIBLE);
                holder.delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setMessage("Are you sure you want to delete " + books.get(position).getName() + "?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (Utils.getInstance(mContext).removeCurrentlyReadingBooks(books.get(position))){
                                    Toast.makeText(mContext, "removed", Toast.LENGTH_SHORT).show(); // asynchronous
                                    // without calling this callback, the recycleView will not refresh, because we used sharedPreference, which takes too long time to update (threading issue).
                                    // while it is not needed to be called if we use static attributes
                                    updateCallBack.updateView(mContext);
                                    notifyDataSetChanged();
                                }else{
                                    Toast.makeText(mContext, "Error, please try again!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.create().show();

                    }
                });

            }
            else if (parentActivity.equals("favoriteBooks")){
                holder.delete.setVisibility(View.VISIBLE);
                holder.delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setMessage("Are you sure you want to delete " + books.get(position).getName() + "?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (Utils.getInstance(mContext).removeFavoriteBook(books.get(position))){
                                    Toast.makeText(mContext, "removed", Toast.LENGTH_SHORT).show(); // asynchronous
                                    updateCallBack.updateView(mContext);
                                    notifyDataSetChanged();
                                }else{
                                    Toast.makeText(mContext, "Error, please try again!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.create().show();

                    }
                });

            }
            else if (parentActivity.equals("wantToRead")){
                holder.delete.setVisibility(View.VISIBLE);
                holder.delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setMessage("Are you sure you want to delete " + books.get(position).getName() + "?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (Utils.getInstance(mContext).removeWantToReadBook(books.get(position))){
                                    Toast.makeText(mContext, "removed", Toast.LENGTH_SHORT).show(); // asynchronous
                                    updateCallBack.updateView(mContext);
                                    notifyDataSetChanged();
                                }else{
                                    Toast.makeText(mContext, "Error, please try again!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.create().show();

                    }
                });

            }
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
