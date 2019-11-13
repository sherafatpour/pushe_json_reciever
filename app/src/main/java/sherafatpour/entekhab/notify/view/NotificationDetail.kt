package sherafatpour.entekhab.notify.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders

import kotlinx.android.synthetic.main.activity_notification_detail.*
import sherafatpour.entekhab.notify.R
import sherafatpour.entekhab.notify.util.GlobalClass
import sherafatpour.entekhab.notify.viewModel.NotificationViewModel

class NotificationDetail : AppCompatActivity() {
    lateinit var viewModel: NotificationViewModel
    lateinit var globalClass: GlobalClass
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_detail)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        globalClass = GlobalClass()
        globalClass.setFont(vg_detail)
        globalClass.statusBarColor(window)

        if (intent.hasExtra("id")){

            val id = intent.getIntExtra("id",0);
                    viewModel = ViewModelProviders.of(this).get(NotificationViewModel::class.java)
                     viewModel.updateStatus(id)

        }

    }

}
