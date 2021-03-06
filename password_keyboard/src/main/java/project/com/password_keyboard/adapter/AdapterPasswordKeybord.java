package project.com.password_keyboard.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import project.com.password_keyboard.R;
import project.com.password_keyboard.view.PassWordLayout;

/**
 * Created by ihgoo
 */
public class AdapterPasswordKeybord extends RecyclerView.Adapter<AdapterPasswordKeybord.ViewHolder> {

    private final int KEYBORD_SIZE = 12;
    private int passwordLength;
//    private StringBuffer password;
//    private int nowPasswordLength;
    private List<String> numberList;
    private boolean shuffle;
    private  int index = 0 ; //默认的坐标
    private  PassWordLayout mPasswordInputView = null;

    public AdapterPasswordKeybord(boolean isShuffle, PassWordLayout password_input_view) {
        if(null != password_input_view){
            mPasswordInputView = password_input_view;
        }
        setShuffle(isShuffle);
    }


    private void setShuffle(boolean isShuffle) {
        shuffle = isShuffle;
        String[] arr = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
        numberList = Arrays.asList(arr);
        if (isShuffle) {
            Collections.shuffle(numberList);
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_keybord, parent, false);
        AdapterPasswordKeybord.ViewHolder viewHolder = new AdapterPasswordKeybord.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterPasswordKeybord.ViewHolder holder, final int position) {
        int key = 0;
        if (position < 10) {
            key = Integer.parseInt(numberList.get(position));
        }
        if (position < 9) {
            holder.tvKey.setText(String.valueOf(key));
        } else if (position == 9) {
            holder.itemKeybord.setVisibility(View.INVISIBLE);
            holder.tvKey.setVisibility(View.INVISIBLE);
            holder.viewStub.setVisibility(View.INVISIBLE);
//            holder.tvKey.setText("");
        } else if (position == 10) {
            if (shuffle){
                holder.tvKey.setText(numberList.get(9));
            }else {
                holder.tvKey.setText("0");
            }
        }else if(position == 11){
            holder.itemKeybord.setBackground(null);
        }
        if (position < 11 && position != 9) {
            holder.viewStub.setVisibility(View.GONE);
            holder.tvKey.setVisibility(View.VISIBLE);
        } else if (position == 9) {
            holder.viewStub.setVisibility(View.GONE);
            holder.tvKey.setVisibility(View.GONE);
        } else {
            holder.tvKey.setVisibility(View.GONE);
            holder.viewStub.setVisibility(View.VISIBLE);
        }

        int finalKey = key;
        holder.itemKeybord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position < 9) {
//                    mOnKeybordClickListener.onNumberClick(finalKey);
                    mPasswordInputView.addPwd(finalKey+"");
//                    passwordApped(finalKey);
                } else if (position == 9) {

                } else if (position == 10) {
//                    mOnKeybordClickListener.onNumberClick(0);
                    mPasswordInputView.addPwd(finalKey+"");
//                    passwordApped(finalKey);
                } else if (position == 11) {
//                    passwordSub();
//                    mOnKeybordClickListener.onDeleteClick();
                    mPasswordInputView.removePwd();
                }
//                if (nowPasswordLength == passwordLength) {
//                    mOnPasswordCallBack.wholePassword(password.toString());
//                }

            }
        });

    }

//    private void passwordSub() {
//        if (nowPasswordLength > 0) {
//            nowPasswordLength--;
//            password.delete(password.length() - 1, password.length());
//        }
//    }

//    private void passwordApped(int key) {
//        if (password.length() < passwordLength) {
//            password.append(String.valueOf(key));
//            nowPasswordLength++;
//        }
//    }

    @Override
    public int getItemCount() {
        return KEYBORD_SIZE;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvKey;
        ViewStub viewStub;
        RelativeLayout itemKeybord;

        ViewHolder(View itemView) {
            super(itemView);
            tvKey = itemView.findViewById(R.id.tv_keys);
            itemKeybord = itemView.findViewById(R.id.item_keybord);
            viewStub = itemView.findViewById(R.id.view_stub);
        }
    }
}
