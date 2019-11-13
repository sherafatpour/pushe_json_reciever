package sherafatpour.entekhab.notify.room

import android.app.Application
import android.os.AsyncTask

import androidx.lifecycle.LiveData

import sherafatpour.entekhab.notify.model.Notification

class NotificationRepository(application: Application) {
    private val notificationDao: NotificationDao
    val allUnReadNotification: LiveData<List<Notification>>
    val allReadNotification: LiveData<List<Notification>>

    init {
        val database = NotificationDatabase.getInstance(application)
        notificationDao = database.noteDao()
        allUnReadNotification = notificationDao.allUnreadNotification
        allReadNotification = notificationDao.allReadNotification
    }

    fun insert(note: Notification) {
        InsertNoteAsyncTask(notificationDao).execute(note)
    }

    fun update(note: Notification) {
        UpdateNoteAsyncTask(notificationDao).execute(note)
    }

    fun delete(note: Notification) {
        DeleteNoteAsyncTask(notificationDao).execute(note)

    }

    fun deleteAllNotes() {
        DeleteAllNoteAsyncTask(notificationDao).execute()
    }

    fun updateStatus(id:Int) {
        UpdateStatusAsyncTask(notificationDao).execute(id)
    }


    private class InsertNoteAsyncTask(private val notificationDao: NotificationDao) : AsyncTask<Notification, Void, Void>() {

        override fun doInBackground(vararg notes: Notification): Void? {
            notificationDao.insert(notes[0])
            return null
        }
    }

    private class UpdateNoteAsyncTask(private val notificationDao: NotificationDao) : AsyncTask<Notification, Void, Void>() {

        override fun doInBackground(vararg notes: Notification): Void? {
            notificationDao.update(notes[0])
            return null
        }
    }

    private class DeleteNoteAsyncTask(private val notificationDao: NotificationDao) : AsyncTask<Notification, Void, Void>() {

        override fun doInBackground(vararg notes: Notification): Void? {
            notificationDao.delete(notes[0])
            return null
        }
    }

    private class DeleteAllNoteAsyncTask(private val notificationDao: NotificationDao) : AsyncTask<Notification, Void, Void>() {

        override fun doInBackground(vararg notes: Notification): Void? {
            notificationDao.deleteAllNote()
            return null
        }
    }


    private class UpdateStatusAsyncTask(private val notificationDao: NotificationDao) : AsyncTask<Int, Void, Void>() {
        override fun doInBackground(vararg params: Int?): Void? {
            notificationDao.updateStatus(params[0]!!)
            return null

        }


    }

}
