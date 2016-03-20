package nit.contact.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import nit.contact.R

/**
 * Created by NIT Admin on 13/03/2016
 */
class CommentRecyclerViewAdapter : RecyclerView.Adapter<CommentRecyclerViewAdapter.DataHolderObjectComment> {
    private var mContext: Context? = null

    constructor(context: Context) {
        this.mContext = context
    }

    override fun onCreateViewHolder(p0: ViewGroup?, p1: Int): DataHolderObjectComment? {
        var view = LayoutInflater.from(mContext).inflate(R.layout.itemlist_commnet, p0, false)
        var DataHolderObjectComment: DataHolderObjectComment = DataHolderObjectComment(view)
        return DataHolderObjectComment
    }

    override fun onBindViewHolder(p0: DataHolderObjectComment?, p1: Int) {
    }

    override fun getItemCount(): Int {
        return 100
    }

    class DataHolderObjectComment(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    }
}
