package nit.contact.ui.activities

import android.os.Bundle
import android.os.PersistableBundle
import nit.contact.R
import nit.contact.ui.BaseActivity

/**
 * Created by nghianv on 20/03/2016
 */

class ProfileActivity: BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_profile)
    }

}
