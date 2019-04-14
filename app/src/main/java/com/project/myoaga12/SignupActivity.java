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

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import androidx.appcompat.app.AppCompatActivity;


public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    //ui components
    private EditText edtname, edtmail, edtpass;
    private Button btnsignup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Signup");
        setContentView(R.layout.activity_signup);

        edtname = findViewById(R.id.edtsignname);
        edtmail = findViewById(R.id.edtsignemail);

        edtpass = findViewById(R.id.edtsignpass);

        edtpass.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {

                if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){
                    onClick(btnsignup);
                }
                return false;
            }
        });



        btnsignup = findViewById(R.id.btnsignup);

        btnsignup.setOnClickListener(this);

        if (ParseUser.getCurrentUser() != null) {
            ParseUser.getCurrentUser().logOut();
            //transitiontomsearchActivity();}
        }
    }

        @Override
        public void onClick (View view){

            switch (view.getId()) {

                case R.id.btnsignup:

                    if (edtmail.getText().toString().equals("") || edtname.getText().toString().equals("") || edtpass.getText().toString().equals("")) {
                        FancyToast.makeText(SignupActivity.this, " Name,E-mail,Password fields cannot be empty",
                                Toast.LENGTH_SHORT, FancyToast.INFO, true).show();
                    }
                    else {
                        final ParseUser appUser = new ParseUser();
                        appUser.setEmail(edtmail.getText().toString());
                        appUser.setUsername(edtname.getText().toString());
                        appUser.setPassword(edtpass.getText().toString());

                        final ProgressDialog progressDialog = new ProgressDialog(this);
                        progressDialog.setMessage("Registering" + edtname.getText().toString());
                        progressDialog.show();
                        appUser.signUpInBackground(new SignUpCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {
                                    FancyToast.makeText(SignupActivity.this, appUser.getUsername() + "  you have Signed Up",
                                            Toast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                                    Intent intentLogin = new Intent(SignupActivity.this, LocationActivity.class);
                                    startActivity(intentLogin);
                                    //transitiontomsearchActivity()
                                } else {
                                    FancyToast.makeText(SignupActivity.this, "User already exist, Please login. If forgot password signup with different Username and Email-id",
                                            Toast.LENGTH_LONG, FancyToast.ERROR, true).show();
                                }
                                progressDialog.dismiss();
                            }
                        });
                    }
                        break;

                        case R.id.txtsignuplogin:
                            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                            startActivity(intent);
                            break;

                    }
        }
        //private void transitiontomsearchActivity() {
        //Intent intent = new Intent(SignupActivity.this, MSearchActivity.class);
        //startActivity(intent); }
        public void rootTrap(View view){

        try {
            InputMethodManager inputMethodManager =(InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }
        catch (Exception e){

            e.printStackTrace();
        }
        }
    }
