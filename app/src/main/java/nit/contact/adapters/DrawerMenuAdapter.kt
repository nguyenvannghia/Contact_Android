package nit.contact.adapters

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import nit.contact.R
import nit.contact.objects.DrawerItemMenu
import nit.contact.views.TextViewComingSoon

/**
 * Created by nghianv on 20/03/2016
 */
class DrawerMenuAdapter : BaseAdapter {
    private var mContext: Context? = null
    private var mListMenus: List<DrawerItemMenu>? = null

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var view = convertView
        var holder: Holder

        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_drawer_menu, parent, false)

            holder = Holder()
            holder.imvIcon = view?.findViewById(R.id.imvIcon) as ImageView?
            holder.tvName = view?.findViewById(R.id.tvComingSoonName) as TextViewComingSoon?
            holder.tvNumNotify = view?.findViewById(R.id.tvComingSoonNumNotify) as TextViewComingSoon?

            view?.tag = holder
        } else {
            holder = view.tag as Holder
        }



        holder.imvIcon?.setImageDrawable(ContextCompat.getDrawable(mContext, getItem(position)?.icon!!))
        holder.tvName?.text = getItem(position)?.title

        if (getItem(position)?.numNotify == 0) {
            holder.tvNumNotify?.text = "0";
            holder.tvNumNotify?.visibility = View.GONE;

        } else {
            holder.tvNumNotify?.text = getItem(position)?.numNotify.toString()
            holder.tvNumNotify?.visibility = View.VISIBLE;
        }


        return view
    }

    override fun getItem(position: Int): DrawerItemMenu? {
        return mListMenus?.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return mListMenus?.size ?: 0
    }

    constructor(context: Context, listMenus: List<DrawerItemMenu>?) {
        this.mContext = context
        this.mListMenus = listMenus

    }

    class Holder {
        var imvIcon: ImageView? = null
        var tvName: TextViewComingSoon? = null
        var tvNumNotify: TextViewComingSoon? = null
    }
}