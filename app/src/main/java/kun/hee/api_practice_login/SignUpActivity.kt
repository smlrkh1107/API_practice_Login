package kun.hee.api_practice_login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sign_up.*
import kun.hee.api_practice_login.utils.ServerUtil
import org.json.JSONObject

class SignUpActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        setValues()
        setupEvents()
    }


    override fun setupEvents() {

        signUpBtn.setOnClickListener {
            val inputId = idEdt.text.toString()
            val inputPw = pwEdt.text.toString()
            val inputName = nameEdt.text.toString()
            val inputPhoneNum = phoneNumEdt.text.toString()

            ServerUtil.putRequestSignUp(mContext, inputId, inputPw, inputName, inputPhoneNum, object:ServerUtil.JsonResponseHandler{
                override fun onResponse(json: JSONObject) {
                    Log.d("회원가입 응답",json.toString()) // 임시로 서버 응답 확인하기 위한 코드

                    val code = json.getInt("code")

                    if (code==201){
                        runOnUiThread {
                            Toast.makeText(mContext, "회원가입 성공!", Toast.LENGTH_SHORT).show()
                            finish()
                        }
                        val data = json.getJSONObject("data")
                        val user = data.getJSONObject("user")
                        val userId = user.getString("user_id")
                        val name = user.getString("name")


                        val myIntent = Intent(mContext, SignUpActivity::class.java)
                        myIntent.putExtra("userName", name)
                        startActivity(myIntent)
                    }

                    else {
                        val message = json.getString("message")
                        runOnUiThread {
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                        }
                    }

                }

            })








        }

    }

    override fun setValues() {

    }


}
