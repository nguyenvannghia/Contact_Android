package nit.contact.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import nit.contact.R

/**
 * Created by nghianv on 20/03/2016
 */
class NotifyRecyclerViewAdapter : RecyclerView.Adapter<NotifyRecyclerViewAdapter.DataObjectNotify> {
    private var mContext: Context? = null

    constructor(context: Context){
        this.mContext = context
    }

    override fun getItemCount(): Int {
        return 100
    }

    override fun onBindViewHolder(holder: DataObjectNotify?, position: Int) {

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DataObjectNotify? {
       var view = LayoutInflater.from(mContext).inflate(R.layout.item_recycler_views_notify, parent, false)

        return DataObjectNotify(view)
    }

    class DataObjectNotify : RecyclerView.ViewHolder {
        constructor(itemView: View) : super(itemView) {

        }
    }
}