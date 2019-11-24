package android.eko.todo.data.local;

import io.realm.RealmObject;

public class TodoLocal extends RealmObject {
    public int todoId;
    public int userId;
    public String todoTitle;
    public boolean todoCompleted;
}
