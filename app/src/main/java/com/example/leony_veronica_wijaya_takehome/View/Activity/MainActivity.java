package com.example.leony_veronica_wijaya_takehome.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.leony_veronica_wijaya_takehome.R;

public class MainActivity extends AppCompatActivity {
    CardView users, repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        users = findViewById(R.id.cv_Users);
        repo = findViewById(R.id.cv_Repo);

        users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(i);
            }
        });

//        repo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(MainActivity.this, SearchRepoActivity.class);
//                startActivity(i);
//            }
//        });
    }
}