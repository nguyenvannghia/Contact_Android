package nit.contact.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import nit.contact.ui.fragments.PictureAndCommentDetail

/**
 * Created by NIT Admin on 08/03/2016
 */
class SwipeFeedsListViewPagerAdapter(fm: FragmentManager, onViewPagerHorListener: PictureAndCommentDetail.OnViewPagerHorListener) : FragmentPagerAdapter(fm) {
    private var mOnViewPagerHorListener: PictureAndCommentDetail.OnViewPagerHorListener? = null

    init {
        this.mOnViewPagerHorListener = onViewPagerHorListener
    }

    override fun getItem(position: Int): Fragment {
        return PictureAndCommentDetail(mOnViewPagerHorListener!!)
    }

    override fun getCount(): Int {
        return 100
    }
}
