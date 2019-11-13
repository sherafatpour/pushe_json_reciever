package sherafatpour.entekhab.notify.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import sherafatpour.entekhab.notify.model.Notification
import sherafatpour.entekhab.notify.adapter.NotificationAdapter
import sherafatpour.entekhab.notify.viewModel.NotificationViewModel

import sherafatpour.entekhab.notify.R



class ArchiveFragment : Fragment() {
    private var noteViewModel: NotificationViewModel? = null

companion object{

    fun newInstance() = ArchiveFragment()
}
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view=inflater.inflate(R.layout.fragment_archive, container, false)
        val recyclerView = view.findViewById(R.id.recycler_read) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
        val noteAdapter = NotificationAdapter(activity!!.applicationContext)
        recyclerView.adapter = noteAdapter

        noteViewModel = ViewModelProviders.of(this).get(NotificationViewModel::class.java)

        noteViewModel!!.readNotification.observe(this, Observer<List<Notification>> { notes ->
            //update RecyclerView
            noteAdapter.submitList(notes)
        })
        // Inflate the layout for this fragment
        return view
    }


}
