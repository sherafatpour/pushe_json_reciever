package sherafatpour.entekhab.notify;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Notification.class}, version = 1)
public abstract class NotificationDatabase extends RoomDatabase {

    private static NotificationDatabase instance;
    public abstract NotificationDao noteDao();

    public static synchronized NotificationDatabase getInstance(Context context) {
        if (instance == null) {

            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NotificationDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private NotificationDao notificationDao;

        private PopulateDbAsyncTask(NotificationDatabase db) {

            notificationDao = db.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            notificationDao.insert(new Notification("title", "Description1", 1));
            notificationDao.insert(new Notification("title", "Description2", 2));
            notificationDao.insert(new Notification("title", "Description3", 3));

            return null;
        }
    }
}
