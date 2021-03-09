package com.example.semac;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SecondActivity extends AppCompatActivity
{


    FirebaseAuth auth;
    EditText PhoneNumber;
    EditText VerificationCode;
    Button VerifyOTPButton;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;
    String verification_code;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        PhoneNumber = (EditText)findViewById(R.id.PhoneNumberEditText);
        VerificationCode = (EditText)findViewById(R.id.OTPEditText);
        VerifyOTPButton = (Button)findViewById(R.id.VerifyOTPButton);
        auth = FirebaseAuth.getInstance();

            mCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks()
            {
                @Override
                public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential)
                {



                }

                @Override
                public void onVerificationFailed(FirebaseException e)
                {



                }

                @Override
                public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken)
                {

                   super.onCodeSent(s, forceResendingToken);
                    verification_code = s;
                    Toast.makeText(getApplicationContext(),"Code Sent to the Number",Toast.LENGTH_SHORT).show();

                }
            };
    }

    public void send_sms(View v)
    {
        String number = PhoneNumber.getText().toString();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(number,60, TimeUnit.SECONDS,this,mCallback);

    }

    public void signInWithPhone(PhoneAuthCredential credential)
    {

        auth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {

                        if(task.isSuccessful())
                        {

                            Toast.makeText(getApplicationContext(),"User Verification Successful",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SecondActivity.this, FinalActivity.class);
                            startActivity(intent);

                        }

                        else
                        {

                            Toast.makeText(getApplicationContext(),"Invalid OTP -> Verification Failed",Toast.LENGTH_SHORT).show();

                        }

                    }
                });

    }

     public void verify(View v)
     {

         String input_code = VerificationCode.getText().toString();


             verifyPhoneNumber(verification_code,input_code);



     }

     public void verifyPhoneNumber(String verifyCode, String input_code)
     {

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verifyCode,input_code);
        signInWithPhone(credential);

     }

}
