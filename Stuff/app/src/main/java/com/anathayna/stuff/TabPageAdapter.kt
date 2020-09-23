package com.anathayna.stuff
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabPageAdapter(f: FragmentActivity): FragmentStateAdapter(f) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            1 -> return AlbumsFragment.newInstance()
            2 -> return ArtistsFragment.newInstance()
            else -> return RecentsFragment.newInstance()
        }
    }

}