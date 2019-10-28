package sherafatpour.entekhab.notify.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import sherafatpour.entekhab.notify.R



class ArchiveFragment : Fragment() {

companion object{

    fun newInstance() = ArchiveFragment()
}
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view=inflater.inflate(R.layout.fragment_archive, container, false)

        // Inflate the layout for this fragment
        return view
    }


}
