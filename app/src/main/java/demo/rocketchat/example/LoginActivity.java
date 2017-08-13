package demo.rocketchat.example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;

public class LoginActivity extends AppCompatActivity {

    AppCompatEditText username;
    AppCompatEditText password;
    AppCompatButton login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (AppCompatEditText) findViewById(R.id.username);
        password = (AppCompatEditText) findViewById(R.id.password);
        login = (AppCompatButton) findViewById(R.id.password);

    }
}
