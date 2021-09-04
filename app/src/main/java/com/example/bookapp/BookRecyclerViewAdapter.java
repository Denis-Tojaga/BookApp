package com.example.bookapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookRecyclerViewAdapter extends RecyclerView.Adapter<BookRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "BookRecyclerViewAdapter";

    //list of all the books that we are going to show in our activity
    private ArrayList<Book> _books = new ArrayList<>();

    //if we are going to use Glide to show images from internet we need to have context also in this class because we are not inside any activity
    private Context mContext;

    //constructor
    public BookRecyclerViewAdapter(Context mContext) {
        this.mContext = mContext;
    }
    //setter for the arrayList of books
    public void set_books(ArrayList<Book> _books) {
        this._books = _books;
        //important method to implement because we are going to refresh the data in our recyclerView
        notifyDataSetChanged();
    }





    //methods that interface class implements here
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //first we need to make a view from LayoutInflater
        //LayoutInflater.from() takes one arg
        //context, we can get it from parent
        //inflate takes the layout responsible for showing a single item(list_item_book)
        //it takes the parent which is responsible for holding them all together and last one is the false param to avoid redundancy
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_book,parent,false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    //here we connect the data inside a holder with corresponding item inside an arrayList
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: Called");

        holder._name.setText(_books.get(position).get_name());
        //in order to show images from the internet we use Glide dependency
        Glide.with(mContext).asBitmap().load(_books.get(position).get_imageURL()).into(holder._imgView);

        //setting the onClickListener on the whole card and that is _parent attribute
        holder._parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, _books.get(position).get_name() + " selected", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //returns the number of books inside the list
    @Override
    public int getItemCount() {
        return _books.size();
    }






    //class that is gonna be viewHolder for each book item
    public class ViewHolder extends RecyclerView.ViewHolder{

        private CardView _parent;
        private ImageView _imgView;
        private TextView _name;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            _parent = itemView.findViewById(R.id.parentCard);
            _imgView = itemView.findViewById(R.id.imgBook);
            _name = itemView.findViewById(R.id.txtTitle);
        }
    }
}
