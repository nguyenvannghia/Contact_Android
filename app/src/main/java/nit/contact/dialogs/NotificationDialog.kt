package nit.contact.dialogs

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.LinearLayout
import nit.contact.R
import nit.contact.views.TextViewComingSoon

/**
 * Created by NIt Admin on 04/03/2016
 */
class NotificationDialog : DialogFragment {
    var title: String? = null
    var message: String? = null
    var dialogNotifyListener: DialogNotifyListener? = null
    var tvMessage: TextViewComingSoon? = null
    var lnButtons: LinearLayout? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }


    constructor(dialogNotifyListener: DialogNotifyListener) {
        this.dialogNotifyListener = dialogNotifyListener;
    }

    companion object {
        fun showDialog(fragmentManager: FragmentManager, dialogNotifyListener: DialogNotifyListener) {
            val dialogNotification = NotificationDialog(dialogNotifyListener)
            dialogNotification.show(fragmentManager, "");
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.custom_dialog_notification, container, false)
        initViews(rootView)
        return rootView;
    }

    private fun initViews(view: View) {
        lnButtons = view.findViewById(R.id.lnButtons) as LinearLayout?
        tvMessage = view.findViewById(R.id.tvMessage) as TextViewComingSoon?
        //        var listButton = ArrayList<>()
        //
        //        for (index in listButton!!.indices) {
        //            var viewItemButton = LayoutInflater.from(activity).inflate(R.layout.item_button_custom_dialog, null, true);
        //            var tvItemButton: TextView = viewItemButton?.findViewById(R.id.tvItemButton) as CustomTextViewComingSoon;
        //            var viewLine = viewItemButton.findViewById(R.id.viewLine);
        //            tvItemButton.text = listButton.get(index);
        //
        //            var param: LinearLayout.LayoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f);
        //            viewItemButton.layoutParams = param;
        //
        //            lnButtons?.addView(viewItemButton);
        //
        //            if (listButton.size == 1) {
        //                viewLine.visibility = View.GONE;
        //                tvItemButton.setOnClickListener {
        //                    dialogNotifyListener?.onClickItem(listButton.get(index));
        //                    dismiss();
        //                };
        //
        //            } else {
        //                if (index == 0) {
        //                    viewLine.visibility = View.VISIBLE;
        //                    tvItemButton.setOnClickListener {
        //                        dialogNotifyListener?.onClickItem(listButton.get(index));
        //                        dismiss();
        //                    };
        //                } else {
        //                    viewLine.visibility = View.GONE;
        //                    tvItemButton.setOnClickListener(object : View.OnClickListener {
        //                        override fun onClick(p0: View?) {
        //                            dialogNotifyListener?.onClickItem(listButton.get(index));
        //                            dismiss();
        //                        }
        //                    });
        //                }
        //
        //            }
        //        }
    }

    interface DialogNotifyListener {
        fun onClickItem(btnClick: String)
    }

}
