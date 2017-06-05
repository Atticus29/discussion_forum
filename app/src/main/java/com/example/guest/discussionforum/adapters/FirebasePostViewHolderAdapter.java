package com.example.guest.discussionforum.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.discussionforum.Constants;
import com.example.guest.discussionforum.R;
import com.example.guest.discussionforum.models.Post;
import com.example.guest.discussionforum.ui.PostDetailActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebasePostViewHolderAdapter extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    View view;
    Context context;

    public FirebasePostViewHolderAdapter (View itemView){
        super(itemView);
        view = itemView;
        context = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindPost (Post post){
        ImageView contentImageView = (ImageView) view.findViewById(R.id.ContentURL);
        TextView postTitle = (TextView) view.findViewById(R.id.postTitle);
        TextView postContent = (TextView) view.findViewById(R.id.postContent);

        Picasso.with(context)
                .load(post.getImageURL())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(contentImageView);

        postTitle.setText(post.getTitle());
        postContent.setText(post.getContent());
    }

    @Override
    public void onClick(View view){
        final ArrayList<Post> posts = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_POSTS);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    posts.add(snapshot.getValue(Post.class));
                }
                int itemPosition = getLayoutPosition();
                Intent intent = new Intent(context, PostDetailActivity.class);
                intent.putExtra("position", itemPosition+"");
                intent.putExtra("posts", Parcels.wrap(posts));
                context.startActivity(intent);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

}
