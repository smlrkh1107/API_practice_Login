package kun.hee.api_practice_login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun setupEvents() {

        loginBtn.setOnClickListener {
            val inputId = idEdt.text.toString() //아이디가 존재하지 않는다면 ?
            val inputPw = pwEdt.text.toString() //비밀번호가 틀리다면 ?
        }

    }

    override fun setValues() {

        }

}
