package com.good.solscript.adapter

import android.net.Uri
import android.view.View
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.good.solscript.R
import com.good.solscript.data.ResponseRecommandSubData
import kotlinx.android.synthetic.main.recyclerview_recommand_item.view.*
import org.jetbrains.anko.imageURI

class CommandViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvTitle = itemView.tv_recommand_title
    private val tvSubTitle = itemView.tv_recommand_subtitle
    private val ivSubnail = itemView.iv_recommand

    fun bind(recommandData: ResponseRecommandSubData) {
        tvTitle.text = recommandData.title
        tvSubTitle.text = recommandData.subTitle
        val uri = Uri.parse(recommandData.image)
        ivSubnail.setImageURI(uri)

    }
}