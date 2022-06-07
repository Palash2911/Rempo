package com.godspeed.propmart.ui.Bookmarks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.godspeed.propmart.Adapters.BookmarkViewPagerAdapter
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

        val adapter = BookmarkViewPagerAdapter(requireContext(), requireActivity().supportFragmentManager, _binding!!.tabLayout.tabCount)
        _binding!!.bookmarkvp.adapter = adapter

        _binding!!.bookmarkvp.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(_binding!!.tabLayout))

        _binding!!.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener
        {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                _binding!!.bookmarkvp.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}