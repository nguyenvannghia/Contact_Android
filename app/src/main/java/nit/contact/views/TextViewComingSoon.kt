package nit.contact.views

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.TextView

/**
 * Created by NIT Admin on 08/03/2016
 */
class TextViewComingSoon : TextView {
    constructor(context: Context) : super(context) {
        initTypeFace(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initTypeFace(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initTypeFace(context)
    }

    private fun initTypeFace(context: Context) {
        this.typeface = Typeface.createFromAsset(context.assets, "fonts/ComingSoon.ttf")
    }
}
