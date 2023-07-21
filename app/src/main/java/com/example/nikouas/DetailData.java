package com.example.nikouas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.pm.PermissionInfoCompat;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailData extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbsaya;
    TextView hasname,hasjns;
    Button back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data);
        //database
        dbsaya=new DataHelper(this);

        hasname=findViewById(R.id.HasNama);
        hasjns=findViewById(R.id.HasJenis);
        back=findViewById(R.id.Back);

        SQLiteDatabase db = dbsaya.getReadableDatabase();
        cursor = db.rawQuery("select * from biodata where id = ? ",new String[]{getIntent().getStringExtra("id")});
        cursor.moveToFirst();

        if (cursor.getCount()>0)
        {
            hasname.setText(cursor.getString(1));
            hasjns.setText(cursor.getString(2));
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                finish();
            }
        });

    }
}