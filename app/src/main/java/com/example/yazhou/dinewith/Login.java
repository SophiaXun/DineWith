package com.example.yazhou.dinewith;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yazhou.dinewith.com.example.yazhou.dinewith.sqlite.helper.DatabaseHelper;
import com.example.yazhou.dinewith.com.example.yazhou.dinewith.sqlite.model.User;

public class Login extends AppCompatActivity {
    private static EditText username;
    private static EditText pwd;
    private static Button loginButton;
    private static DatabaseHelper dineWithDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        dineWithDB=new DatabaseHelper(this);



        LoginButton();
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void LoginButton(){
        username= (EditText)findViewById(R.id.usernameEditText);
        pwd= (EditText)findViewById(R.id.pwdEditText);
        loginButton= (Button)findViewById(R.id.loginButton);

        loginButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
///////////////////////////////////////////////////////////////////////////////////

                         dineWithDB.generateMetadata();

//                        String usernameString=username.getText().toString();
//                        String pwdString=pwd.getText().toString();

// ////////////////////////////////////////////////////////////////////////////////
                        String usernameString=new String("Sophia");
                        String pwdString=new String("1");
/////////////////////////////////////////////////////////////////////////
                        boolean loginResult=dineWithDB.Login(usernameString,pwdString);
                        if(loginResult){
                            Intent intent=new Intent("com.example.yazhou.dinewith.HomePage");
                            startActivity(intent);
                        }else{
                            TextView  reminder=(TextView)findViewById(R.id.reminderTextView);
                            reminder.setText("Incorrect Password");
                        }
                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
