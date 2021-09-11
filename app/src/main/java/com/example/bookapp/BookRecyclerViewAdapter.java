package com.example.bookapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.transition.Transition;
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

public class BookRecyclerViewAdapter extends RecyclerView.Adapter<BookRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "BookRecyclerViewAdapter";

    //list of all the books that we are going to show in our activity
    private ArrayList<Book> _books = new ArrayList<>();
    //if we are going to use Glide to show images from internet we need to have context also in this class because we are not inside any activity
    private Context mContext;

    private String parentActivity;


    //constructor
    public BookRecyclerViewAdapter(Context mContext, String parentActivity) {
        this.mContext = mContext;
        this.parentActivity = parentActivity;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_book, parent, false);

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
                //when the card is clicked we need to navigate to BookActivity
                //because we dont have startActivity() method we can use one from the context object we recieved from activity
                Intent intent = new Intent(mContext, BookDetailsActivity.class);

                //to pass some custom object to another activity we need to implement Serializable interface
                //in the putExtra() method we pass in the class name and the object we want to send
                intent.putExtra("BookId", _books.get(position).get_id());
                mContext.startActivity(intent);
            }
        });


        //setting the data when the CardView is expanded
        holder._txtAuthor.setText(_books.get(position).get_author());
        holder._txtShortDesc.setText(_books.get(position).get_shortDesc());


        //we need to check if the item at this position is expanded or not
        if (_books.get(position).get_isExpanded()) {
            //if it's expanded we need to make the second relative layout visible and down arrow not visible
            TransitionManager.beginDelayedTransition(holder._parent);
            holder._expandedLayout.setVisibility(View.VISIBLE);
            holder._downArrow.setVisibility(View.GONE);


            //checking if we have to allow the user to delete books
            if (parentActivity.equals("AllBooks")) {
                holder._btnDeleteItem.setVisibility(View.GONE);
            } else if (parentActivity.equals("AlreadyRead")) {
                holder._btnDeleteItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DeleteItemFromAlreadyRead(position);
                    }
                });
            } else if (parentActivity.equals("WantToRead")) {
                holder._btnDeleteItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DeleteItemFromWantToRead(position);
                    }
                });
            } else if (parentActivity.equals("CurrentlyReading")) {
                holder._btnDeleteItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DeleteItemFromCurrentlyReading(position);
                    }
                });
            } else {
                holder._btnDeleteItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DeleteItemFromFavorites(position);
                    }
                });
            }

        } else {
            TransitionManager.beginDelayedTransition(holder._parent);
            holder._expandedLayout.setVisibility(View.GONE);
            holder._downArrow.setVisibility(View.VISIBLE);
        }
    }






    private void DeleteItemFromFavorites(int position) {
        //making the AlertDialog builder in order to delete the book
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage("Are you sure you want to delete this book?");

        //if the user agrees we will then make onClickListener for this button and there we have to delete the book
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(Utils.getInstance().removeFromFavorites(_books.get(position)))
                {
                    Toast.makeText(mContext, "Book successfully removed!", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                }
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //we dont have to do anything
            }
        });

        //in the end we need to create our builder and show it
        builder.create().show();

    }
    private void DeleteItemFromCurrentlyReading(int position) {
        //making the AlertDialog builder in order to delete the book
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage("Are you sure you want to delete this book?");

        //if the user agrees we will then make onClickListener for this button and there we have to delete the book
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(Utils.getInstance().removeFromCurrentlyReading(_books.get(position)))
                {
                    Toast.makeText(mContext, "Book successfully removed!", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                }
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //we dont have to do anything
            }
        });

        //in the end we need to create our builder and show it
        builder.create().show();

    }
    private void DeleteItemFromWantToRead(int position) {
        //making the AlertDialog builder in order to delete the book
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage("Are you sure you want to delete this book?");

        //if the user agrees we will then make onClickListener for this button and there we have to delete the book
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(Utils.getInstance().removeFromWantToRead(_books.get(position)))
                {
                    Toast.makeText(mContext, "Book successfully removed!", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                }
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //we dont have to do anything
            }
        });

        //in the end we need to create our builder and show it
        builder.create().show();
    }
    private void DeleteItemFromAlreadyRead(int position) {
        //making the AlertDialog builder in order to delete the book
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage("Are you sure you want to delete this book?");

        //if the user agrees we will then make onClickListener for this button and there we have to delete the book
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(Utils.getInstance().removeFromAlreadyRead(_books.get(position)))
                {
                    Toast.makeText(mContext, "Book successfully removed!", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                }
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //we dont have to do anything
            }
        });

        //in the end we need to create our builder and show it
        builder.create().show();
    }





    //returns the number of books inside the list
    @Override
    public int getItemCount() {
        return _books.size();
    }


    //class that is gonna be viewHolder for each book item
    public class ViewHolder extends RecyclerView.ViewHolder {

        private CardView _parent;
        private ImageView _imgView;
        private TextView _name;
        private ImageView _downArrow, _upArrow;
        private RelativeLayout _expandedLayout;
        private TextView _txtAuthor, _txtShortDesc;
        private TextView _btnDeleteItem;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            _parent = itemView.findViewById(R.id.parentCard);
            _imgView = itemView.findViewById(R.id.imgBook);
            _name = itemView.findViewById(R.id.txtTitle);
            _downArrow = itemView.findViewById(R.id.btnDownArrow);
            _upArrow = itemView.findViewById(R.id.btnUpArrow);
            _txtAuthor = itemView.findViewById(R.id.txtAuthor);
            _txtShortDesc = itemView.findViewById(R.id.txtShortDescription);
            _expandedLayout = itemView.findViewById(R.id.expandedRelativeLayout);
            _btnDeleteItem = itemView.findViewById(R.id.btnDeleteItem);


            //in constructor we also set the onClickListener for btnDownArrow
            _downArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //here we want to set the value of the isExpanded for a clicked book to opposite of what it was
                    //we get the clicked view with getAdapterPosition() method
                    Book clickedItem = _books.get(getAdapterPosition());

                    clickedItem.set_isExpanded(!clickedItem.get_isExpanded());


                    //because here we are changing only one item we need to use notifyItemChanged()
                    notifyItemChanged(getAdapterPosition());
                }
            });

            //in constructor we also set the onClickListener for upArrow
            _upArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //first we get the book on the clicked position
                    Book clickedItem = _books.get(getAdapterPosition());

                    //then we invert the value of isExpanded property
                    clickedItem.set_isExpanded(!clickedItem.get_isExpanded());

                    //call the method to alert the recycler view that this item's value has changed
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}
