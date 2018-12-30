package group10.onushandhan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Promit on 9/18/2017.
 */

public class Signup_Activity extends AppCompatActivity {

    private ImageView imageViewLogo;
    private EditText etName, etEmail, etPassword, etConfirmPassword, etContactNumber;
    private Spinner spinnerAge, spinnerGender;
    private Button bSignUp;
    private TextView tvLoginHere;
    private ProgressBar progressBarSignUp;

    private FirebaseAuth auth;
    private DatabaseReference databaseUser;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        imageViewLogo = (ImageView) findViewById(R.id.imageViewLogo);
        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etConfirmPassword = (EditText) findViewById(R.id.etConfirmPassword);
        etContactNumber = (EditText) findViewById(R.id.etContactNumber);
        spinnerAge = (Spinner) findViewById(R.id.spinnerAge);
        spinnerGender = (Spinner) findViewById(R.id.spinnerGender);
        bSignUp = (Button) findViewById(R.id.bSignUp);
        tvLoginHere = (TextView) findViewById(R.id.tvLoginHere);
        progressBarSignUp = (ProgressBar) findViewById(R.id.progressBarSignUp);

        auth = FirebaseAuth.getInstance();
        databaseUser = FirebaseDatabase.getInstance().getReference("users");

        bSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = etName.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString().trim();
                String confirmpassword = etConfirmPassword.getText().toString().trim();
                String contactnumber = etContactNumber.getText().toString();
                String age = spinnerAge.getSelectedItem().toString();
                String gender = spinnerGender.getSelectedItem().toString();


                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(Signup_Activity.this, "Enter Name!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Signup_Activity.this, "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Signup_Activity.this, "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(confirmpassword)) {
                    Toast.makeText(Signup_Activity.this, "Again Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 6) {
                    Toast.makeText(Signup_Activity.this, "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!password.equals(confirmpassword)) {
                    Toast.makeText(Signup_Activity.this, "Password Does Not Match", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(contactnumber)) {
                    Toast.makeText(Signup_Activity.this, "Enter Contact Number!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(age)) {
                    Toast.makeText(Signup_Activity.this, "Select Age", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(gender)) {
                    Toast.makeText(Signup_Activity.this, "Select Gender", Toast.LENGTH_SHORT).show();
                    return;
                }


                progressBarSignUp.setVisibility(View.VISIBLE);

                auth.createUserWithEmailAndPassword(email, password).
                        addOnCompleteListener(Signup_Activity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(Signup_Activity.this, "User Created", Toast.LENGTH_SHORT).show();
                                progressBarSignUp.setVisibility(View.GONE);

                                if (!task.isSuccessful()) {
                                    Toast.makeText(Signup_Activity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();

                                } else {
                                    //addUser();
                                    //startActivity(new Intent(Signup_Activity.this, Login_Activity.class));
                                    //finish();
                                }
                            }
                        });

                addUser();
                startActivity(new Intent(Signup_Activity.this, Login_Activity.class));


            }
        });

        tvLoginHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Signup_Activity.this, Login_Activity.class));
                finish();
            }
        });
    }


    private void addUser() {
        String id = databaseUser.push().getKey();

        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString().trim();
        String contactnumber = etContactNumber.getText().toString();
        String age = spinnerAge.getSelectedItem().toString();
        String gender = spinnerGender.getSelectedItem().toString();

        User user = new User(id, name, email, password, contactnumber, age, gender);
        databaseUser.child(id).setValue(user);

    }

}
