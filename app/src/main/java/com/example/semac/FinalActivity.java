package com.example.semac;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FinalActivity extends AppCompatActivity
{
    Button Confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        Confirm = (Button)findViewById(R.id.ConfirmFAButton);

        Confirm.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = getPackageManager().getLaunchIntentForPackage("com.example.satm");
                startActivity(i);
                Toast.makeText(getApplicationContext(),"Complete Successfully verification, Now You can withdraw Cash",Toast.LENGTH_SHORT).show();

            }
        });

    }
}
