package demo.rocketchat.example.application;

import android.app.Application;

import com.rocketchat.core.RocketChatAPI;

/**
 * Created by sachin on 13/8/17.
 */

public class RocketChatApplication extends Application {

    public static String url = "https://demo.rocket.chat";

    RocketChatAPI rocketChatAPI;

    @Override
    public void onCreate() {
        super.onCreate();
        rocketChatAPI = new RocketChatAPI(url);
    }

    public RocketChatAPI getRocketChatAPI() {
        return rocketChatAPI;
    }

}
