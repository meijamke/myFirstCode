package com.example.force_offline;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.force_offline.Utilities.ExplicitIntentUtils;

public class LoginActivity extends BaseActivity {

    private EditText userNameEditText;
    private EditText passwordEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userNameEditText = findViewById(R.id.user_name_edit_text);
        passwordEditText = findViewById(R.id.password_edit_text);
    }

    public void Login(View view) {
        String userName = userNameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        if ("admin".equals(userName) && "1234".equals(password)) {
            ExplicitIntentUtils.intentToMainActivity(this);
            finish();
        } else
            Toast.makeText(this, "User Name or Password is invalid", Toast.LENGTH_LONG).show();
    }
}
