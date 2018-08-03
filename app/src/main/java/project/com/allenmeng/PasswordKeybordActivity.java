package project.com.allenmeng;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import project.com.password_keyboard.linstener.OnCloseClickListener;
import project.com.password_keyboard.linstener.OnForgetPasswordListener;
import project.com.password_keyboard.linstener.OnPassWordInputListener;
import project.com.password_keyboard.view.PasswordKeyboardView;

/**
 * Created by
 */
public class PasswordKeybordActivity extends AppCompatActivity  {

//    private EditText textAmount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_keybord);

//        textAmount = findViewById(R.id.et);
        PasswordKeyboardView keyboardView = findViewById(R.id.keyboardView);
        //if you dont want to use custom attributes :
//        keyboardView.setPwdLength(6);
//        keyboardView.setShuffle(true);

        keyboardView.setOnCloseClickListener(new OnCloseClickListener() {
            @Override
            public void onCloseClick() {
                finish();
            }
        });

        keyboardView.setmOnPassWordInputListener(new OnPassWordInputListener() {
            @Override
            public void onChange(String currentPwd) {
                Log.e("TAG", "当前输入的密码是===="+currentPwd);
            }

            @Override
            public void onFinish(String pwd) {
                Log.e("TAG", "输入的密码是===="+pwd);
            }
        });
        
        keyboardView.setmOnForgetPasswordListener(new OnForgetPasswordListener() {
            @Override
            public void onForgetPwd() {
                Toast.makeText(PasswordKeybordActivity.this, "忘记密码", Toast.LENGTH_SHORT).show();
            }
        });

//        if (android.os.Build.VERSION.SDK_INT <= 10) {
//            textAmount.setInputType(InputType.TYPE_NULL);
//        } else {
//            this.getWindow().setSoftInputMode(
//                    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
//            try {
//                Class<EditText> cls = EditText.class;
//                Method setShowSoftInputOnFocus;
//                setShowSoftInputOnFocus = cls.getMethod("setShowSoftInputOnFocus",
//                        boolean.class);
//                setShowSoftInputOnFocus.setAccessible(true);
//                setShowSoftInputOnFocus.invoke(textAmount, false);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
    }
}
