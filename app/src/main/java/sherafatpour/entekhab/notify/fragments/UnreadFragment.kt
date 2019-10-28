package sherafatpour.entekhab.notify.fragments

import android.os.Bundle

import androidx.fragment.app.Fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import sherafatpour.entekhab.notify.Notification
import sherafatpour.entekhab.notify.NotificationAdapter
import sherafatpour.entekhab.notify.NotificationViewModel

import sherafatpour.entekhab.notify.R


class UnreadFragment : Fragment() {


    private var noteViewModel: NotificationViewModel? = null

companion object{

    fun newInstance()= UnreadFragment()
}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_unread, container, false)

        val recyclerView = view.findViewById(R.id.recycler_view) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
        val noteAdapter =  NotificationAdapter(activity!!.applicationContext)
        recyclerView.adapter = noteAdapter

        noteViewModel = ViewModelProviders.of(this).get(NotificationViewModel::class.java)

        noteViewModel!!.allNote.observe(this, Observer<List<Notification>> { notes ->
            //update RecyclerView
            Toast.makeText(activity, "onChange", Toast.LENGTH_SHORT).show()
            noteAdapter.submitList(notes)
        })



        // Inflate the layout for this fragment
        return view
    }


}
