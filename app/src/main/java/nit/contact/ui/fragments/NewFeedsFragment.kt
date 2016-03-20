package nit.contact.ui.fragments


import `in`.srain.cube.views.ptr.PtrDefaultHandler
import `in`.srain.cube.views.ptr.PtrFrameLayout
import `in`.srain.cube.views.ptr.PtrHandler
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.custom_refresh_layout.view.*
import kotlinx.android.synthetic.main.fragment_new_feeds.view.*
import nit.contact.R
import nit.contact.adapters.NewFeedsRecyclerViewAdapter
import nit.contact.dialogs.PictureHorDialog
import nit.contact.ui.BaseFragment
import nit.contact.views.MyCustomLayoutManager

/**
 * Created by NIT Admin on 07/03/2016
 */
class NewFeedsFragment : BaseFragment(), NewFeedsRecyclerViewAdapter.OnClickInItemNewFeed {


    private var mNewFeedsRecyclerViewAdapter: NewFeedsRecyclerViewAdapter? = null
    private var mOnNewFeedsListener: OnNewFeedsListener? = null

    companion object {
        fun newInstance(onNewFeedsListener: OnNewFeedsListener): NewFeedsFragment {
            val fragment = NewFeedsFragment()
            fragment.mOnNewFeedsListener = onNewFeedsListener
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater!!.inflate(R.layout.fragment_new_feeds, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {
        // create recyclerView
        mNewFeedsRecyclerViewAdapter = NewFeedsRecyclerViewAdapter(activity, this@NewFeedsFragment)
        view.recyclerViewNewFeedsFragment?.layoutManager = MyCustomLayoutManager(activity)
        view.recyclerViewNewFeedsFragment?.adapter = mNewFeedsRecyclerViewAdapter

        // create refresh header for recyclerView
        val viewHeaderRefresh = LayoutInflater.from(activity)
                .inflate(R.layout.custom_refresh_layout, view.ptrFrameNewFeedsFragment, false)

        viewHeaderRefresh.loading.setMovieResource(R.drawable.gif_loading_text)
        view.ptrFrameNewFeedsFragment.headerView = viewHeaderRefresh
        view.ptrFrameNewFeedsFragment.setPtrHandler(object : PtrHandler {
            override fun onRefreshBegin(frame: PtrFrameLayout) {
                frame.postDelayed({
                    view.ptrFrameNewFeedsFragment.refreshComplete()
                }, 1800)
            }

            override fun checkCanDoRefresh(frame: PtrFrameLayout, content: View, header: View): Boolean {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header)
            }
        })
    }

    override fun onClickCommentFeed(positionFeed: Int) {
        mOnNewFeedsListener?.startFeedDetail()
    }

    override fun onClickImageFeed(positionFeed: Int) {
        PictureHorDialog.showDialog(fragmentManager)
    }


    override fun onClickLikeFeed(positionFeed: Int) {
        throw UnsupportedOperationException()
    }

    interface OnNewFeedsListener {
        fun startFeedDetail();
    }
}
