package nit.contact.dialogs


import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import nit.contact.R
import nit.contact.adapters.SwipeFeedsListViewPagerAdapter
import nit.contact.ui.fragments.PictureAndCommentDetail
import nit.contact.views.ViewPagerVertical

/**
 * Created by NIT Admin on 08/03/2016
 */
class PictureHorDialog : DialogFragment(), PictureAndCommentDetail.OnViewPagerHorListener {

    override fun enoughViewPager(isSwipe: Boolean) {
        mViewPagerVerticalPicAndCommentDetail?.isSwipe = isSwipe
    }

    private var mSwipeFeedsListViewPagerAdapter: SwipeFeedsListViewPagerAdapter? = null
    private var mViewPagerVerticalPicAndCommentDetail: ViewPagerVertical? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater?.inflate(R.layout.dialog_fragment_picture_comment_detail, container, false)
        dialog.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        initViews(view)
        return view
    }

    private fun initViews(view: View?) {
        mViewPagerVerticalPicAndCommentDetail = view?.findViewById(R.id.viewPagerHorPicAndCommentDetail) as ViewPagerVertical?
        mSwipeFeedsListViewPagerAdapter = SwipeFeedsListViewPagerAdapter(childFragmentManager, this@PictureHorDialog)
        mViewPagerVerticalPicAndCommentDetail?.adapter = mSwipeFeedsListViewPagerAdapter
    }


    companion object {
        fun showDialog(fragmentManager: FragmentManager) {
            PictureHorDialog().show(fragmentManager, "")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Light_NoTitleBar);


    }

    override fun onStart() {
        super.onStart()
        dialog.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        dialog.window.attributes.windowAnimations = R.style.DialogAnimation;
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var dialog = super.onCreateDialog(savedInstanceState)
        dialog.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        return dialog
    }

}