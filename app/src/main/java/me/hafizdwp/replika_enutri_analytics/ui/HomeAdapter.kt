package me.hafizdwp.replika_enutri_analytics.ui

import android.view.View
import kotlinx.android.synthetic.main.home_item.view.*
import me.hafizdwp.replika_enutri_analytics.R
import me.hafizdwp.replika_enutri_analytics.base.BaseRecyclerAdapter

/**
 * @author hafizdwp
 * 11/02/2020
 **/
class HomeAdapter(val listener: HomeListener) : BaseRecyclerAdapter<HomeItem>() {

    override val bindItemLayoutRes: Int?
        get() = R.layout.home_item
    override val mListItem: List<HomeItem>
        get() = HomeItem.getList()

    override fun onBind(itemView: View, model: HomeItem) {
        itemView.apply {
            imgMenu.setImageResource(model.resId)
            textMenuName.text = model.title
            rootView.setOnClickListener {
                listener.onMenuClick(model)
            }
        }
    }
}