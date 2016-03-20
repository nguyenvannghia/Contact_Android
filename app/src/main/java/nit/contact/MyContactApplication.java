package nit.contact;

import android.app.Application;
import android.support.multidex.MultiDex;

import com.orm.SugarContext;


/**
 * Created by NIT Admin on 01/03/2016
 */
public class MyContactApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        SugarContext.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }
}
