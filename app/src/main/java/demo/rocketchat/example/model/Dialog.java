package demo.rocketchat.example.model;

import com.rocketchat.common.data.model.Room;
import com.rocketchat.common.utils.Utils;
import com.stfalcon.chatkit.commons.models.IDialog;

import java.util.ArrayList;

/**
 * Created by sachin on 25/8/17.
 */

public class Dialog implements IDialog<Message> {

    private String id;
    private String dialogPhoto;
    private String dialogName;
    private ArrayList<User> users;
    private Message lastMessage;

    private int unreadCount;

    public Dialog(String id, String name, String photo,
                  ArrayList<User> users, Message lastMessage, int unreadCount) {

        this.id = id;
        this.dialogName = name;
        this.dialogPhoto = photo;
        this.users = users;
        this.lastMessage = lastMessage;
        this.unreadCount = unreadCount;
    }

    public Dialog (Room room) {
        this.id = room.getRoomId();
        this.dialogPhoto = Utils.getAvatar(room.getRoomName());
        this.dialogName = room.getRoomName();
        users = null;
        lastMessage = null;
        unreadCount = 0;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getDialogPhoto() {
        return dialogPhoto;
    }

    @Override
    public String getDialogName() {
        return dialogName;
    }

    @Override
    public ArrayList<User> getUsers() {
        return users;
    }

    @Override
    public Message getLastMessage() {
        return lastMessage;
    }

    @Override
    public void setLastMessage(Message lastMessage) {
        this.lastMessage = lastMessage;
    }

    @Override
    public int getUnreadCount() {
        return unreadCount;
    }
}
