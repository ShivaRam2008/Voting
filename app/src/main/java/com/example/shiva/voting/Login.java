package com.example.shiva.voting;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class Login extends AppCompatActivity {
EditText loginId;
    TextView signUpText;
    Button loginButton;
    String piv=null;
    String logId;
    SharedPreferences shared;
    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    DatabaseReference mReference = mDatabase.getReference();

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        if(!isNetworkConnected())   {
            final AlertDialog.Builder builder;
            builder = new AlertDialog.Builder(this,android.R.style.Theme_Material_Dialog);
            builder.setTitle("No Internet Connection").setMessage("Connect to the INTERNET and try again").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    finish();
                }
            }).show();
        }
        shared = getSharedPreferences("fileName", Context.MODE_PRIVATE);

        final SharedPreferences.Editor edio = shared.edit();
        loginButton = (Button)findViewById(R.id.buttonforlogin);
        signUpText = (TextView)findViewById(R.id.signuptextView);
        loginId = (EditText)findViewById(R.id.loginid);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(loginId.getText().toString().equals("")) {
                    Toast.makeText(Login.this, "Login ID required", Toast.LENGTH_SHORT).show();
                }

                else if(loginId.getText().toString().equals("piv1")||loginId.getText().toString().equals("piv0"))    {
                    logId = loginId.getText().toString();
                    if(logId.startsWith("piv1")) {
                         piv = "piv1";
                    }
                    else {
                         piv="piv0";
                    }
                    edio.putString("piv",piv);
                    edio.commit();
                    loginId.setText("");
                    Toast.makeText(Login.this, ""+logId, Toast.LENGTH_SHORT).show();
                    Intent toPass = new Intent(Login.this,Passwrod.class);
                    startActivity(toPass);



                    //Toast.makeText(Login.this, ""+mm, Toast.LENGTH_SHORT).show();

                }
                else    {
                    Toast.makeText(Login.this, "User not found!", Toast.LENGTH_SHORT).show();

                }
            }
        });
        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toSignup = new Intent(Login.this,SignUp.class);
                startActivity(toSignup);

            }
        });
    }
    private boolean isNetworkConnected(){
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();

        return cm.getActiveNetworkInfo()!=null && activeNetworkInfo.isConnected();
    }
}
