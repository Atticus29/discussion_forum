package com.example.guest.discussionforum.ui;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.guest.discussionforum.R;
import com.example.guest.discussionforum.adapters.PostListAdapter;
import com.example.guest.discussionforum.models.Post;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    @Bind(R.id.addPostButton) FloatingActionButton mAddPostButton;

    private PostListAdapter mAdapter;
    public ArrayList<Post> mPosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mAddPostButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v==mAddPostButton) {
            Intent intent = new Intent(MainActivity.this, AddPostActivity.class);
            startActivity(intent);
        }
    }
}
