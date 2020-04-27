package kun.hee.api_practice_login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.idEdt
import kotlinx.android.synthetic.main.activity_login.pwEdt
import kotlinx.android.synthetic.main.activity_login.signUpBtn
import kotlinx.android.synthetic.main.activity_sign_up.*
import kun.hee.api_practice_login.utils.ServerUtil
import org.json.JSONObject

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setValues()
        setupEvents()
    }

    override fun setupEvents() {
        signUpBtn.setOnClickListener {
            val myIntent = Intent(mContext, SignUpActivity::class.java)
            startActivity(myIntent)
        }




        loginBtn.setOnClickListener {
            val inputId = idEdt.text.toString() //아이디가 존재하지 않는다면 ?
            val inputPw = pwEdt.text.toString() //비밀번호가 틀리다면 ?
//        서버로 로그인 요청 => ServerUtil클래스 기능 활용
            ServerUtil.postRequestLogin(mContext, inputId, inputPw, object:ServerUtil.JsonResponseHandler{//인터페이스 달라고하면 object:ㅇ라ㅣㅏㅓ리멀
                override fun onResponse(json: JSONObject) {// 실제로 응답을 받은 것을 분석, 대응하는 코드를 여기다 적으래.
                Log.d("서버응답JSON",json.toString()) // 임시로 서버 응답 확인하기 위한 코드

                val code = json.getInt("code")
                if (code == 200){ // 로그인 성공
                    val data = json.getJSONObject("data") // 중괄호 전부
                    val user = data.getJSONObject("user")
                    val name = user.getString("name")


                    val myIntent = Intent(mContext, MainActivity::class.java)
                    myIntent.putExtra("userName", name) // "userName"이거 Main에서 틀리면 앱 죽는다!!★
                    startActivity(myIntent)
                }
                else {
                    val message = json.getString("message")
                    runOnUiThread {//안에넣고돌려야해
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                    }
//                    Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show() // 비번틀리면,, 앱이터져버려
//                [오류] java.lang.RuntimeException: Can't toast on a thread that has not called Looper.prepare()
                }

            }


            })

        }

    }

    override fun setValues() {

        }

}
