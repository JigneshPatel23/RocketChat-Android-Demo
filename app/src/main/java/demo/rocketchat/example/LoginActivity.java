package demo.rocketchat.example;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;

import demo.rocketchat.example.activity.MyAdapterActivity;
import demo.rocketchat.example.application.RocketChatApplication;
import demo.rocketchat.example.utils.AppUtils;
import io.rocketchat.core.RocketChatAPI;

public class LoginActivity extends MyAdapterActivity {

    AppCompatEditText username;
    AppCompatEditText password;
    AppCompatButton login;
    RocketChatAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("RocketChat Login");

        username = (AppCompatEditText) findViewById(R.id.username);
        password = (AppCompatEditText) findViewById(R.id.password);
        login = (AppCompatButton) findViewById(R.id.password);

        api = ((RocketChatApplication)getApplicationContext()).getRocketChatAPI();
        api.setReconnectionStrategy(null);
        api.setPingInterval(3000);
        api.connect(this);
    }

    @Override
    public void onConnect(String sessionID) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Snackbar
                        .make(findViewById(R.id.activity_login), R.string.connected, Snackbar.LENGTH_LONG)
                        .show();
            }
        });
    }

    @Override
    public void onDisconnect(boolean closedByServer) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AppUtils.getSnackbar(findViewById(R.id.activity_login),R.string.disconnected_from_server)
                        .setAction("RETRY", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                api.reconnect();
                            }
                        })
                        .show();
            }
        });
    }

    @Override
    public void onConnectError(Exception websocketException) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AppUtils.getSnackbar(findViewById(R.id.activity_login),R.string.connection_error)
                        .setAction("RETRY", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                api.reconnect();

                            }
                        })
                        .show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        api.getConnectivityManager().unRegister(this);
        super.onDestroy();
    }
}
