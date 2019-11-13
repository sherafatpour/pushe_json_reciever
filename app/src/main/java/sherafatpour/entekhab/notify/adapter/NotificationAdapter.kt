package sherafatpour.entekhab.notify.adapter

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import sherafatpour.entekhab.notify.model.Notification
import sherafatpour.entekhab.notify.view.NotificationDetail
import sherafatpour.entekhab.notify.R

import sherafatpour.entekhab.notify.util.GlobalClass


class NotificationAdapter(var context: Context) : ListAdapter<Notification, NotificationAdapter.NotHolder>(DIFF_CALLBACK) {
    private var globalClass: GlobalClass? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.notify_item, null)
        globalClass = GlobalClass()

        return NotHolder(itemView)
    }

    override fun onBindViewHolder(holder: NotHolder, position: Int) {
        val currentNote = getItem(position)
        globalClass!!.setFont(holder.viewGroup)
        holder.textViewTitle.text = currentNote.title
        // holder.textViewPriority.setText(String.valueOf(currentNote.getPriority()));
        holder.textViewDescription.text = currentNote.description

        if (getNoteAt(position).isExpend){

            holder.more.visibility = View.VISIBLE
        }else{

            holder.more.visibility = View.GONE

        }




        when (getNoteAt(position).brand) {
            "snowa" -> {
                holder.brand.setImageResource(R.drawable.ic_snowa)
            }
            "daewoo" -> {
                holder.brand.setImageResource(R.drawable.ic_daewoo)
            }
            /*"technoGas" -> {
                holder.brand.setImageResource(R.drawable.ic_daewoo)
            }
            "araks" -> {
                holder.brand.setImageResource(R.drawable.ic_snowa)

            }*/
        }


        when (getNoteAt(position).type) {

            "sale"->{

                holder.img.setImageResource(R.drawable.ic_sale)

            }
            "cansel" -> {
                holder.img.setImageResource(R.drawable.ic_buy_cansel)

            }
            "undo" -> {
                holder.img.setImageResource(R.drawable.ic_undo_buy)

            }
            "message" -> {
                holder.img.setImageResource(R.drawable.ic_message)

            }
            "warning" -> {
                holder.img.setImageResource(R.drawable.ic_warning)

            }

        }

        when (getNoteAt(position).priority) {


            0 -> {

                holder.priorityColor.setBackgroundColor(ContextCompat.getColor(context,R.color.grey_300))

            }

            1-> {

                holder.priorityColor.setBackgroundColor(ContextCompat.getColor(context,R.color.red_A100))//red_A100

            }
           2 -> {
                holder.priorityColor.setBackgroundColor(ContextCompat.getColor(context,R.color.amber_400))//light_blue_500
            }
            3 -> {

                holder.priorityColor.setBackgroundColor(ContextCompat.getColor(context,R.color.deep_purple_300))
            }
            4-> {

                holder.priorityColor.setBackgroundColor(ContextCompat.getColor(context,R.color.light_blue_500))

            }
            5 -> {

                holder.priorityColor.setBackgroundColor(ContextCompat.getColor(context,R.color.green_A200))

            }




        }



        holder.setOnItemClickListener(object : CustomOnclickListener {
            override fun onItemClick(view: View, position: Int) {

                if (currentNote.isExpend){
                    holder.more.visibility = View.GONE
                    getItem(position).isExpend = false
                    notifyItemChanged(position)
                }else{
                    holder.more.visibility = View.VISIBLE
                    getItem(position).isExpend = true
                    notifyItemChanged(position)

                }


            }


        })

        holder.detail.setOnClickListener {
            val intent = Intent(context, NotificationDetail::class.java)
            intent.flags = FLAG_ACTIVITY_NEW_TASK
            intent.putExtra("id",getNoteAt(position).id)
            context.startActivity(intent)

        }

    }


    fun getNoteAt(position: Int): Notification {
        return getItem(position)
    }

    inner class NotHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val textViewTitle: TextView
        val textViewDescription: TextView
        val img: ImageView
        val brand: ImageView
        val priorityColor: ImageView
        val detail: ImageButton
        val viewGroup: ViewGroup
        var more: ConstraintLayout
        var customOnclickListener: CustomOnclickListener? = null


        init {
            textViewTitle = itemView.findViewById(R.id.text_view_title)
            textViewDescription = itemView.findViewById(R.id.text_view_description)
            img = itemView.findViewById(R.id.img)
            brand = itemView.findViewById(R.id.brand)
            priorityColor = itemView.findViewById(R.id.priority_color)
            detail = itemView.findViewById(R.id.detail)
            viewGroup = itemView.findViewById(R.id.vg_notify)
            more = itemView.findViewById(R.id.more)
            itemView.setOnClickListener(this)


        }

        fun setOnItemClickListener(customOnclickListener: CustomOnclickListener) {

            this.customOnclickListener = customOnclickListener
        }


        override fun onClick(v: View?) {

            this.customOnclickListener!!.onItemClick(v!!, layoutPosition)

        }
    }

    interface CustomOnclickListener {

        fun onItemClick(view: View, position: Int)

    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Notification>() {
            override fun areItemsTheSame(oldItem: Notification, newItem: Notification): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Notification, newItem: Notification): Boolean {
                return oldItem.title == newItem.title && oldItem.description == newItem.description && oldItem.priority == newItem.priority
            }
        }
    }
}
