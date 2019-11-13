package sherafatpour.entekhab.notify.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import sherafatpour.entekhab.notify.model.Notification;

@Database(entities = {Notification.class}, version = 1, exportSchema = false)
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
           notificationDao.insert(new Notification("test", "Description1", 1,"sale","snowa",1,false));
           notificationDao.insert(new Notification("test", "Description1", 1,"sale","snowa",0,false));
          /*   notificationDao.insert(new Notification("test", "Description2", 2,0,false));
            notificationDao.insert(new Notification("title", "Description3", 3,0,false));*/

            return null;
        }
    }
}
