package com.example.guest.discussionforum.ui;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.guest.discussionforum.Constants;
import com.example.guest.discussionforum.R;
import com.example.guest.discussionforum.adapters.FirebasePostViewHolderAdapter;
import com.example.guest.discussionforum.models.Post;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    @Bind(R.id.addPostButton) FloatingActionButton mAddPostButton;
    private FirebaseRecyclerAdapter firebaseAdapter;
    private DatabaseReference postReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mAddPostButton.setOnClickListener(this);
        postReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_POSTS);
        setUpFirebaseAdapter();

    }

    @Override
    public void onClick(View v) {
        if (v==mAddPostButton) {
            Intent intent = new Intent(MainActivity.this, AddPostActivity.class);
            startActivity(intent);
        }
    }

    private void setUpFirebaseAdapter(){
        firebaseAdapter = new FirebaseRecyclerAdapter<Post, FirebasePostViewHolderAdapter>(Post.class, R.layout.fragment_post, FirebasePostViewHolderAdapter.class, postReference){
            @Override
            protected void populateViewHolder(FirebasePostViewHolderAdapter viewHolder, Post model, int position){
                viewHolder.bindPost(model);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(firebaseAdapter);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        firebaseAdapter.cleanup();
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.action_logout){
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout(){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
