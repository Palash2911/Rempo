package com.godspeed.propmart.Adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.godspeed.propmart.Fragments.Bidsfrag
import com.godspeed.propmart.Fragments.Savedfrag
import com.godspeed.propmart.ui.Bookmarks.BookmarksFragment

class BookmarkViewPagerAdapter(private val myContext: Context, fm: FragmentManager, internal var totalTabs: Int) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return totalTabs
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> {
                Savedfrag()
            }
            1 -> {
                Bidsfrag()
            }
            else -> Savedfrag()
        }
    }

}