package com.example.nikouas;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateData extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbsaya;
    EditText upname,upjns;
    Button update,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        dbsaya=new DataHelper(this);
        upname=findViewById(R.id.upnama);
        upjns=findViewById(R.id.upjenis);
        update=findViewById(R.id.update);
        back=findViewById(R.id.kembali2);

        SQLiteDatabase db = dbsaya.getReadableDatabase();
        cursor = db.rawQuery("select * from biodata where id = ?",
                new String[]{getIntent().getStringExtra("id")});
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            upname.setText(cursor.getString(1));
            upjns.setText(cursor.getString(2));
        }

        update.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                SQLiteDatabase db = dbsaya.getWritableDatabase();
                  db.execSQL("update biodata set nama=? , jk=? where id = ?",
                          new String[]{upname.getText().toString(),
                                       upjns.getText().toString(),
                                  getIntent().getStringExtra("id")});

                Toast.makeText(getApplicationContext(), "BERHASIL UPDATE", Toast.LENGTH_LONG).show();
                AddData.ni.Refresh();
                finish();
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }
}