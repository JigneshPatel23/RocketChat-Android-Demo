package demo.rocketchat.example.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rocketchat.common.utils.Utils;
import com.rocketchat.core.model.SubscriptionObject;
import com.squareup.picasso.Picasso;

import java.util.List;

import demo.rocketchat.example.ChatActivity_;
import demo.rocketchat.example.R;
import demo.rocketchat.example.utils.UserAvatarHelper;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by aniket on 05/09/17.
 */

public class MyCustomAdapter extends RecyclerView.Adapter<MyCustomAdapter.MyViewHolder> {

    private List<SubscriptionObject> roomObjects;
    Context context;

    public MyCustomAdapter(List<SubscriptionObject> roomObjects, Context context) {
        this.roomObjects = roomObjects;
        this.context = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_layout, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String roomName = roomObjects.get(position).getRoomName();
        holder.textView.setText(roomName);
        Drawable drawable = UserAvatarHelper.getTextDrawable(roomName, context);

        Picasso.with(context)
                .load(Utils.getAvatar(roomName))
                .transform(new RoundedCornersTransformation(UserAvatarHelper.getRadius(context),0))
                .placeholder(drawable)
                .error(drawable)
                .into(holder.imageView);

        holder.roomItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChatActivity_.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return roomObjects.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView roomItem;
        TextView textView;
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            roomItem = (CardView) itemView.findViewById(R.id.card_view);
            textView = (TextView) itemView.findViewById(R.id.channel);
            imageView = (ImageView) itemView.findViewById(R.id.image_view);
        }
    }
}
