package com.lamba.gadwally;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button login;
    Dialog regdialog;
    TextView createaccount, desc1 , desc2, forgetpass;
    Typeface face;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        face = Typeface.createFromAsset(getAssets(),"fonts/bauhaus93.ttf");
        login = findViewById(R.id.button);
        createaccount = findViewById(R.id.create_account);
        forgetpass = findViewById(R.id.forget_password);
        desc1 = findViewById(R.id.mydesc);
        desc1.setTypeface(face);
        desc2 = findViewById(R.id.mydesc2);
        desc2.setTypeface(face);
        regdialog = new Dialog(this);
        forgetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "lma nb2a nensa el password", Toast.LENGTH_SHORT).show();
            }
        });
        createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView close;
                Button submit;
                regdialog.setContentView(R.layout.activity_register);
                close = regdialog.findViewById(R.id.cancel_cross);
                submit = regdialog.findViewById(R.id.submit_register);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        regdialog.dismiss();

                    }
                });
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, " لسه فاضل شوية شغل ", Toast.LENGTH_SHORT).show();
                    }
                });
                regdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                regdialog.show();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText username  = findViewById(R.id.username);
                EditText password  = findViewById(R.id.password);
                if (username.getText().toString().equals("admin") &&
                        password.getText().toString().equals("admin")){
                    Toast.makeText(MainActivity.this, "Login Successful >>>", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this , Main_Interface.class));

                }

            }
        });


    }
}
