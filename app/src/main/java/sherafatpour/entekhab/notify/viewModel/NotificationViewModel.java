package sherafatpour.entekhab.notify.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import sherafatpour.entekhab.notify.model.Notification;
import sherafatpour.entekhab.notify.room.NotificationRepository;

public class NotificationViewModel extends AndroidViewModel {
    private NotificationRepository repository;
    private LiveData<List<Notification>> unreadNotification;
    private LiveData<List<Notification>> readNotification;

    public NotificationViewModel(@NonNull Application application) {
        super(application);

        repository = new NotificationRepository(application);
        unreadNotification = repository.getAllUnReadNotification();
        readNotification = repository.getAllReadNotification();
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
    public void updateStatus(int id){ repository.updateStatus(id); }

    public  LiveData<List<Notification>> getUnReadNotification ()
    {
       return unreadNotification;
    }
    public  LiveData<List<Notification>> getReadNotification ()
    {
       return readNotification;
    }
}
