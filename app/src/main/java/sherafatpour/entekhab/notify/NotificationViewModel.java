package sherafatpour.entekhab.notify;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NotificationViewModel extends AndroidViewModel {
    private NotificationRepository repository;
    private LiveData<List<Notification>> allNote;
    public NotificationViewModel(@NonNull Application application) {
        super(application);

        repository = new NotificationRepository(application);
        allNote = repository.getAllNotes();
    }

    public void insert(Notification note){
        repository.insert(note);
    }
    public void update(Notification note){
        repository.update(note);
    }
    public void delete(Notification note){
        repository.delete(note);
    }
    public void deleteAllNotes(){
        repository.deleteAllNotes();
    }

    public  LiveData<List<Notification>> getAllNote ()
    {
       return allNote;
    }
}
