package nit.contact.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import nit.contact.R
import nit.contact.adapters.NotifyRecyclerViewAdapter

/**
 * Created by NIT Admin on 07/03/2016
 */
class NotifyFragment : Fragment() {
    private var mNotifyRecyclerViewAdapter: NotifyRecyclerViewAdapter? = null

    companion object {
        fun newInstance(): NotifyFragment {

            val args = Bundle()

            val fragment = NotifyFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater!!.inflate(R.layout.fragmrnt_notifies, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View?) {
        mNotifyRecyclerViewAdapter = NotifyRecyclerViewAdapter(activity)
        (view as RecyclerView).layoutManager = LinearLayoutManager(activity)
        view.adapter = mNotifyRecyclerViewAdapter
    }
}
