package nit.contact.ui.activities

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.TranslateAnimation
import android.widget.EditText
import io.socket.client.IO
import io.socket.client.Socket
import kotlinx.android.synthetic.main.activity_splash.*
import nit.contact.R
import nit.contact.common.GlobalParams
import nit.contact.common.hiddenKeyboard

/**
 * Created by NIT Admin on 04/03/2016
 */

class SplashActivity : AppCompatActivity() {
    var mSocket: Socket = IO.socket("http://nitcontact-nghiapfiev.rhcloud.com/")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        init()
        setListening()
        startView()

    }

    private fun init() {
        //        mSocket.connect();
        //        mSocket.on("register", object : Emitter.Listener {
        //            override fun call(vararg p0: Any?) {
        //                runOnUiThread {
        //                    animationHideViewloginSignup()
        //                }
        //
        //                println("register return = " + p0[0])
        //            }
        //
        //        });

        gifLoadingSplashActivity.setMovieResource(R.drawable.loading)
        atvEmailSplashActivity.setText(GlobalParams.EMAIL_TEST_LOGIN)
        edtPasswordSplashActivity.setText(GlobalParams.PASSWORD_TEST_LOGIN)

    }

    private fun startView() {

        val animLogo = AnimationUtils.loadAnimation(this@SplashActivity,
                R.anim.anim_translate_logo_on_splash_screen)
                as TranslateAnimation
        animLogo.fillAfter = true
        animLogo.duration = 900

        animLogo.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
            }

            override fun onAnimationEnd(animation: Animation) {
                gifLoadingSplashActivity.visibility = View.VISIBLE
                object : CountDownTimer(1000, 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                    }

                    override fun onFinish() {
                        animationShowViewLoginSignUp()

                    }
                }.start()
            }

            override fun onAnimationRepeat(animation: Animation) {
            }
        })

        imvLogoSplashActivity.visibility = View.VISIBLE
        imvLogoSplashActivity.startAnimation(animLogo)
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.anim_in_right, R.anim.anim_out_left)
        finish()
    }

    fun setListening() {
        tvLoginFacebookSplashActivity.setOnClickListener(loginWith(GlobalParams.FACEBOOK))
        tvLoginGoogleSplashActivity.setOnClickListener(loginWith(GlobalParams.GOOGLE_PLUS))
        tvLoginTwitterSplashActivity.setOnClickListener(loginWith(GlobalParams.TWITTER))
        tvEvenSplashActivity.setOnClickListener(getEvenAndHandle())
        tvSwitchEvenSplashActivity.setOnClickListener {
            edtPasswordSplashActivity.setText(getString(R.string.empty))
            edtDisplaynameSplashActivity.setText(getString(R.string.empty))

            val strEvenHandle = tvEvenSplashActivity.text.toString().trim()
            val strEvenPrepared = tvSwitchEvenSplashActivity.text.toString().trim()

            tvEvenSplashActivity.text = strEvenPrepared
            tvSwitchEvenSplashActivity.text = strEvenHandle

            if (strEvenHandle.trim().equals(getString(R.string.Sign_up))) {
                edtDisplaynameSplashActivity.visibility = View.GONE
            } else {
                edtDisplaynameSplashActivity.visibility = View.VISIBLE
            }
        }
    }

    private fun getEvenAndHandle(): View.OnClickListener? {
        var listen = View.OnClickListener {
            if (tvEvenSplashActivity.text.toString().trim().equals(getString(R.string.Login))) {
                loginAccount()
            } else {
                signup()
            }
        }
        return listen
    }

    private fun signup() {
        animationHideViewLoginSignUp()
        mSocket.emit("register", "nghia", "password 234", "mailtest");
    }


    fun loginWith(loginType: String): View.OnClickListener {
        val listen = View.OnClickListener {
            if (loginType == GlobalParams.FACEBOOK) loginFacebook()
            if (loginType == GlobalParams.GOOGLE_PLUS) loginGoogle()
            if (loginType == GlobalParams.TWITTER) loginTwitter()
            if (loginType == GlobalParams.ACCOUNT) loginAccount()
        }
        return listen;
    }

    fun animationHideViewLoginSignUp() {
        val animationFromRight = AnimationUtils.loadAnimation(this@SplashActivity, R.anim.anim_out_left) as TranslateAnimation
        animationFromRight.duration = 400

        vLoginSignupSplashActivity.visibility = View.VISIBLE

        animationFromRight.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(p0: Animation?) {
            }

            override fun onAnimationEnd(p0: Animation?) {
                vLoginSignupSplashActivity.visibility = View.GONE;
                gifLoadingSplashActivity.visibility = View.VISIBLE
            }

            override fun onAnimationStart(p0: Animation?) {
            }

        })
        vLoginSignupSplashActivity.startAnimation(animationFromRight)

    }

    private fun animationShowViewLoginSignUp() {
        val animationFromRight = AnimationUtils.loadAnimation(this@SplashActivity, R.anim.anim_in_right) as TranslateAnimation
        animationFromRight.duration = 400
        animationFromRight.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
            }

            override fun onAnimationStart(p0: Animation?) {
                gifLoadingSplashActivity.visibility = View.GONE
            }

        })

        vLoginSignupSplashActivity.visibility = View.VISIBLE
        vLoginSignupSplashActivity.startAnimation(animationFromRight)
    }

    fun loginAccount() {
        startMainActivity()
    }

    private fun loginTwitter() {
        startMainActivity()
    }

    private fun loginGoogle() {
        startMainActivity()
    }

    private fun loginFacebook() {
        startMainActivity()
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        val v = currentFocus
        if (v != null &&
                (ev?.action == MotionEvent.ACTION_UP || ev!!.action == MotionEvent.ACTION_MOVE) &&
                v is EditText &&
                !v.javaClass.name.startsWith("android.webkit.")) {
            val params = IntArray(2)
            v.getLocationOnScreen(params)
            val x = ev!!.rawX + v.left - params[0]
            val y = ev.rawY + v.top - params[1]

            if (x < v.left || x > v.right || y < v.top || y > v.bottom)
                hiddenKeyboard(this)
        }
        return super.dispatchTouchEvent(ev)
    }
}