package demo.rocketchat.example.application;

import android.app.Application;

import io.rocketchat.core.RocketChatAPI;

/**
 * Created by sachin on 13/8/17.
 */

public class RocketChatApplication extends Application {

    public static String url = "wss://demo.rocket.chat/websocket/";

    RocketChatAPI rocketChatAPI;

    @Override
    public void onCreate() {
        rocketChatAPI = new RocketChatAPI(url);
    }

    public RocketChatAPI getRocketChatAPI() {
        return rocketChatAPI;
    }
}
