package me.hafizdwp.replika_enutri_analytics.ui

import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.home_fragment.*
import me.hafizdwp.replika_enutri_analytics.MainActivity
import me.hafizdwp.replika_enutri_analytics.R
import me.hafizdwp.replika_enutri_analytics.base.BaseFragment
import me.hafizdwp.replika_enutri_analytics.base.BasePagerAdapter
import me.hafizdwp.replika_enutri_analytics.toastSpammable

/**
 * @author hafizdwp
 * 11/02/2020
 **/
class HomeFragment : BaseFragment<MainActivity>(), HomeListener {

    override val bindLayoutRes: Int
        get() = R.layout.home_fragment

    lateinit var mAdapter: HomeAdapter
    lateinit var mPagerAdapter: BasePagerAdapter


    override fun onReady() {
        mAdapter = HomeAdapter(this)

        recyclerHome.apply {
            adapter = mAdapter
            layoutManager = GridLayoutManager(mContext, 4)
            itemAnimator = DefaultItemAnimator()
        }

        mPagerAdapter = BasePagerAdapter(childFragmentManager)
        repeat(3) {
            mPagerAdapter.addFragment(HomeBanner())
        }
        vpSlider.adapter = mPagerAdapter
        tabSlider.setupWithViewPager(vpSlider, true)
    }

    override fun onMenuClick(data: HomeItem) {
        toastSpammable(data.title)
    }
}