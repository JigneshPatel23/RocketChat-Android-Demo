package demo.rocketchat.example.application;

import android.app.Application;

import com.rocketchat.core.RocketChatAPI;
import com.squareup.picasso.Picasso;

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
        Picasso.Builder builder = new Picasso.Builder(this);
        Picasso built = builder.build();
        built.setIndicatorsEnabled(true);
        built.setLoggingEnabled(true);
        Picasso.setSingletonInstance(built);
    }

    public RocketChatAPI getRocketChatAPI() {
        return rocketChatAPI;
    }

}
