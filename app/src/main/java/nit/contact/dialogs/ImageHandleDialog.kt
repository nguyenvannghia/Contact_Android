package nit.contact.dialogs

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.view.*
import android.widget.ImageButton
import nit.contact.R

/**
 * Created by NIT Admin on 12/03/2016
 */
class ImageHandleDialog : DialogFragment {
    private var mUrlImageView: String? = null
    private var imgBtnBack: ImageButton? = null

    constructor(urlImageView: String) {
        this.mUrlImageView = urlImageView
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater?.inflate(R.layout.image_handle_dialog, container, false)
        imgBtnBack = view?.findViewById(R.id.imgBtnBack) as ImageButton?
        imgBtnBack?.setOnClickListener { dismiss() }
        return view
    }

    override fun onStart() {
        super.onStart()
        dialog.window.setWindowAnimations(R.style.slider_horizontal)
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var dialog: Dialog = super.onCreateDialog(savedInstanceState)
        dialog.window.requestFeature(Window.FEATURE_NO_TITLE)
        return dialog;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Light_NoTitleBar);
    }

    companion object {
        fun showDialog(fragmentManager: FragmentManager, imageUrl: String) {
            var imageHandleDialog = ImageHandleDialog(imageUrl)
            imageHandleDialog.show(fragmentManager, "")
        }
    }

}
