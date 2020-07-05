package me.hafizdwp.replika_enutri_analytics

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.hafizdwp.replika_enutri_analytics.ui.*
import me.hafizdwp.replika_enutri_analytics.ui.chat.ChatMenuFragment

class MainActivity : AppCompatActivity() {

//    val mFragmentMenuList = listOf<Fragment>(
//            HomeFragment(),
//            PustakaFragment(),
//            ToolsFragment(),
//            EpoinFragment()
//    )

    private val mFragmentMenuList = listOf<Fragment>(
        HomeFragment(),
        ChatMenuFragment(),
        EpoinFragment()
    )

    private var isDoubleBackPressed = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupBottomNav()
    }


    fun setupBottomNav() {
        bottomNav.setOnNavigationItemSelectedListener {
//            when (it.itemId) {
//                R.id.home -> selectFragment(mFragmentMenuList[0])
//                R.id.pustaka -> selectFragment(mFragmentMenuList[1])
//                R.id.alat_bantu -> selectFragment(mFragmentMenuList[2])
//                R.id.epoin -> selectFragment(mFragmentMenuList[3])
//            }

            when (it.itemId) {
                R.id.home -> selectFragment(mFragmentMenuList[0])
                R.id.chat -> selectFragment(mFragmentMenuList[1])
                R.id.epoin -> selectFragment(mFragmentMenuList[2])
            }

            return@setOnNavigationItemSelectedListener true
        }

        selectFragment(mFragmentMenuList[0])
    }

    private fun selectFragment(fragment: Fragment) {

        val tag = when (fragment) {
            is HomeFragment -> HomeFragment::class.java.simpleName
            is PustakaFragment -> PustakaFragment::class.java.simpleName
            is ToolsFragment -> ToolsFragment::class.java.simpleName
            is EpoinFragment -> EpoinFragment::class.java.simpleName
            is ChatMenuFragment -> ChatMenuFragment::class.java.simpleName
            else -> ""
        }

        supportFragmentManager.beginTransaction().apply {
            if (supportFragmentManager.findFragmentByTag(tag) == null) {
                add(R.id.frameLayout, fragment, tag)
            }

            show(fragment)

            mFragmentMenuList.forEach {
                it.takeIf { it != fragment }?.let { fragmentToHide ->
                    hide(fragmentToHide)
                }
            }
        }.commit()
    }

    override fun onBackPressed() {
        handleBackPressed()
    }

    private fun handleBackPressed() {
        when {
            bottomNav.selectedItemId != R.id.home -> bottomNav.selectedItemId = R.id.home
            bottomNav.selectedItemId == R.id.home -> checkIsDoublePressed()
            else -> checkIsDoublePressed()
        }
    }

    private fun checkIsDoublePressed() {
        if (isDoubleBackPressed) {
            super.onBackPressed()
        } else {
            isDoubleBackPressed = true
            toastSpammable("Click back twice to exit")
            GlobalScope.launch {
                delay(2000L)
                isDoubleBackPressed = false
            }
        }
    }
}
