package com.example.nikouas;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.UUID;

public class CreateData extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbsaya;
    EditText name,jns;
    Button back;
    ImageButton save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_data);

        //value
        name=findViewById(R.id.nama);
        jns=findViewById(R.id.jenis);
        save=findViewById(R.id.simpan);
        back=findViewById(R.id.kembali);

        //database
        dbsaya=new DataHelper(this);

        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0){
                SQLiteDatabase dB = dbsaya.getWritableDatabase();
                dB.execSQL("insert into biodata(id,nama,jk) values(?,?,?)",new Object[]{UUID.randomUUID(),name.getText().toString(),jns.getText().toString()});

                Toast.makeText(getApplicationContext(),"BERHASIL", Toast.LENGTH_LONG).show();
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