package com.example.coinflip;

import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView k1;
    Button b1;
    Button  b2;
    TextView t1;
    Integer tries=0;
    Integer yourchoise;
    Integer enemychoice;
    Random r=new Random();
    Integer win=0;
    Integer lose=0;
    Integer rounds=0;
    AlertDialog alertdia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        innit();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enemychoice=r.nextInt(2);
                setpic();
                yourchoise=0;
                check();
                print();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enemychoice=r.nextInt(2);
                setpic();
                yourchoise=1;
                check();
                print();
            }
        });
    }

    public  void innit(){
        this.k1=findViewById(R.id.pick);
        this.b1=findViewById(R.id.fej);
        this.b2=findViewById(R.id.irás);
        this.t1=findViewById(R.id.result);
        alertdia=new AlertDialog.Builder(MainActivity.this).setTitle("veresség").setMessage("szeretne új játékot").setNegativeButton("nem", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        }).setPositiveButton("igen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                rounds=0;
                win=0;
                lose=0;
                print();
            }
        })
                .setCancelable(false).create();

    }
    public  void print(){
        String s="Dobások:"+rounds + "\nnyerés:"+win+"\nvereség:"+lose;
        t1.setText(s);
    }
    public void check(){
        rounds++;
        if(rounds==5){
            alertdia.show();
        }
        if(yourchoise==enemychoice){
            win++;

        }
        else {
            lose++;

        }
    }
    public void setpic(){
        if(enemychoice==0){
            k1.setImageResource(R.drawable.heads);
        }
        else{
            k1.setImageResource(R.drawable.tails);
        }
    }
}