package nit.contact.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import nit.contact.R

/**
 * Created by NIT Admin on 07/03/2016
 */
class CustomImageViewFeedDetail: Fragment(){
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater?.inflate(R.layout.custom_image_for_feeddetail, container, false)
        return view
    }
}
