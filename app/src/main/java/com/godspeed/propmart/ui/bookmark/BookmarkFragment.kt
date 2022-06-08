package com.godspeed.propmart.ui.Bookmarks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.godspeed.propmart.Adapters.BookmarkViewPagerAdapter
import com.godspeed.propmart.Fragments.Bidsfrag
import com.godspeed.propmart.Fragments.Savedfrag
import com.godspeed.propmart.R
import com.godspeed.propmart.databinding.FragmentBookmarkBinding
import com.google.android.material.tabs.TabLayout

class BookmarksFragment : Fragment() {

    private var _binding: FragmentBookmarkBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val BookmarksViewModel =
            ViewModelProvider(this).get(BookmarksViewModel::class.java)

        _binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        val root: View = binding.root

        addFragment(Savedfrag())

        _binding!!.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                setFragment(tab!!.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
        return root
    }

    private fun setFragment(position: Int) {
        when (position + 1) {
            1 -> {
                addFragment(Savedfrag())
            }
            2 -> {
                addFragment(Bidsfrag())
            }
        }
    }
    private fun addFragment(fragment: Fragment) {
        val manager: FragmentManager = requireActivity().supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.flayout, fragment)
        transaction.commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}