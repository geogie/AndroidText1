package app.androidtext1.com.androidtext1.activity1.dialog;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import app.androidtext1.com.androidtext1.R;

/**
 * Created by Administrator on 2016/4/26 0026.
 */
@SuppressLint("ValidFragment")
public class OneTableDialogFragment extends DialogFragment{
    private EditText edit_onetable_name;
    private EditText edit_onetable_sex;
    private EditText edit_onetable_tel;
    private EditText edit_onetable_age;

    private String uName;
    private String uSex;
    private String uAge;
    private String uTel;
    private int flag;
    private long uId;
    public interface AddUserOnClickListener{
        void onAddUserOnClick(long id, String uName, String uSex, String uAge, String uTel,int flag);
    }
    @SuppressLint("ValidFragment")
    public OneTableDialogFragment(long uId,String uName, String uSex, String uAge, String uTel, int flag) {
        this.uName = uName;
        this.uSex = uSex;
        this.uAge = uAge;
        this.uTel = uTel;
        this.flag = flag;
        this.uId = uId;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_onetable_dialog,null);
        edit_onetable_name = (EditText) view.findViewById(R.id.edit_onetable_name);
        edit_onetable_sex = (EditText) view.findViewById(R.id.edit_onetable_sex);
        edit_onetable_tel = (EditText) view.findViewById(R.id.edit_onetable_tel);
        edit_onetable_age = (EditText) view.findViewById(R.id.edit_onetable_age);

        edit_onetable_name.setText(uName);
        edit_onetable_age.setText(uAge);
        edit_onetable_sex.setText(uSex);
        edit_onetable_tel.setText(uTel);
        builder.setView(view)
                .setTitle("添加用户")
                .setPositiveButton("添加",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                AddUserOnClickListener listener = (AddUserOnClickListener) getActivity();
                                listener.onAddUserOnClick(uId,edit_onetable_name.getText().toString(),
                                        edit_onetable_sex.getText().toString(),
                                        edit_onetable_age.getText().toString(),
                                        edit_onetable_tel.getText().toString(),
                                        flag);
                            }
                        })
        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                edit_onetable_name.setText("");
                edit_onetable_sex.setText("");
                edit_onetable_age.setText("");
                edit_onetable_tel.setText("");
            }
        });
        return builder.create();
    }
}
