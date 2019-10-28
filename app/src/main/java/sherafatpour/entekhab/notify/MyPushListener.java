package sherafatpour.entekhab.notify;

import org.json.JSONObject;

import co.ronash.pushe.PusheListenerService;

public class MyPushListener extends PusheListenerService {
    NotificationViewModel noteViewModel;

    @Override
    public void onMessageReceived(JSONObject customContent, JSONObject pushMessage){
        if (customContent == null || customContent.length() == 0)
            return; //json is empty
        android.util.Log.i("Pushe","Custom json Message: "+ customContent.toString()); //print json to logCat
        //Do something with json
        try{
            noteViewModel = new NotificationViewModel(getApplication());

            String s1 = customContent.getString("title");
            String s2 = customContent.getString("content");
            int s3 = customContent.getInt("priority");
            android.util.Log.i("Pushe","Json Message\n title: " + s1 + "\n content: " + s2);

            Notification note = new Notification(s1, s2, s3);
            noteViewModel.insert(note);
        } catch (Exception e) {
            android.util.Log.e("TAG","Exception in parsing json" ,e);
        }}
}