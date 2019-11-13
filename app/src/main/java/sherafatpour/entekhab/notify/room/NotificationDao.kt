package sherafatpour.entekhab.notify.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

import sherafatpour.entekhab.notify.model.Notification

@Dao
interface NotificationDao {

    @get:Query("SELECT * FROM note_table WHERE status = 0 ORDER BY priority ASC")
    val allUnreadNotification: LiveData<List<Notification>>

    @get:Query("SELECT * FROM note_table WHERE status = 1 ORDER BY priority ASC")
    val allReadNotification: LiveData<List<Notification>>

    @Insert
    fun insert(note: Notification)

    @Update
    fun update(note: Notification)

    @Delete
    fun delete(note: Notification)

    @Query("DELETE FROM note_table")
    fun deleteAllNote()

    @Query("UPDATE note_table SET status = 1 WHERE id = :id")
    fun updateStatus(id: Int)

}
