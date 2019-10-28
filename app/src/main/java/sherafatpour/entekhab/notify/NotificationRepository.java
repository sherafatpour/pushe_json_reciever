package sherafatpour.entekhab.notify;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NotificationRepository {
    private NotificationDao notificationDao;
    private LiveData<List<Notification>> allNote;

    public NotificationRepository(Application application) {
        NotificationDatabase database = NotificationDatabase.getInstance(application);
        notificationDao = database.noteDao();
        allNote = notificationDao.getAllNote();
    }

    public void insert(Notification note) {
        new InsertNoteAsyncTask(notificationDao).execute(note);
    }

    public void update(Notification note) {
    new UpdateNoteAsyncTask(notificationDao).execute(note);
    }

    public void delete(Notification note) {
        new DeleteNoteAsyncTask(notificationDao).execute(note);

    }

    public void deleteAllNotes() {
        new DeleteAllNoteAsyncTask(notificationDao).execute();

    }

    public LiveData<List<Notification>> getAllNotes() {
        return allNote;
    }


    private static class InsertNoteAsyncTask extends AsyncTask<Notification, Void, Void> {
        private NotificationDao notificationDao;

        private InsertNoteAsyncTask(NotificationDao notificationDao) {
            this.notificationDao = notificationDao;
        }

        @Override
        protected Void doInBackground(Notification... notes) {
            notificationDao.insert(notes[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<Notification, Void, Void> {
        private NotificationDao notificationDao;

        private UpdateNoteAsyncTask(NotificationDao notificationDao) {
            this.notificationDao = notificationDao;
        }

        @Override
        protected Void doInBackground(Notification... notes) {
            notificationDao.update(notes[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<Notification, Void, Void> {
        private NotificationDao notificationDao;

        private DeleteNoteAsyncTask(NotificationDao notificationDao) {
            this.notificationDao = notificationDao;
        }

        @Override
        protected Void doInBackground(Notification... notes) {
            notificationDao.delete(notes[0]);
            return null;
        }
    }


    private static class DeleteAllNoteAsyncTask extends AsyncTask<Notification, Void, Void> {
        private NotificationDao notificationDao;

        private DeleteAllNoteAsyncTask(NotificationDao notificationDao) {
            this.notificationDao = notificationDao;
        }

        @Override
        protected Void doInBackground(Notification... notes) {
            notificationDao.deleteAllNote();
            return null;
        }
    }

}
