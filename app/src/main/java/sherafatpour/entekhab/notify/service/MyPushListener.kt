package sherafatpour.entekhab.notify.service

import org.json.JSONObject

import co.ronash.pushe.PusheListenerService
import sherafatpour.entekhab.notify.model.Notification
import sherafatpour.entekhab.notify.viewModel.NotificationViewModel

class MyPushListener : PusheListenerService() {
    lateinit var noteViewModel: NotificationViewModel

    override fun onMessageReceived(customContent: JSONObject?, pushMessage: JSONObject?) {
        if (customContent == null || customContent.length() == 0)
            return  //json is empty
        android.util.Log.i("Pushe", "Custom json Message: $customContent") //print json to logCat
        //Do something with json
        try {
            noteViewModel = NotificationViewModel(application)
            val title = customContent.getString("title")
            val content = customContent.getString("content")
            val priority = customContent.getInt("priority")
            val type = customContent.getString("type")
            val brand = customContent.getString("brand")

            val note = Notification(title, content, priority, type, brand, 0, false)
            noteViewModel.insert(note)
        } catch (e: Exception) {
            android.util.Log.e("TAG", "Exception in parsing json", e)
        }

    }
}