package employeeapp.pooja.com.hrapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends Activity implements View.OnClickListener{

    //defining views
    private Button btn_Login, btn_reset_password;
    private EditText et_EmailToLogin;
    private EditText et_PasswordToLogin;


    //firebase auth object
    private FirebaseAuth firebaseAuth;

    //progress dialog
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //getting firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();

        //if the objects getcurrentuser method is not null
        //means user is already logged in

      //  if(firebaseAuth.getCurrentUser() != null) {
            //close this activity
     //       finish();
            //opening profile activity
     //       startActivity(new Intent(getApplicationContext(), WelcomeActivity.class));
     //   }

        //initializing views
        et_EmailToLogin = (EditText) findViewById(R.id.et_EmailToLogin);
        et_PasswordToLogin = (EditText) findViewById(R.id.et_PasswordToLogin);
        btn_Login = (Button) findViewById(R.id.btn_Login);


        progressDialog = new ProgressDialog(this);

        //attaching click listener
        btn_Login.setOnClickListener(this);




    }

    //method for user login
    private void userLogin(){
        final String email = et_EmailToLogin.getText().toString().trim();
        final String password  = et_PasswordToLogin.getText().toString().trim();


        //checking if email and passwords are empty
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email", Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password", Toast.LENGTH_LONG).show();
            return;
        }

        //if the email and password are not empty
        //displaying a progress dialog

        progressDialog.setMessage("Signing In Please Wait...");
        progressDialog.show();

        //logging in the user
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        //if the task is successfull
                        if(task.isSuccessful()){

                                //start the profile activity
                                finish();
                                startActivity(new Intent(getApplicationContext(), MenuActivity.class));

                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this,"Wrong Email or password", Toast.LENGTH_LONG).show();
                        }


                    }
                });

    }


    @Override
    public void onClick(View view) {
        if(view == btn_Login){
            userLogin();
        }

    }
}
