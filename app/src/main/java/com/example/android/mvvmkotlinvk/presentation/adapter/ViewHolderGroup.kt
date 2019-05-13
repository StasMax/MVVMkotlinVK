package com.example.android.mvvmkotlinvk.presentation.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.android.mvvmkotlinvk.R
import com.example.android.mvvmkotlinvk.data.model.ModelGroup
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.item_view_recycler.view.*

class ViewHolderGroup(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var civAvatar: CircleImageView = itemView.findViewById(R.id.groupsSivAvatar)

    fun bind(groupModel: ModelGroup) {
        itemView.groupTxtName.text = groupModel.name
        itemView.groupTxtSubscribers.text = groupModel.subscribers
        itemView.isFavoriteCheckBox.isChecked = groupModel.isFavorite
        groupModel.avatar?.let { Picasso.with(itemView.context).load(it).into(civAvatar) }
    }
}