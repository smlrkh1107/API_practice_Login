package kun.hee.api_practice_login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kun.hee.api_practice_login.utils.ServerUtil
import org.json.JSONObject

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setValues()
        setupEvents()
    }

    override fun setupEvents() {

        loginBtn.setOnClickListener {
            val inputId = idEdt.text.toString() //아이디가 존재하지 않는다면 ?
            val inputPw = pwEdt.text.toString() //비밀번호가 틀리다면 ?
//        서버로 로그인 요청 => ServerUtil클래스 기능 활용
            ServerUtil.postRequestLogin(mContext, inputId, inputPw, object:ServerUtil.JsonResponseHandler{//인터페이스 달라고하면 object:ㅇ라ㅣㅏㅓ리멀
                override fun onResponse(json: JSONObject) {// 실제로 응답을 받은 것을 분석, 대응하는 코드를 여기다 적으래.


                Log.d("서버응답JSON",json.toString()) // 임시로 서버 응답 확인하기 위한 코드

            }


            })

        }

    }

    override fun setValues() {

        }

}
