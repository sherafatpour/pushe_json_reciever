package sherafatpour.entekhab.notify

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

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

        when (position) {
            0 -> {
                holder.img.setImageResource(R.drawable.ic_buy)
                holder.brand.setImageResource(R.drawable.ic_snowa)
                holder.priorityColor.setBackgroundColor(context.resources.getColor(R.color.light_blue_500))

            }
            1 -> {
                holder.img.setImageResource(R.drawable.ic_warning)
                holder.brand.setImageResource(R.drawable.ic_daewoo)
                holder.priorityColor.setBackgroundColor(context.resources.getColor(R.color.red_A100))
            }
            2 -> {
                holder.img.setImageResource(R.drawable.ic_undo_buy)
                holder.brand.setImageResource(R.drawable.ic_daewoo)
                holder.priorityColor.setBackgroundColor(context.resources.getColor(R.color.amber_400))
            }
            3 -> {
                holder.img.setImageResource(R.drawable.ic_buy)
                holder.brand.setImageResource(R.drawable.ic_snowa)
                holder.priorityColor.setBackgroundColor(context.resources.getColor(R.color.deep_purple_300))

            }
            4 -> {
                holder.img.setImageResource(R.drawable.ic_buy_cansel)
                holder.brand.setImageResource(R.drawable.ic_daewoo)
                holder.priorityColor.setBackgroundColor(context.resources.getColor(R.color.green_A200))

            }

            5 -> {
                holder.img.setImageResource(R.drawable.ic_message)
                holder.brand.setImageResource(R.drawable.ic_snowa)
                holder.priorityColor.setBackgroundColor(context.resources.getColor(R.color.grey_300))

            }

            6 -> {
                holder.img.setImageResource(R.drawable.ic_undo_buy)
                holder.brand.setImageResource(R.drawable.ic_daewoo)
                holder.priorityColor.setBackgroundColor(context.resources.getColor(R.color.light_blue_500))

            }
        }
        holder.setOnItemClickListener(object : CustomOnclickListener {
            override fun onItemClick(view: View, position: Int) {

                holder.more.visibility = View.VISIBLE
                notifyItemChanged(position)

            }


        })

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
        val viewGroup: ViewGroup
        var more: ConstraintLayout
        var customOnclickListener: CustomOnclickListener? = null


        init {
            textViewTitle = itemView.findViewById(R.id.text_view_title)
            textViewDescription = itemView.findViewById(R.id.text_view_description)
            img = itemView.findViewById(R.id.img)
            brand = itemView.findViewById(R.id.brand)
            priorityColor = itemView.findViewById(R.id.priority_color)
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
