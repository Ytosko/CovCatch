package com.ytosko.covcatch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.BitSet;

public class register extends AppCompatActivity {

    String first,second,mail,id;
    EditText name1,name2,e_mail,ids,pwd,repwd;
    Button regi;
    TextView login;
    String pwd1 = "fsdbhsdfbshdfjkhfszdjk";
    String f_name,l_name,ml,iid,pswd,repswd;
    private ProgressDialog pDialog;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ActionBar a = getSupportActionBar();
        a.hide();
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        first = getIntent().getStringExtra("fname");
        second = getIntent().getStringExtra("lname");
        mail = getIntent().getStringExtra("email");

        name1 = findViewById(R.id.input_fname);
        name2 = findViewById(R.id.input_lname);
        e_mail = findViewById(R.id.input_email);
        pwd = findViewById(R.id.input_password);
        repwd = findViewById(R.id.input_reEnterPassword);
        regi = findViewById(R.id.btn_signup);
        login = findViewById(R.id.link_login);


        mAuth = FirebaseAuth.getInstance();
        pDialog = new ProgressDialog(register.this);
        pDialog.setMessage("Registration in proccess");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToHome = new Intent(register.this, login.class);
                intToHome.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intToHome);
                finish();
            }
        });

        if(first != null && second != null && mail != null){
            name1.setText(first);
            name2.setText(second);
            e_mail.setText(mail);
            FirebaseAuth.getInstance().signInWithEmailAndPassword(mail, pwd1)
                    .addOnCompleteListener(
                            new OnCompleteListener<AuthResult>()
                            {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task)
                                {
                                    if (!task.isSuccessful())
                                    {
                                        try
                                        {
                                            throw task.getException();
                                        }
                                        catch (FirebaseAuthInvalidCredentialsException wrongPassword)
                                        {
                                            Toast.makeText(register.this, "User already exists! Please log in", Toast.LENGTH_SHORT).show();
                                            Intent intToHome = new Intent(register.this, login.class);
                                            intToHome.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                            startActivity(intToHome);
                                            finish();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }
                    );

            disconnectFromFacebook();
        }

        regi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pDialog.show();
                f_name = name1.getText().toString();
                l_name = name2.getText().toString();
                ml = e_mail.getText().toString();
                pswd = pwd.getText().toString().trim();
                repswd = repwd.getText().toString().trim();

                if(f_name.equals("")){
                    name1.setError("Please Enter valid first name");
                    name1.requestFocus();
                    pDialog.dismiss();
                }
                else if(l_name.equals("")){
                    name2.requestFocus();
                    name2.setError("Please enter valid last name");
                    pDialog.dismiss();
                }
                else if(ml.equals("")){
                    e_mail.setError("please enter valid email");
                    e_mail.requestFocus();
                    pDialog.dismiss();
                }
                else if(pswd.equals("")){
                    pwd.setError("Please enter valid password");
                    pwd.requestFocus();
                    pDialog.dismiss();
                }
                else if(repswd.equals("")){
                    repwd.setError("Please enter valid password");
                    repwd.requestFocus();
                    pDialog.dismiss();
                }
                else if(!pswd.equals(repswd)){
                    pwd.setError("Password(s) are not matched");
                    repwd.setError("Enter the same password as above");
                    pwd.requestFocus();
                    repwd.requestFocus();
                    pDialog.dismiss();
                }
                else{
                    mAuth.createUserWithEmailAndPassword(ml, pswd).addOnCompleteListener(register.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            pDialog.dismiss();
                            if (task.isSuccessful()) {
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(f_name + " " + l_name).build();

                                user.updateProfile(profileUpdates);
                                mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if (task.isSuccessful()) {

                                            final info user;
                                            user = new info(
                                                    f_name,
                                                    l_name,
                                                    ml
                                            );

                                            FirebaseDatabase.getInstance().getReference("User")
                                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                    .child("fname").setValue(user.getF_name()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        FirebaseDatabase.getInstance().getReference("User")
                                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                                .child("lname").setValue(user.getL_name());
                                                        FirebaseDatabase.getInstance().getReference("User")
                                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                                .child("email").setValue(user.getMl());
                                                        Toast.makeText(register.this,"Please log in after email varification",Toast.LENGTH_SHORT).show();
                                                        Intent intToHome = new Intent(register.this,dip.class);
                                                        intToHome.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                                        startActivity(intToHome);
                                                        pDialog.dismiss();
                                                        finish();
                                                    } else {
                                                        pDialog.dismiss();
                                                        Toast.makeText(register.this,"Try again",Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });

                                        } else {
                                            Toast.makeText( register.this , task.getException().getMessage() , Toast.LENGTH_SHORT ).show();
                                        }
                                    }
                                });


                            } else {
                                overridePendingTransition(0, 0);
                                finish();
                                overridePendingTransition(0, 0);
                                startActivity(getIntent());

                                pDialog.dismiss();
                                Toast.makeText(register.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            onBackPressed();
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(register.this, login.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(i);
        finish();
    }
    public void disconnectFromFacebook() {

        if (AccessToken.getCurrentAccessToken() == null) {
            return; // already logged out
        }

        new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permissions/", null, HttpMethod.DELETE, new GraphRequest
                .Callback() {
            @Override
            public void onCompleted(GraphResponse graphResponse) {

                LoginManager.getInstance().logOut();

            }
        }).executeAsync();
    }
}