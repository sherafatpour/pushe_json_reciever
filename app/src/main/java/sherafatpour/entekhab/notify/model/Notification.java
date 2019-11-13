package sherafatpour.entekhab.notify.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Notification {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String description;
    private int priority;     // 0..10
    private String type;     // cansel / undo / warning / message /sale
    private String brand;   // snowa / daewoo /technogas /araks
    private int status;    // 0=> unread  1=>read
    private boolean expend;

    public Notification(String title, String description, int priority, String type, String brand, int status, boolean expend) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.type = type;
        this.brand = brand;
        this.status = status;
        this.expend = expend;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isExpend() {
        return expend;
    }

    public void setExpend(boolean expend) {
        this.expend = expend;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
