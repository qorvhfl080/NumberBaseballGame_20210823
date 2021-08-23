package com.neppplus.numberbaseballgame_20210823

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
        Log.d("debug", "userInputNumArr = $userInputNumArr")

        var strikeCount = 0
        var ballCount = 0

        for (i in 0..2) {

            for (j in 0..2) {

                if (userInputNumArr[i] == mQuestionNumbers[j]) {
//                    같은 숫자

                    if (i == j) {
//                        위치도 같은 위치
                        strikeCount++
                        Log.d("debug", "i=$i, j=$j 스트라이크 = $strikeCount")
                    } else {
                        ballCount++
                        Log.d("debug", "i=$i, j=$j 볼 = $ballCount")
                    }
                }
            }
        }

        messageList.add( Message("${strikeCount}S ${ballCount}B 입니다.", "CPU"))
        Log.d("debug", "total 스트라이크 = $strikeCount, total 볼 = $ballCount")

        messageAdapter.notifyDataSetChanged()

        messageListView.smoothScrollToPosition(messageList.size - 1)

//        만약 3S라면 -> 축하 메세지 -> 입력 못하게 막기
        if (strikeCount == 3) {
            messageList.add( Message("축하합니다. 정답입니다.", "CPU"))

            messageAdapter.notifyDataSetChanged()

            messageListView.smoothScrollToPosition(messageList.size - 1)

            numberEdt.isEnabled = false
            okBtn.isEnabled = false

            Toast.makeText(this, "게임을 종료합니다.", Toast.LENGTH_SHORT).show()
        }

    }

    fun makeQuestionNumbers() {

//        랜덤한 3개 숫자를 문제로
//        1~9 숫자만, 중복 숫자 X
        for (i in 0..2) {
            while (true) {

//                1~9 사이의 랜덤 정수 추출
                val randomNum = (Math.random() * 9 + 1).toInt()
                Log.d("debug", "randomNum = ${randomNum}")

                var isDupliOk = true

//                mQuestionNumbers에 이미 들어있는지 검사
                for (num in mQuestionNumbers) {
                    if (num == randomNum) {
//                        중복된 숫자 발견
                        isDupliOk = false
                        Log.d("debug", "중복입니다")
                    }
                }

//                중복검사를 통과한 상태로 유지
                if (isDupliOk == true) {
                    mQuestionNumbers.add(randomNum)
                    Log.d("debug", "mQuestionNumbers = ${mQuestionNumbers}")
//                    무한 반복 탈출
                    break
                }

            }

        }

        for (num in mQuestionNumbers) {
            Log.d("debug", num.toString())
        }

//        환영 메세지를 채팅창에 띄우기
//        메세지 리스트에 띄워줄 메세지 데이터 추가
        messageList.add(Message("안녕하세요", "CPU"))
        messageList.add(Message("숫자야구 게임입니다.", "CPU"))
        messageList.add(Message("세자리 숫자를 맞춰주세요", "CPU"))

    }

}



