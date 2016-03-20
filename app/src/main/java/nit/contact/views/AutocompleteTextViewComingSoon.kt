package nit.contact.views

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.AutoCompleteTextView

/**
 * Created by NIT Admin on 08/03/2016
 */
class AutocompleteTextViewComingSoon : AutoCompleteTextView {
    constructor(context: Context) : super(context) {
        this.typeface = Typeface.createFromAsset(context.assets, "fonts/ComingSoon.ttf")
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        this.typeface = Typeface.createFromAsset(context.assets, "fonts/ComingSoon.ttf")
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        this.typeface = Typeface.createFromAsset(context.assets, "fonts/ComingSoon.ttf")
    }
}
