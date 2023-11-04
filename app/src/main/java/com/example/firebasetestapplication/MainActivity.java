package com.example.firebasetestapplication;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.example.firebasetestapplication.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    String firstName, lastName, age, userName, passWord;
    FirebaseDatabase db;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstName = binding.etFirstname.getText().toString();
                lastName = binding.etLastname.getText().toString();
                age = binding.etAge.getText().toString();
                userName = binding.etUsername.getText().toString();
                passWord = binding.etPass.getText().toString();

                if(!firstName.isEmpty() && !lastName.isEmpty() && !age.isEmpty() && !userName.isEmpty()){
                    Users users = new Users(firstName,lastName,age,userName,passWord);
                    db = FirebaseDatabase.getInstance();
                    reference = db.getReference("Users");
                    reference.child(userName).setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            binding.etFirstname.setText("");
                            binding.etLastname.setText("");
                            binding.etAge.setText("");
                            binding.etUsername.setText("");
                            binding.etPass.setText("");

                            Toast.makeText(MainActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        binding.btnGotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LogInActivity.class);
                startActivity(intent);
            }
        });
    }
}