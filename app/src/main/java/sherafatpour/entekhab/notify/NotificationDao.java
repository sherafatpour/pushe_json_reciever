package sherafatpour.entekhab.notify;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NotificationDao {
    @Insert
    void insert(Notification note);

    @Update
    void update(Notification note);

    @Delete
    void delete(Notification note);

    @Query("DELETE FROM note_table")
    void  deleteAllNote();

    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    LiveData<List<Notification>> getAllNote();
}
