package nit.contact.adapters

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import nit.contact.R
import nit.contact.ui.fragments.ContactFragment
import nit.contact.ui.fragments.NotifyFragment
import nit.contact.ui.fragments.NewFeedsFragment
import nit.contact.views.PagerSlidingTabStrip
import nit.contact.views.TextViewComingSoon

/**
 * Created by NIT Admin on 07/03/2016
 */
class MainViewPagerAdapter : FragmentPagerAdapter, PagerSlidingTabStrip.IconTabProvider {

    private var mContext: Context? = null
    private var mOnNewFeedsListener: NewFeedsFragment.OnNewFeedsListener? = null
    private var mTvViewTitle: TextView? = null

    constructor(fm: FragmentManager, context: Context, tvViewTitle: TextView, onNewFeedsFragmentListener: NewFeedsFragment.OnNewFeedsListener) : super(fm) {
        this.mContext = context
        this.mOnNewFeedsListener = onNewFeedsFragmentListener
        this.mTvViewTitle = tvViewTitle

    }

    override fun getView(position: Int): View? {
        var view = LayoutInflater.from(mContext).inflate(R.layout.item_menu_strip_tab, null)

        when (position) {
            0 -> (view as TextViewComingSoon).text = mContext?.resources?.getString(R.string.item_menu_strip_tab_new_feeds)
            1 -> (view as TextViewComingSoon).text = mContext?.resources?.getString(R.string.item_menu_strip_tab_contacts)
            2 -> (view as TextViewComingSoon).text = mContext?.resources?.getString(R.string.item_menu_notifies)
        }

        return view
    }

    override fun onPageScrollState(position: Int) {
        when (position) {
            0 -> (mTvViewTitle as TextViewComingSoon).text = mContext?.resources?.getString(R.string.item_menu_strip_tab_new_feeds)
            1 -> (mTvViewTitle as TextViewComingSoon).text = mContext?.resources?.getString(R.string.item_menu_strip_tab_contacts)
            2 -> (mTvViewTitle as TextViewComingSoon).text = mContext?.resources?.getString(R.string.item_menu_notifies)
        }
    }

    override fun onTabClick(position: Int, isSameTab: Boolean) {
    }


    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return NewFeedsFragment.newInstance(mOnNewFeedsListener!!)
            1 -> return ContactFragment.newInstance()
            2 -> return NotifyFragment.newInstance()
        }
        return null
    }

    override fun getCount(): Int {
        return 3;
    }
}
