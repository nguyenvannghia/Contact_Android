package nit.contact.views

import android.content.Context
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageButton
import kotlinx.android.synthetic.main.custom_header_layout.view.*
import nit.contact.R

/**
 * Created by NIT Admin on 07/03/2016
 */
class HeaderLayout : FrameLayout {
    private var mBtnLeft: ImageButton? = null
    private var mBtnRight: ImageButton? = null
    private var mTextViewNameHeader: TextViewComingSoon? = null

    private var mOnClickItemHeaderListener: OnClickItemHeaderListener? = null
    var isChildPage: Boolean = false;

    constructor(context: Context) : super(context) {
        var view = LayoutInflater.from(context).inflate(R.layout.custom_header_layout, this, false)
        addView(view)
        initViews(view)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs, 0) {
        var view = LayoutInflater.from(context).inflate(R.layout.custom_header_layout, this, false)
        addView(view)
        initViews(view)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        var view = LayoutInflater.from(context).inflate(R.layout.custom_header_layout, this, false)
        addView(view)
        initViews(view)
    }


    fun initViews(view: View) {
        mBtnLeft = view.btnLeft
        mBtnRight = view.btnRight
        mTextViewNameHeader = view.customTextViewNameHeader

        mBtnLeft?.setOnClickListener {
            if (isChildPage) {
                mOnClickItemHeaderListener?.onClickBackFromChildPage()
            } else {

            }
        }

        mBtnRight?.setOnClickListener { mOnClickItemHeaderListener?.onClickShowMenu() }
    }

    fun setTitle(title: String?) {
        if (title != null) {
            mTextViewNameHeader?.text = title;
            mTextViewNameHeader?.visibility = View.VISIBLE
        } else {
            mTextViewNameHeader?.visibility = View.GONE
        }
    }

    fun isShowButtonRight(isShow: Boolean) {
        if (isShow) mBtnRight?.visibility = View.VISIBLE
        else mBtnRight?.visibility = View.GONE
    }

    fun isShowButtonLeft(isShow: Boolean) {
        if (isShow) mBtnLeft?.visibility = View.VISIBLE
        else mBtnLeft?.visibility = View.GONE
    }

    fun getViewTitle(): TextViewComingSoon? {
        return mTextViewNameHeader;
    }


    fun setIconLeft(drawable: Int) {
        mBtnLeft?.setImageDrawable(ContextCompat.getDrawable(context, drawable))
    }

    fun setIconRight(drawable: Int) {
        mBtnRight?.setImageDrawable(ContextCompat.getDrawable(context, drawable))
    }

    fun setCallback(onClickItemHeaderListener: OnClickItemHeaderListener) {
        this.mOnClickItemHeaderListener = onClickItemHeaderListener
    }


    interface OnClickItemHeaderListener {
        fun onClickBackFromChildPage()
        fun onClickShowMenu()
    }


}
