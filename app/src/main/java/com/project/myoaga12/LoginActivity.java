package com.project.myoaga12;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtloginname, edtloginpass;
    private Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login");

        edtloginname = findViewById(R.id.edtloginname);
        edtloginpass = findViewById(R.id.edtloginpass);

        btnlogin = findViewById(R.id.btnlogin);

        edtloginpass.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {

                if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){
                    onClick(btnlogin);
                }
                return false;
            }
        });

        btnlogin.setOnClickListener(this);


        if (ParseUser.getCurrentUser() != null) {
            ParseUser.getCurrentUser().logOut();
            //transitiontomsearchActivity();
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnlogin:
                if (edtloginname.getText().toString().equals("") || edtloginpass.getText().toString().equals("")){
                    FancyToast.makeText(LoginActivity.this,  " Username,Password fields cannot be empty",
                            Toast.LENGTH_SHORT, FancyToast.INFO, true).show();
                }
                else {
                    final ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("Logging in " + edtloginname.getText().toString());
                    progressDialog.show();
                    ParseUser.logInInBackground(edtloginname.getText().toString(),
                            edtloginpass.getText().toString(), new LogInCallback() {
                                @Override
                                public void done(ParseUser user, ParseException e) {

                                    if (user != null && e == null) {
                                        FancyToast.makeText(LoginActivity.this, user.getUsername() + " is Logged in",
                                                Toast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                                        //transitiontomsearchActivity();
                                        Intent Signnext = new Intent(LoginActivity.this, LocationActivity.class);
                                        startActivity(Signnext);

                                    } else {
                                        FancyToast.makeText(LoginActivity.this, edtloginname.getText().toString()+" is not Register, Please Register",
                                                Toast.LENGTH_LONG, FancyToast.INFO, true).show();

                                    }
                                    progressDialog.dismiss();
                                }
                            });
                }
                break;

            case R.id.txtloginsignup:
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
                break;

        }

    }
       // private void transitiontomsearchActivity () {
       //     Intent intented = new Intent(LoginActivity.this, MSearchActivity.class);
      //      startActivity(intented); }
       public void rootTap(View view){

           try{
               InputMethodManager inputMethodManager =(InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
               inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
           }
           catch (Exception e){
               e.printStackTrace();
           }
       }
}
