package com.ytosko.covcatch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.request.RequestOptions;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class login extends AppCompatActivity {

    private EditText mail,pwd;
    private Button login;
    private TextView reset, reg,resend;
    private LinearLayout lpgoogle;
    FirebaseAuth mFirebaseAuth;
    private ProgressDialog pDialog;
    private LoginButton lg;
    private SignInButton ggl;
    private CallbackManager cl;
    String first_name,last_name,email,id;
    GoogleSignInClient mGoogleSignInClient;
    String maill,pinn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FacebookSdk.sdkInitialize(getApplicationContext());
        FacebookSdk.setApplicationId(getResources().getString(R.string.facebook_app_id));

        mFirebaseAuth = FirebaseAuth.getInstance();
        ActionBar a = getSupportActionBar();
        a.hide();
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        pDialog = new ProgressDialog(login.this);
        pDialog.setMessage("Authenticating");

        mail = findViewById(R.id.mail);
        pwd = findViewById(R.id.pin);
        login = findViewById(R.id.btn_login);

        reset = findViewById(R.id.link_reset);
        reg = findViewById(R.id.link_signup);
        resend = findViewById(R.id.lesent);

        lg = findViewById(R.id.login_button_fb);
        lpgoogle = findViewById(R.id.ggl);
        FirebaseAuth.getInstance().signOut();
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(login.this, register.class);
                startActivity(i);
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToHome = new Intent(login.this, resetpwd.class);
                intToHome.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intToHome);
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maill = mail.getText().toString().trim();
                pinn = pwd.getText().toString().trim();

                if(maill.isEmpty()){
                    mail.setError("Please enter email id");
                    mail.requestFocus();
                }
                else  if(pinn.isEmpty()){
                    pwd.setError("Please enter your pin");
                    pwd.requestFocus();
                }
                else  if(maill.isEmpty() && pinn.isEmpty()){
                    Toast.makeText(login.this,"Fields Are Empty!",Toast.LENGTH_SHORT).show();
                }
                else  if(!(maill.isEmpty() && pinn.isEmpty())){
                    pDialog.show();
                    mFirebaseAuth.signInWithEmailAndPassword(maill, pinn).addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                pDialog.dismiss();
                                Toast.makeText(login.this,"Authentication error",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                boolean emailVerified = user.isEmailVerified();
                                if(emailVerified){
                                    Intent intToHome = new Intent(login.this, main.class);
                                    intToHome.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                    startActivity(intToHome);
                                    finish();
                                }
                                else if(!emailVerified)
                                {
                                    pDialog.dismiss();
                                    FirebaseAuth.getInstance().signOut();
                                    resend.setVisibility(View.VISIBLE);
                                    Toast.makeText(login.this,"E - mail is not vatified",Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
                }
                else{

                    pDialog.dismiss();
                    Toast.makeText(login.this,"Error Occurred!",Toast.LENGTH_SHORT).show();

                }
            }
        });

        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                    FirebaseAuth.getInstance().getCurrentUser().reload();
                    if (!FirebaseAuth.getInstance().getCurrentUser().isEmailVerified()) {
                        FirebaseAuth.getInstance().getCurrentUser().sendEmailVerification();
                        Toast.makeText(login.this, "Email re-sent successfully", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(login.this, "Your email has been verified! You can login now.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        cl = CallbackManager.Factory.create();
        lg.setReadPermissions(Arrays.asList("email","public_profile"));
        //checkit();
        lg.registerCallback(cl, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(com.ytosko.covcatch.login.this, gso);

        lpgoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        cl.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }

    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            first_name = "";
            last_name = "";
            email = "";

            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(login.this);
            if (acct != null) {
                first_name = acct.getGivenName();
                last_name = acct.getFamilyName();
                email = acct.getEmail();
            }
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            //Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
        }
        Intent i = new Intent(login.this, register.class);
        i.putExtra("fname", first_name);
        i.putExtra("lname", last_name);
        i.putExtra("email", email);
        startActivity(i);
    }

    AccessTokenTracker tt = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {

            if(currentAccessToken==null){
                first_name = "";
                last_name = "";
                email = "";
            }
            else{
                loaduserdata(currentAccessToken);
            }
        }
    };

    private void loaduserdata(AccessToken newaccess){
        GraphRequest gr = GraphRequest.newMeRequest(newaccess, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {

                try {
                    Log.e("LoginActivity Response ", response.toString());
                    first_name = object.getString("first_name");
                    last_name = object.getString("last_name");
                    email = object.getString("email");

                    //Log.e("nm : ", first_name + " " + last_name + " " + email + " " + id);

                    RequestOptions requestOptions = new RequestOptions();
                    requestOptions.dontAnimate();


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Intent i = new Intent(login.this, register.class);
                i.putExtra("fname", first_name);
                i.putExtra("lname", last_name);
                i.putExtra("email", email);
                startActivity(i);

            }
        });
        Bundle pr = new Bundle();
        pr.putString("fields","first_name,last_name,email");
        gr.setParameters(pr);
        gr.executeAsync();
    }
    private void checkit(){
        if(AccessToken.getCurrentAccessToken() != null){
            loaduserdata(AccessToken.getCurrentAccessToken());
        }
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
        finish();
        System.exit(0);

    }
}