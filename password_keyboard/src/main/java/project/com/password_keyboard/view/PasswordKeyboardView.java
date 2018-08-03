package project.com.password_keyboard.view;


import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import project.com.password_keyboard.R;
import project.com.password_keyboard.adapter.AdapterPasswordKeybord;
import project.com.password_keyboard.linstener.OnCloseClickListener;
import project.com.password_keyboard.linstener.OnForgetPasswordListener;
import project.com.password_keyboard.linstener.OnPassWordInputListener;
import project.com.password_keyboard.manager.GridSpacingItemDecoration;

/**
 * Created by allenmeng
 */
public class PasswordKeyboardView extends RelativeLayout {

    private final int ROW = 3;
    private RecyclerView keybordView;
    private ImageView layoutBack;
    private PassWordLayout password_input_view;
    private TextView tv_forget_password; //忘记密码
    private AdapterPasswordKeybord adapterPasswordKeybord;
    private OnCloseClickListener mOnCloseClickListener;
    private OnForgetPasswordListener mOnForgetPasswordListener;
    private OnPassWordInputListener mOnPassWordInputListener;

    public void setmOnPassWordInputListener(OnPassWordInputListener mOnPassWordInputListener) {
        this.mOnPassWordInputListener = mOnPassWordInputListener;
    }

    public void setmOnForgetPasswordListener(OnForgetPasswordListener mOnForgetPasswordListener) {
        this.mOnForgetPasswordListener = mOnForgetPasswordListener;
    }

    private int pwdLength;
    private boolean shuffle;

    private View inflateView;
    private Context mContext;


    public PasswordKeyboardView(Context context) {
        super(context);
    }

    public void setOnCloseClickListener(OnCloseClickListener onCloseClickListener) {
        this.mOnCloseClickListener = onCloseClickListener;
    }

    public PasswordKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        inflateView = View.inflate(context, R.layout.layout_password_keyboard, null);
        layoutBack = inflateView.findViewById(R.id.layoutBack);
        password_input_view = inflateView.findViewById(R.id.password_input_view);
        tv_forget_password = inflateView.findViewById(R.id.tv_forget_password);
        keybordView = inflateView.findViewById(R.id.rv_keybord);
        initListener(layoutBack, password_input_view, tv_forget_password);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.password_keybord);
        shuffle = typedArray.getBoolean(R.styleable.password_keybord_shuffle, true);
        pwdLength = typedArray.getInteger(R.styleable.password_keybord_pwdLength, 6);
        build();
    }

    private void initListener(ImageView layoutBack, PassWordLayout password_input_view, TextView tv_forget_password) {
        //默认第一个
        PassWordView passWordView = (PassWordView) password_input_view.getChildAt(0);
        if (passWordView != null) {
            passWordView.setmIsShowRemindLine(true);
            passWordView.startInputState();
        }
        layoutBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnCloseClickListener != null) {
                    mOnCloseClickListener.onCloseClick();
                }
            }
        });

        password_input_view.setPwdChangeListener(new PassWordLayout.pwdChangeListener() {
            @Override
            public void onChange(String pwd) {
                if(null != mOnPassWordInputListener){
                    mOnPassWordInputListener.onChange(pwd);
                }
            }
            @Override
            public void onNull() {

            }
            @Override
            public void onFinished(String pwd) {
                if(null != mOnPassWordInputListener){
                    mOnPassWordInputListener.onFinish(pwd);
                }
            }
        });

        tv_forget_password.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mOnForgetPasswordListener) {
                    mOnForgetPasswordListener.onForgetPwd();
                }
            }
        });

    }


    private PasswordKeyboardView build() {
        adapterPasswordKeybord = new AdapterPasswordKeybord(shuffle, password_input_view);
        int spanCount = 3; // 3 columns
        int spacing = 30; // 50px
        boolean includeEdge = true;
        keybordView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        keybordView.setAdapter(adapterPasswordKeybord);
        keybordView.setLayoutManager(new GridLayoutManager(mContext, ROW));
        addView(inflateView);
        return this;
    }


    public PasswordKeyboardView rebuild() {
        adapterPasswordKeybord = new AdapterPasswordKeybord(shuffle, password_input_view);
        int spanCount = 3; // 3 columns
        int spacing = 30; // 50px
        boolean includeEdge = true;
        keybordView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        keybordView.setAdapter(adapterPasswordKeybord);
        keybordView.setLayoutManager(new GridLayoutManager(mContext, ROW));
        removeAllViews();
        addView(inflateView);
        return this;
    }


    public int getPwdLength() {
        return pwdLength;
    }

    public PasswordKeyboardView setPwdLength(int pwdLength) {
        this.pwdLength = pwdLength;
        return this;
    }

    public boolean isShuffle() {
        return shuffle;
    }

    public PasswordKeyboardView setShuffle(boolean shuffle) {
        this.shuffle = shuffle;
        return this;
    }

    public AdapterPasswordKeybord getPasswordKeybord() {
        return adapterPasswordKeybord;
    }

    /**
     * 获取输入的密码
     *
     * @return
     */
    public String getPassWord() {
        if (null != password_input_view) {
            return password_input_view.getPassString();
        } else {
            return "";
        }
    }


}
