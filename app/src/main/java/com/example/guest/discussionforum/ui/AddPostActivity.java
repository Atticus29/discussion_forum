package com.example.guest.discussionforum.ui;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.guest.discussionforum.Constants;
import com.example.guest.discussionforum.R;
import com.example.guest.discussionforum.models.Post;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddPostActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.savePostButton) FloatingActionButton mSavePostButton;
    @Bind(R.id.contentText) EditText mContentText;
    @Bind(R.id.titleText) EditText mTitleText;
    @Bind(R.id.imageUrlText) EditText mImageUrlText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        ButterKnife.bind(this);

        mSavePostButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (mSavePostButton == v) {
            String content = mContentText.getText().toString();
            String title = mTitleText.getText().toString();
            String imageUrl = mImageUrlText.getText().toString();

            Post newPost = new Post(content, title, imageUrl);

            DatabaseReference postReference = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_POSTS);
            postReference.push().setValue(newPost);
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        }
    }
}
