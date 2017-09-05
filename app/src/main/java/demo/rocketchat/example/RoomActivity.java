package demo.rocketchat.example;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rocketchat.common.data.model.ErrorObject;
import com.rocketchat.core.RocketChatAPI;
import com.rocketchat.core.model.SubscriptionObject;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import demo.rocketchat.example.activity.MyAdapterActivity;
import demo.rocketchat.example.adapter.MyCustomAdapter;
import demo.rocketchat.example.application.RocketChatApplication;
import demo.rocketchat.example.utils.AppUtils;

@EActivity (R.layout.activity_room)
public class RoomActivity extends MyAdapterActivity{

    RocketChatAPI api;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @ViewById (R.id.my_recycler_view)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        api = ((RocketChatApplication)getApplicationContext()).getRocketChatAPI();
        api.getConnectivityManager().register(this);
        api.getSubscriptions(this);
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    void afterViewsSet(){
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }


    @UiThread
    @Override
    public void onGetSubscriptions(List<SubscriptionObject> subscriptions, ErrorObject error) {
        adapter = new MyCustomAdapter(subscriptions,this);
        recyclerView.setAdapter(adapter);
    }

    @UiThread
    @Override
    public void onConnect(String sessionID) {
        Snackbar
                .make(findViewById(R.id.activity_room), R.string.connected, Snackbar.LENGTH_LONG)
                .show();
    }

    @UiThread
    @Override
    public void onDisconnect(boolean closedByServer) {
        AppUtils.getSnackbar(findViewById(R.id.activity_room), R.string.disconnected_from_server)
                .setAction("RETRY", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        api.reconnect();
                    }
                })
                .show();

    }

    @UiThread
    @Override
    public void onConnectError(Exception websocketException) {
        AppUtils.getSnackbar(findViewById(R.id.activity_room), R.string.connection_error)
                .setAction("RETRY", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        api.reconnect();
                    }
                })
                .show();
    }

    @Override
    protected void onDestroy() {
        api.getConnectivityManager().unRegister(this);
        super.onDestroy();
    }
}
