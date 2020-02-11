package me.hafizdwp.replika_enutri_analytics.ui

import androidx.annotation.DrawableRes
import me.hafizdwp.replika_enutri_analytics.R

/**
 * @author hafizdwp
 * 11/02/2020
 **/
data class HomeItem(
        var title: String,
        @DrawableRes
        var resId: Int
) {
    companion object {
        fun getList(): ArrayList<HomeItem> {
            val array = arrayListOf<HomeItem>()
            array.apply {
                add(HomeItem("Forum", R.drawable._ic_forum))
                add(HomeItem("Tanya Ahli", R.drawable._ic_tanya_ahli))
                add(HomeItem("Edukasi", R.drawable._ic_education))
                add(HomeItem("Alat Bantu", R.drawable._ic_alat_bantu))
                add(HomeItem("E-Learning", R.drawable._ic_e_learning))
                add(HomeItem("BGM", R.drawable._ic_bgm))
                add(HomeItem("Survey", R.drawable._ic_survey))
            }
            return array
        }
    }
}