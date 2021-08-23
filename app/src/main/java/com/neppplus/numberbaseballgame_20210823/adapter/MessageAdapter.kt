package com.neppplus.numberbaseballgame_20210823.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
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

        val cpuMessageLayout = row.findViewById<LinearLayout>(R.id.cpuMessageLayout)
        val userMessageLayout = row.findViewById<LinearLayout>(R.id.userMessageLayout)
        val cpuMessageTxt = row.findViewById<TextView>(R.id.cpuMessageTxt)
        val userMessageTxt = row.findViewById<TextView>(R.id.userMessageTxt)

        if (data.writer == "CPU") {
            userMessageLayout.visibility = View.GONE

            cpuMessageTxt.text = data.content

        } else {
            cpuMessageLayout.visibility = View.GONE

            userMessageTxt.text = data.content
        }

        return row
    }

}