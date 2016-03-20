package nit.contact.dialogs

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.view.*
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import nit.contact.R
import nit.contact.common.DialogCallBack


/**
 * Created by NIT Admin on 12/2/2015
 */
class BottomDialog(textsListInButton: Array<String>, dialogCallBack: DialogCallBack<String>) : DialogFragment() {


    var cancel: Button? = null
    var textsListInButton: Array<String>? = null
    var rootButtons: LinearLayout? = null
    var dialogCallBack: DialogCallBack<String>? = null

    init {
        this@BottomDialog.textsListInButton = textsListInButton
        this@BottomDialog.dialogCallBack = dialogCallBack
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater!!.inflate(R.layout.custom_dialog_bottom, container, false)
        createView()
        setListening()

        return view
    }

    private fun setListening() {
        cancel!!.setOnClickListener(onDismiss)
    }

    fun createView() {
        if (textsListInButton != null && textsListInButton!!.size > 0) {

            for (index in textsListInButton!!.indices) {
                val itemButtonText = textsListInButton!![index]
                val inflater = LayoutInflater.from(context)
                val viewButton = inflater.inflate(R.layout.itemlist_custom, null, true)

                val button: TextView

                if (textsListInButton!!.size == 1) {
                    button = viewButton.findViewById(R.id.buttonOnly) as TextView
                } else if (index == 0) {
                    button = viewButton.findViewById(R.id.buttonFirst) as TextView

                } else if (index == textsListInButton!!.size - 1) {
                    button = viewButton.findViewById(R.id.buttonLast) as TextView
                } else {
                    button = viewButton.findViewById(R.id.button) as TextView
                }

                button.visibility = View.VISIBLE

                button.text = itemButtonText
                button.setOnClickListener(evenForThisButton(itemButtonText.trim { it <= ' ' }))

                val param = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f)
                viewButton.layoutParams = param

                rootButtons!!.addView(viewButton)
            }
        } else {
            rootButtons!!.visibility = View.GONE
        }
    }


    private val onDismiss = View.OnClickListener { dismiss() }

    override fun onStart() {
        super.onStart()
        dialog.window.attributes.windowAnimations = R.style.anim_bottom_bottom
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var dialog: Dialog = super.onCreateDialog(savedInstanceState)

        dialog.window.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)

        return dialog;
    }

    private fun evenForThisButton(even: String): View.OnClickListener {
        val evenForThisButton = View.OnClickListener {
            dialogCallBack?.evenDialogListener(even)
            dismiss()
        }

        return evenForThisButton

    }

    companion object {
        fun showDialog(fragmentManager: FragmentManager, textsListInButton: Array<String>, dialogCallBack: DialogCallBack<String>) {
            val dialogBottom = BottomDialog(textsListInButton, dialogCallBack)
            dialogBottom.show(fragmentManager, "")
        }
    }


}
