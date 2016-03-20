package nit.contact.ui.fragments

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.contact_fragment.*
import nit.contact.R
import nit.contact.adapters.ContactsRecyclersViewAdapters
import nit.contact.ui.BaseFragment
import nit.contact.views.EditTextComingSoon

/**
 * Created by NIT Admin on 07/03/2016
 */
class ContactFragment : BaseFragment() {

    private var mContactsRecyclersViewAdapters: ContactsRecyclersViewAdapters? = null

    companion object {
        fun newInstance(): ContactFragment {
            val args = Bundle()
            val fragment = ContactFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater!!.inflate(R.layout.contact_fragment, container, false)
        initViews(view)
        setListening(view)
        return view
    }

    private fun initViews(view: View?) {
        mContactsRecyclersViewAdapters = ContactsRecyclersViewAdapters(activity)
        (view?.findViewById(R.id.recyslerViewsContactFragment) as RecyclerView).layoutManager = LinearLayoutManager(activity)
        (view?.findViewById(R.id.recyslerViewsContactFragment) as RecyclerView).adapter = mContactsRecyclersViewAdapters
    }

    private fun setListening(view: View?) {
        view?.findViewById(R.id.imvBtnDeletedKeySearchContactFragment)?.setOnClickListener {
            customEditTextKeySearchContactFragment.setText("")
        }

        (view?.findViewById(R.id.customEditTextKeySearchContactFragment) as EditTextComingSoon).addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (customEditTextKeySearchContactFragment.text.length > 0) {
                    isShowButtonRemoveKeySearch(true)
                } else {
                    isShowButtonRemoveKeySearch(false)
                }
            }

        })


    }

    private fun isShowButtonRemoveKeySearch(isShow: Boolean) {
        if (isShow) {
            imvBtnDeletedKeySearchContactFragment.visibility = View.VISIBLE
        } else {
            imvBtnDeletedKeySearchContactFragment.visibility = View.GONE
        }
    }
}
