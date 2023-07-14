package com.leows.testapp;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import com.leows.testapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    //viewBinding
    //自动将xml文件生成java类  类的名字以xml的名字+Binding
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);



            //改造activity
            activityMainBinding= ActivityMainBinding.inflate(getLayoutInflater());
            setContentView(activityMainBinding.getRoot());

            activityMainBinding.button.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    activityMainBinding.drawerLayout.openDrawer(Gravity.LEFT);
                }
            });

            activityMainBinding.button2.setOnClickListener(
                    view->{
                        activityMainBinding.drawerLayout.closeDrawers();
                    }
            );


            activityMainBinding.btIntent.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this,VXActivity.class);
                    startActivity(intent);
                }
            });
    }
}