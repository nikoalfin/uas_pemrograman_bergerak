package com.example.nikouas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.service.controls.actions.FloatAction;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.nikouas.data.Biodata;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.IntFunction;

public class AddData extends AppCompatActivity {
    Biodata[] TabelData;
    ListView ListView01;
    Menu menu;
    protected Cursor cursor;
    DataHelper dbsaya;
    public static AddData ni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        Button add=findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddData.this, CreateData.class);
                startActivity(intent);
            }
        });
        ni = this;
        dbsaya = new DataHelper(this);
        Refresh();
    }

    public void Refresh(){
        SQLiteDatabase DB=dbsaya.getReadableDatabase();
        cursor=DB.rawQuery("select*from biodata",null);
        TabelData=new Biodata[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc = 0; cc < cursor.getCount(); cc++) {
            cursor.moveToPosition(cc);
            TabelData[cc] = new Biodata(cursor.getString(0),cursor.getString(1),cursor.getString(2));
        }

        ListView01 = findViewById(R.id.TampilData);
        String[]data = new String[TabelData.length];
        for(int i = 0 ; i< TabelData.length; i ++){
            data[i] = TabelData[i].getNama();
        }

        ListView01.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, data));
        ListView01.setSelected(true);

        ListView01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3){
            final String selection=TabelData[arg2].getId();
            final CharSequence[] listPil={"Lihat Detail", "Edit Data", "Hapus Data"};
            AlertDialog.Builder build = new AlertDialog.Builder(AddData.this);
            build.setTitle("Pilih");
            build.setItems(listPil, new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int item) {
                    switch (item) {
                        case 0:
                            Intent detail = new Intent(getApplicationContext(), DetailData.class);
                            detail.putExtra("id", selection);
                            startActivity(detail);
                            break;
                        case 1:
                            Intent edit = new Intent(getApplicationContext(), UpdateData.class);
                            edit.putExtra("id", selection);
                            startActivity(edit);
                            break;
                        case 2:
                            SQLiteDatabase db = dbsaya.getWritableDatabase();
                            db.execSQL("delete from biodata where id = '" + selection + "'");
                            Refresh();
                            break;
                    }
                }
            });
                build.create().show();
            }
        });

        ((ArrayAdapter) ListView01.getAdapter()).notifyDataSetInvalidated();
    }
}