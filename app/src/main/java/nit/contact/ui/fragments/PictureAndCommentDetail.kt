package nit.contact.ui.fragments

import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.daimajia.swipe.SwipeLayout
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener
import nit.contact.R
import nit.contact.adapters.CommentRecyclerViewAdapter
import nit.contact.views.CustomSwipeLayout
import nit.contact.views.EditTextComingSoon
import nit.contact.views.TextViewComingSoon

/**
 * Created by NIT Admin on 14/03/2016
 */
class PictureAndCommentDetail : Fragment {


    private var mOnViewPagerHorListener: OnViewPagerHorListener? = null
    private var commentRecyclerViewAdapter: CommentRecyclerViewAdapter? = null
    private var recyclerViewCommentFragmentComment: RecyclerView? = null
    private var swipePicAndCommentHorFeed: CustomSwipeLayout? = null
    private var imvSendSmile: ImageView? = null
    private var tvCommentFragmentPicture: TextViewComingSoon? = null
    private var messagenput: EditTextComingSoon? = null

    constructor(onViewPagerHorListener: OnViewPagerHorListener) {
        this.mOnViewPagerHorListener = onViewPagerHorListener
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater?.inflate(R.layout.item_fragment_picture_comment_detail, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View?) {
        recyclerViewCommentFragmentComment = view?.findViewById(R.id.recyclerViewCommentFragmentComment) as RecyclerView?
        swipePicAndCommentHorFeed = view?.findViewById(R.id.swipePicAndCommentHorFeed) as CustomSwipeLayout?
        imvSendSmile = view?.findViewById(R.id.imvSendSmile) as ImageView?
        messagenput = view?.findViewById(R.id.mesagenput) as EditTextComingSoon?
        tvCommentFragmentPicture = view?.findViewById(R.id.tvCommentFragmentPicture) as TextViewComingSoon?
        commentRecyclerViewAdapter = CommentRecyclerViewAdapter(activity)
        recyclerViewCommentFragmentComment?.layoutManager = LinearLayoutManager(activity)
        recyclerViewCommentFragmentComment?.adapter = commentRecyclerViewAdapter

        var swipeLayout: CustomSwipeLayout? = view?.findViewById(R.id.swipePicAndCommentHorFeed) as CustomSwipeLayout?
        swipeLayout?.setShowMode(CustomSwipeLayout.ShowMode.LayDown);

        //add drag edge.(If the BottomView has 'layout_gravity' attribute, this line is unnecessary)
        swipeLayout?.addDrag(CustomSwipeLayout.DragEdge.Left, view?.findViewById(R.id.bottomWrapper));
        swipeLayout?.addSwipeListener(object : CustomSwipeLayout.SwipeListener {
            override fun onStartOpen(layout: CustomSwipeLayout?) {

            }

            override fun onOpen(layout: CustomSwipeLayout?) {
                swipePicAndCommentHorFeed?.isSwipeEnabled = false
                mOnViewPagerHorListener?.enoughViewPager(false)
                Handler().postDelayed(Runnable {
                    swipePicAndCommentHorFeed?.isSwipeEnabled = true
                }, 300)
            }

            override fun onStartClose(layout: CustomSwipeLayout?) {
            }

            override fun onClose(layout: CustomSwipeLayout?) {
                mOnViewPagerHorListener?.enoughViewPager(true)
            }

            override fun onUpdate(layout: CustomSwipeLayout?, leftOffset: Int, topOffset: Int) {
            }

            override fun onHandRelease(layout: CustomSwipeLayout?, xVel: Float, yVel: Float) {
            }


        });

        tvCommentFragmentPicture?.setOnClickListener { swipePicAndCommentHorFeed?.open() }


        KeyboardVisibilityEvent.setEventListener(
                activity,
                KeyboardVisibilityEventListener { isOpen ->
                    if (isOpen) {
                        swipePicAndCommentHorFeed?.isSwipeEnabled = false
                    } else {
                        Handler().postDelayed({ swipePicAndCommentHorFeed?.isSwipeEnabled = true }, 500);

                    }
                });


    }


    interface OnViewPagerHorListener {
        fun enoughViewPager(isSwipe: Boolean)
    }
}
