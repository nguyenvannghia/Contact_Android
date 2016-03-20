package nit.contact.ui.activities


import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import com.orhanobut.dialogplus.DialogPlus
import kotlinx.android.synthetic.main.activity_main.*
import nit.contact.R
import nit.contact.adapters.DrawerMenuAdapter
import nit.contact.adapters.MainViewPagerAdapter
import nit.contact.adapters.PlusDialogAdapter
import nit.contact.common.DialogCallBack
import nit.contact.common.hiddenKeyboard
import nit.contact.objects.DrawerItemMenu
import nit.contact.ui.BaseActivity
import nit.contact.ui.fragments.NewFeedsFragment
import nit.contact.views.HeaderLayout
import java.util.*

/**
 * Created by NIT Admin on 11/20/2015
 */


class MainActivity : BaseActivity(), DialogCallBack<String>, HeaderLayout.OnClickItemHeaderListener, NewFeedsFragment.OnNewFeedsListener, AdapterView.OnItemClickListener {

    private var mDrawerMenuAdapter: DrawerMenuAdapter? = null
    private var mMenus: ArrayList<DrawerItemMenu>? = null

    override fun onClickBackFromChildPage() {
    }


    var mainViewPagerAdapter: MainViewPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        headerLayoutMainActivity.setCallback(this@MainActivity)
        headerLayoutMainActivity.isShowButtonLeft(false)

        mainViewPagerAdapter = MainViewPagerAdapter(supportFragmentManager, this@MainActivity, headerLayoutMainActivity.getViewTitle()!!, this@MainActivity)
        viewPager.adapter = mainViewPagerAdapter;
        pagerSlidingTabsShopDetailActivity.setViewPager(viewPager)

        mMenus = createListMenu()
        mDrawerMenuAdapter = DrawerMenuAdapter(this, mMenus)
        listViewMenuDrawer.adapter = mDrawerMenuAdapter

        setListening()

        showDialogSwipe()
    }

    private fun setListening() {
        listViewMenuDrawer.setOnItemClickListener(this@MainActivity)
    }

    override fun onClickShowMenu() {
        if (mDrawerLayout.isDrawerVisible(Gravity.RIGHT)) {
            mDrawerLayout.closeDrawer(Gravity.RIGHT)
        } else {
            mDrawerLayout.openDrawer(Gravity.RIGHT)
        }
    }

    override fun evenDialogListener(even: String) {
    }

    override fun startFeedDetail() {
        var inTent: Intent = Intent(this@MainActivity, FeedDetailActivity::class.java)
        startActivity(inTent)
        overridePendingTransition(R.anim.anim_in_right, R.anim.anim_out_left);
    }

    private fun showDialogSwipe() {
        var dialog = DialogPlus.newDialog(this)
                .setAdapter(PlusDialogAdapter(this, true))
                .setOnItemClickListener { p0, p1, p2, p3 -> }
                .setExpanded(true)
                .create();
        dialog.show();

    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        val v = currentFocus
        if (v != null &&
                (ev?.action == MotionEvent.ACTION_UP || ev!!.action == MotionEvent.ACTION_MOVE) &&
                v is EditText &&
                !v.javaClass.name.startsWith("android.webkit.")) {
            val params = IntArray(2)
            v.getLocationOnScreen(params)
            val x = ev!!.rawX + v.left - params[0]
            val y = ev.rawY + v.top - params[1]

            if (x < v.left || x > v.right || y < v.top || y > v.bottom)
                hiddenKeyboard(this)
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun createListMenu(): ArrayList<DrawerItemMenu> {
        var listMenus = ArrayList<DrawerItemMenu>();

        var profile = DrawerItemMenu(getString(R.string.item_menu_drawer_profile), R.drawable.ic_setting, 0)
        var setting = DrawerItemMenu(getString(R.string.item_menu_drawer_setting), R.drawable.ic_setting, 0)

        listMenus.add(profile)
        listMenus.add(setting)
        return listMenus
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (position) {
            0 -> {

            }
        }
    }
}
