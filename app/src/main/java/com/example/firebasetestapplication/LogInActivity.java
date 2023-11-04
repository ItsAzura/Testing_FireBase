package com.example.firebasetestapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.firebasetestapplication.databinding.ActivityLogInBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
public class LogInActivity extends AppCompatActivity {

    ActivityLogInBinding binding;
    String userName, password;
    FirebaseDatabase db;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLogInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnEnterLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName = binding.etUserName.getText().toString();
                password = binding.etPass.getText().toString();

                if (!userName.isEmpty() && !password.isEmpty()) {
                    checkUserLogin(userName, password);
                } else {
                    Toast.makeText(LogInActivity.this, "Please enter both username and password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void checkUserLogin(final String userName, final String password) {
        db = FirebaseDatabase.getInstance();
        reference = db.getReference("Users");

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(userName).exists()) {
                    Users user = dataSnapshot.child(userName).getValue(Users.class);

                    if (user != null && user.getPassWord().equals(password)) {
                        Intent intent = new Intent(LogInActivity.this, HomePageActivity.class);
                        startActivity(intent);
                        finish();
                    } else { Toast.makeText(LogInActivity.this, "Nhập sai mật khẩu", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LogInActivity.this, "Người dùng không tồn tại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Xử lý khi có lỗi xảy ra trong quá trình đọc dữ liệu từ Firebase
                Toast.makeText(LogInActivity.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}