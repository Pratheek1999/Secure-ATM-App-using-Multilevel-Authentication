package com.example.semac;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{

    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = (EditText)findViewById(R.id.UserID_EditText);
        Password = (EditText)findViewById(R.id.PasswordEditText);
        Info = (TextView)findViewById(R.id.NOARTextView);
        Login = (Button)findViewById(R.id.LoginBtn);

        Info.setText("Number of Attempts Remaining: 3");

        Login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                validate(Name.getText().toString(), Password.getText().toString());

            }
        });


    }

    private void validate(String userName, String userPassword)
    {

        if (((userName.equals("101101")) && (userPassword.equals("1234"))) || (userName.equals("111111")) && (userPassword.equals("1357")) || (userName.equals("202202")) && (userPassword.equals("0000")))
        {

            Toast.makeText(getApplicationContext(), "Succesful Transaction Password Verification", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);


        }

        else
        {

            counter --;


            Info.setText("Number Of Attempts Remaining: " + String.valueOf(counter));
            Toast.makeText(getApplicationContext(), "Invalid Transaction Password", Toast.LENGTH_SHORT).show();

            if(counter == 0)
            {

                Login.setEnabled(false);

            }

        }

    }
}
