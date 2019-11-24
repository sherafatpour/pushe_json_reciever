package sherafatpour.entekhab.notify.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_notification_detail.*
import kotlinx.android.synthetic.main.content_notification_detail.*


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

            val id = intent.getIntExtra("id",0)
            val priority = intent.getIntExtra("priority",0)
            val title = intent.getStringExtra("title")
            val type = intent.getStringExtra("type")
            val desc = intent.getStringExtra("description")
            val brd = intent.getStringExtra("brand")
                    viewModel = ViewModelProviders.of(this).get(NotificationViewModel::class.java)
                     viewModel.updateStatus(id)


            text_view_title.text = title
            text_view_description.text = desc
            description.text = desc
            setPriority(priority)
            setType(type)
            setBrand(brd)


    }

}

    private fun setBrand(brd: String?) {
        when (brd) {
            "snowa" -> {
                brand.setImageResource(R.drawable.ic_snowa)
            }
            "daewoo" -> {
                brand.setImageResource(R.drawable.ic_daewoo)
            }
            /*"technoGas" -> {
                    holder.brand.setImageResource(R.drawable.ic_daewoo)
                }
                "araks" -> {
                    holder.brand.setImageResource(R.drawable.ic_snowa)

                }*/
        }
    }

    private fun setType(type: String?) {
        when (type) {

            "sale" -> {

                img.setImageResource(R.drawable.ic_sale)

            }
            "cancel" -> {
                img.setImageResource(R.drawable.ic_buy_cansel)

            }
            "undo" -> {
                img.setImageResource(R.drawable.ic_undo_buy)

            }
            "message" -> {
                img.setImageResource(R.drawable.ic_message)

            }
            "warning" -> {
                img.setImageResource(R.drawable.ic_warning)

            }

        }
    }

    private fun setPriority(priority: Int) {
        when (priority) {


            0 -> {

                priority_color.setBackgroundColor(ContextCompat.getColor(this, R.color.grey_300))

            }

            1 -> {

                priority_color.setBackgroundColor(ContextCompat.getColor(this, R.color.red_A100))//red_A100

            }
            2 -> {
                priority_color.setBackgroundColor(ContextCompat.getColor(this, R.color.amber_400))//light_blue_500
            }
            3 -> {

                priority_color.setBackgroundColor(ContextCompat.getColor(this, R.color.deep_purple_300))
            }
            4 -> {

                priority_color.setBackgroundColor(ContextCompat.getColor(this, R.color.light_blue_500))

            }
            5 -> {

                priority_color.setBackgroundColor(ContextCompat.getColor(this, R.color.green_A200))

            }


        }
    }

}
