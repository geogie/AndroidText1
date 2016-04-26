package app.androidtext1.com.androidtext1.activity1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import app.androidtext1.com.androidtext1.R;
import greendao.Users;

/**
 * Created by Administrator on 2016/4/26 0026.
 */
public class OneTableAdapter extends BaseAdapter{
    private LayoutInflater inflater;
    private List<Users> list_user;

    public OneTableAdapter(Context context,List<Users> list_user) {
        this.inflater = LayoutInflater.from(context);
        this.list_user = list_user;
    }

    @Override
    public int getCount() {
        return list_user.size();
    }

    @Override
    public Object getItem(int position) {
        return list_user.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        UserInfo uInfo;
        if (convertView == null){
            uInfo = new UserInfo();
            convertView = inflater.inflate(R.layout.activity_onetable_lv_item,null);
            uInfo.uAge = (TextView) convertView.findViewById(R.id.txt_onetable_age);
            uInfo.uName = (TextView) convertView.findViewById(R.id.txt_onetable_uName);
            uInfo.uSex = (TextView) convertView.findViewById(R.id.txt_onetable_uSex);
            uInfo.uTel = (TextView) convertView.findViewById(R.id.txt_onetable_tel);

            convertView.setTag(uInfo);
        }else {
            uInfo = (UserInfo) convertView.getTag();
        }
        uInfo.uSex.setText(list_user.get(position).getUSex());
        uInfo.uTel.setText(list_user.get(position).getUTelphone());
        uInfo.uName.setText(list_user.get(position).getUName());
        uInfo.uAge.setText(list_user.get(position).getUAge());
        return convertView;
    }
    public class UserInfo{
        TextView uName;
        TextView uSex;
        TextView uAge;
        TextView uTel;
    }
}
