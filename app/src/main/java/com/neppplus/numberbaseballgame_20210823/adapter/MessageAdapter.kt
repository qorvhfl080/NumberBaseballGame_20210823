package com.neppplus.numberbaseballgame_20210823.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.neppplus.numberbaseballgame_20210823.R
import com.neppplus.numberbaseballgame_20210823.data.Message

class MessageAdapter(val mContext: Context, val resId: Int, val mList: ArrayList<Message>) : ArrayAdapter<Message>(mContext, resId, mList) {

    val mInflater = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var tempRow = convertView
        if (tempRow == null) {
            tempRow = mInflater.inflate(R.layout.message_list_item, null)
        }

        val row = tempRow!!

        val data = mList[position]

        return row
    }

}