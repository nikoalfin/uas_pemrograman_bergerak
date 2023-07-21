package com.example.nikouas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText name,sandi;
    private Button send;

    private String username="niko alfin";
    private String pass ="setyawan";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=findViewById(R.id.Usname);
        sandi=findViewById(R.id.pasw);
        send=findViewById(R.id.Login);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().equalsIgnoreCase(username) && sandi.getText().toString().equalsIgnoreCase(pass)) {
                    Intent PageNew=new Intent(MainActivity.this, AddData.class);
                    startActivity(PageNew);

                    Toast.makeText(MainActivity.this,"LOGIN SUKSES", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"USERNAME / PASSWORD YANG ANDA MASUKAN SALAH!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}