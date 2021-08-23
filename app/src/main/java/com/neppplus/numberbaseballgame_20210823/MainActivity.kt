package com.neppplus.numberbaseballgame_20210823

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.neppplus.numberbaseballgame_20210823.adapter.MessageAdapter
import com.neppplus.numberbaseballgame_20210823.data.Message
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val messageList = ArrayList<Message>()

    lateinit var messageAdapter: MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        messageList.add(Message("안녕하세요", "CPU"))
        messageList.add(Message("반갑습니다", "USER"))

        messageAdapter = MessageAdapter(this, R.layout.message_list_item, messageList)

        messageListView.adapter = messageAdapter

    }
}