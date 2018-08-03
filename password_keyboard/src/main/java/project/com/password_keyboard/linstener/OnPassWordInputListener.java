package project.com.password_keyboard.linstener;

/**
 * Created by allen_meng on 2018/8/3.
 * 密码输入动态监听
 */

public interface OnPassWordInputListener {

    void onChange(String currentPwd);

    void onFinish(String pwd);

}
