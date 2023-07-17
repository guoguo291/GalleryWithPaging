package com.example.gallerywithpagingdone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController=findNavController(R.id.fragment)
        NavigationUI.setupActionBarWithNavController(this,navController)


    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp() || navController.navigateUp()
    }

    private var mExitTime:Long=0
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if ("图趣"==navController.currentDestination?.label&&keyCode == KeyEvent.KEYCODE_BACK) {
            //与上次点击返回键时刻差
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                //大于2000ms则认为是误操作
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show()
                mExitTime = System.currentTimeMillis()
            } else {
                exitProcess(0)
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}
