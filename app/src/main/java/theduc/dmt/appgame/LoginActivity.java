package theduc.dmt.appgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class LoginActivity extends AppCompatActivity {
    EditText edtUser, edtPass;
    Button btLogin, btDelete;
    CheckBox cbSave;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUser = findViewById(R.id.edt_username);
        edtPass = findViewById(R.id.edt_password);
        btLogin = findViewById(R.id.bt_login);
        btDelete = findViewById(R.id.bt_delete);
        cbSave = findViewById(R.id.cb_save);

        sharedPreferences = getSharedPreferences("PREF_LOGIN",MODE_PRIVATE);

        // Đổ dữ liệu đã lưu từ PREF vào các edt, cb
        edtUser.setText(sharedPreferences.getString("username",""));
        edtPass.setText(sharedPreferences.getString("password",""));
        cbSave.setChecked(sharedPreferences.getBoolean("checked",false));

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = edtUser.getText().toString().trim();
                String pass = edtPass.getText().toString().trim();

                if (user.equals("admin") && (pass.equals("admin"))) {

                    Toast.makeText(LoginActivity.this
                             , "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    Util.TAG = 1;

                    // Nếu người dùng chọn lưu
                    if (cbSave.isChecked()) {

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username",user);
                        editor.putString("password",pass);
                        editor.putBoolean("checked",true);
                        editor.commit();

                    } else {

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove("username");
                        editor.remove("password");
                        editor.remove("checked");
                        editor.commit();

                    }

                    CountDownTimer countDownTimer
                            = new CountDownTimer(1000,100) {
                        @Override
                        public void onTick(long l) {

                        }

                        @Override
                        public void onFinish() {

                            finish();
                            overridePendingTransition( R.anim.anim_enter, R.anim.anim_exit );

                        }
                    }.start();
                } else {
                    Toast.makeText(LoginActivity.this, "Lỗi đăng nhập", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtUser.setText("");
                edtPass.setText("");
            }
        });

    }
}