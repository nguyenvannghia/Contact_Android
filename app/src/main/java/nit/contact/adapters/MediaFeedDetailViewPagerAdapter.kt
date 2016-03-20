package nit.contact.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import nit.contact.views.CustomImageViewFeedDetail
import java.util.*

/**
 * Created by NIT Admin on 07/03/2016
 */
class MediaFeedDetailViewPagerAdapter(fm: FragmentManager, listPicture: ArrayList<String>?) : FragmentPagerAdapter(fm) {
    var listPicture: ArrayList<String>? = null

    init {
        this.listPicture = listPicture
    }

    override fun getItem(position: Int): Fragment? {
        return CustomImageViewFeedDetail()
    }

    override fun getCount(): Int {
        return 10
    }
}
