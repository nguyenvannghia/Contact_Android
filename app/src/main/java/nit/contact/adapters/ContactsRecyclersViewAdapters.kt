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
class ContactsRecyclersViewAdapters : RecyclerView.Adapter<ContactsRecyclersViewAdapters.HolderObject> {

    private var mContext: Context? = null

    constructor(context: Context) {
        this.mContext = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderObject? {
        var view = LayoutInflater.from(mContext)?.inflate(R.layout.item_recycler_views_contacts, parent, false)
        var holderObject = HolderObject(view!!)
        return holderObject
    }

    override fun onBindViewHolder(holder: HolderObject, position: Int) {

    }

    override fun getItemCount(): Int {
        return 100;
    }

    class HolderObject(itemView: View) : RecyclerView.ViewHolder(itemView)
}
