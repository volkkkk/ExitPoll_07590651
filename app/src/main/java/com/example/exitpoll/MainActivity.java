package com.example.exitpoll;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.exitpoll.db.DatabaseHelper;
import com.example.exitpoll.model.VoteItem;

import java.util.ArrayList;
import java.util.List;

import static com.example.exitpoll.db.DatabaseHelper.COL_ID;
import static com.example.exitpoll.db.DatabaseHelper.COL_IMAGE;
import static com.example.exitpoll.db.DatabaseHelper.COL_NUMBER;
import static com.example.exitpoll.db.DatabaseHelper.COL_POINT;
import static com.example.exitpoll.db.DatabaseHelper.TABLE_NAME;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper mHelper;
    private SQLiteDatabase mDb;
    private List<VoteItem> mVoteItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHelper = new DatabaseHelper(MainActivity.this);
        mDb = mHelper.getWritableDatabase();

        Button vote_one = findViewById(R.id.vote_1_b);
        Button vote_two = findViewById(R.id.vote_2_b);
        Button vote_three = findViewById(R.id.vote_3_b);
        Button vote_no = findViewById(R.id.vote_no_b);
        Button score_view = findViewById(R.id.point_view_b);

        loadPhoneData();

        vote_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPhoneData();
                VoteItem item = mVoteItemList.get(0);
                String textscore = item.score.toString();
                int score =Integer.valueOf(textscore)+1;
                String newscore = Integer.toString(score);
                ContentValues cv = new ContentValues();
                cv.put(COL_NUMBER, "no");
                cv.put(COL_POINT,newscore);
                cv.put(COL_IMAGE,"vote_no.png");


                mDb.update(
                        TABLE_NAME,
                        cv,
                        COL_ID + " = ?",
                        new String[]{String.valueOf(1)}
                );
                loadPhoneData();


            }
        });

        vote_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPhoneData();
                VoteItem item = mVoteItemList.get(1);
                String textscore = item.score.toString();
                int score =Integer.valueOf(textscore)+1;
                String newscore = Integer.toString(score);
                ContentValues cv = new ContentValues();
                cv.put(COL_NUMBER, "1");
                cv.put(COL_POINT,newscore);
                cv.put(COL_IMAGE,"one.png");


                mDb.update(
                        TABLE_NAME,
                        cv,
                        COL_ID + " = ?",
                        new String[]{String.valueOf(2)}
                );
                loadPhoneData();

            }
        });

        vote_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPhoneData();
                VoteItem item = mVoteItemList.get(2);
                String textscore = item.score.toString();
                int score =Integer.valueOf(textscore)+1;
                String newscore = Integer.toString(score);
                ContentValues cv = new ContentValues();
                cv.put(COL_NUMBER, "2");
                cv.put(COL_POINT,newscore);
                cv.put(COL_IMAGE,"two.png");


                mDb.update(
                        TABLE_NAME,
                        cv,
                        COL_ID + " = ?",
                        new String[]{String.valueOf(3)}
                );
                loadPhoneData();

            }
        });

        vote_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPhoneData();
                VoteItem item = mVoteItemList.get(3);
                String textscore = item.score.toString();
                int score =Integer.valueOf(textscore)+1;
                String newscore = Integer.toString(score);
                ContentValues cv = new ContentValues();
                cv.put(COL_NUMBER, "3");
                cv.put(COL_POINT,newscore);
                cv.put(COL_IMAGE,"three.png");


                mDb.update(
                        TABLE_NAME,
                        cv,
                        COL_ID + " = ?",
                        new String[]{String.valueOf(4)}
                );
                loadPhoneData();

            }
        });



        score_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ScoreActivity.class);
                startActivity(intent);
            }
        });



        //todo


    }
    private void loadPhoneData() {
        Cursor c = mDb.query(TABLE_NAME, null, null, null, null, null, null);

        mVoteItemList = new ArrayList<>();
        while (c.moveToNext()) {
            long id = c.getLong(c.getColumnIndex(COL_ID));
            String number = c.getString(c.getColumnIndex(COL_NUMBER));
            String point = c.getString(c.getColumnIndex(COL_POINT));
            String image = c.getString(c.getColumnIndex(COL_IMAGE));

            VoteItem item = new VoteItem(id, number, point,image);
            mVoteItemList.add(item);
        }
        c.close();
    }

}


