package nit.contact.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.itemlist_new_feed.view.*
import nit.contact.R

/**
 * Created by NIT Admin on 07/03/2016
 */
class NewFeedsRecyclerViewAdapter : RecyclerView.Adapter<NewFeedsRecyclerViewAdapter.DataHolderFeed> {
    private var mContext: Context? = null
    private var mOnClickInItemNewFeed: OnClickInItemNewFeed? = null

    constructor(context: Context, onClickInItemNewFeed: OnClickInItemNewFeed) {
        this.mContext = context
        this.mOnClickInItemNewFeed = onClickInItemNewFeed
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataHolderFeed? {
        var view = LayoutInflater.from(mContext).inflate(R.layout.itemlist_new_feed, parent, false)
        var dataHolderFeed = DataHolderFeed(view)
        return dataHolderFeed
    }

    override fun onBindViewHolder(holder: DataHolderFeed, position: Int) {
        holder.tvComment?.setOnClickListener { mOnClickInItemNewFeed?.onClickCommentFeed(position) }

        holder.imvPicFeed?.setOnClickListener {
            mOnClickInItemNewFeed?.onClickImageFeed(position)
        }
    }


    override fun getItemCount(): Int {
        return 100
    }

    class DataHolderFeed(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgAvatar: ImageView? = null
        var tvDisplayName: TextView? = null
        var tvPlace: TextView? = null
        var imvPicFeed: ImageView? = null
        var tvContentPost: TextView? = null
        var tvComment: TextView? = null

        init {
            imgAvatar = itemView.imgAvatar
            imvPicFeed = itemView.imvPicFeed
            tvDisplayName = itemView.tvDisplayname
            tvPlace = itemView.tvDisplayname
            tvContentPost = itemView.tvContentPost
            tvComment = itemView.tvComment
        }
    }

    interface OnClickInItemNewFeed {
        fun onClickCommentFeed(positionFeed: Int)
        fun onClickLikeFeed(positionFeed: Int)
        fun onClickImageFeed(positionFeed: Int)
    }
}
