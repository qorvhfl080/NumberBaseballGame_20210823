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

//    세자리 문제 숫자를 저장하기 위한 ArrayList
    val mQuestionNumbers = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        세자리 랜덤 문제 만들기
        makeQuestionNumbers()

        messageAdapter = MessageAdapter(this, R.layout.message_list_item, messageList)

        messageListView.adapter = messageAdapter

        okBtn.setOnClickListener {

            val inputNumStr = numberEdt.text.toString()

            val msg = Message(inputNumStr, "USER")

            messageList.add(msg)

            messageAdapter.notifyDataSetChanged()

//            numberEdt의 문구를 비워주고싶다
            numberEdt.setText("")

//            리스트뷰를 최하단으로 내리기
            messageListView.smoothScrollToPosition(messageList.size - 1)

//            컴퓨터가 ?S ?B인지 판단해서 메세지 추가 (답장)
            checkAnswer(inputNumStr.toInt())
        }

    }

    fun checkAnswer(inputNum: Int) {
//        사람이 입력한 숫자사 몇 스트라이크, 볼인지 판단

//        사람이 입력한 숫자를 각 자리별로 나눠서 목록에 대입
        val userInputNumArr = ArrayList<Int>()

        userInputNumArr.add(inputNum / 100)
        userInputNumArr.add(inputNum / 10 % 10)
        userInputNumArr.add(inputNum % 10)

        var strikeCount = 0
        var ballCount = 0

        for (i in 0..2) {

            for (j in 0..2) {
//                내가 입력한 숫자
                if (userInputNumArr[i] == mQuestionNumbers[j]) {
//                    같은 숫자
//                    위치도 같은 위치
                    if (i == j) {
                        strikeCount++
                    } else {
                        ballCount++
                    }
                }
            }
        }

        messageList.add( Message("${strikeCount}S ${ballCount}B 입니다.", "CPU"))

        messageAdapter.notifyDataSetChanged()

        messageListView.smoothScrollToPosition(messageList.size - 1)

    }

    fun makeQuestionNumbers() {

//        고정된 세개 숫자를 임시 문자로
        mQuestionNumbers.add(4)
        mQuestionNumbers.add(7)
        mQuestionNumbers.add(1)

//        환영 메세지를 채팅창에 띄우기
//        메세지 리스트에 띄워줄 메세지 데이터 추가
        messageList.add(Message("안녕하세요", "CPU"))
        messageList.add(Message("숫자야구 게임입니다.", "CPU"))
        messageList.add(Message("세자리 숫자를 맞춰주세요", "CPU"))

    }

}



