package chatcom.f25.myproject

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import chatcom.f25.myproject.databinding.ActivitySplashScreenBinding

class Splash_screen : AppCompatActivity()   {

    private var mContext: Context? = null
    private val REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 124
    private var mBinding: ActivitySplashScreenBinding ?= null
    /********************************* On Create DATA CALL ****************************************/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding=DataBindingUtil.setContentView(this,R.layout.activity_splash_screen)
        mContext=this
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS)

        } else {
            CallHome()
        }
    }
    /********************************* Call Home Activity ****************************************/
    private fun CallHome() {
        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity
            val intent = Intent(mContext, MainActivity::class.java)
            startActivity(intent)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            finish()
        }, 2000)
    }
    /********************************* Call Request Pesrmission  **********************************/
    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS -> {
                CallHome()
                return
            }
        }
    }


}
