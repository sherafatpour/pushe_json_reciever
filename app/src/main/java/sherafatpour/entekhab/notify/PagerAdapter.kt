package sherafatpour.entekhab.notify

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import sherafatpour.entekhab.notify.fragments.ArchiveFragment
import sherafatpour.entekhab.notify.fragments.UnreadFragment

class PageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        when(position){

            0->  {

                return UnreadFragment.newInstance()
            }

            1->{
                return ArchiveFragment.newInstance()


            }

        }
        return UnreadFragment.newInstance()
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return if (position==0){
            "خوانده نشده"

        }else{
            "بایگانی"

        }
    }
}