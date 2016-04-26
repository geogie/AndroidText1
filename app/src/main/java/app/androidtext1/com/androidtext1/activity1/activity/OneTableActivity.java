package app.androidtext1.com.androidtext1.activity1.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.androidtext1.com.androidtext1.R;
import app.androidtext1.com.androidtext1.activity1.adapter.OneTableAdapter;
import app.androidtext1.com.androidtext1.activity1.dialog.OneTableDialogFragment;
import app.androidtext1.com.androidtext1.activity1.dialog.OneTableItemDialogFragment;
import app.androidtext1.com.androidtext1.db.DbService;
import greendao.Users;

public class OneTableActivity extends AppCompatActivity implements OneTableDialogFragment.AddUserOnClickListener,
        OneTableItemDialogFragment.EditUserOnClickListener{
    private Toolbar toolbar;
    private ListView lv_oneTable;
    private List<Users> list_user;
    private OneTableAdapter oAdapter;
    private DbService db;
    private OneTableItemDialogFragment oneItemDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_table);

        toolbar = (Toolbar) this.findViewById(R.id.toolbar);
        toolbar.setTitle("单表操作");
        setSupportActionBar(toolbar);

        db = DbService.getInstance(this);

        initControls();

        initData();
    }
    private void initData(){
        list_user = new ArrayList<>();
        list_user = db.loadAllNoteByOrder();
        oAdapter = new OneTableAdapter(this,list_user);
        lv_oneTable.setAdapter(oAdapter);
    }
    private void initControls(){
        lv_oneTable = (ListView) findViewById(R.id.lv_oneTable);
        lv_oneTable.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(OneTableActivity.this, list_user.get(position).getUName() + "--" +
                        list_user.get(position).getId(), Toast.LENGTH_SHORT).show();

                oneItemDialog = new OneTableItemDialogFragment(list_user.get(position).getId(),position);
                oneItemDialog.show(getFragmentManager(),"编辑");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_one_table,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_onetable_add){
            OneTableDialogFragment oneTableDialogFragment = new OneTableDialogFragment(0,"","","","",0);
            oneTableDialogFragment.show(getFragmentManager(),"添加用户");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAddUserOnClick(long uId, String uName, String uSex, String uAge, String uTel, int flag) {
        Users user = new Users();
        if(flag==1) {
            user.setId(uId);
        }
        user.setUSex(uSex);
        user.setUTelphone(uTel);
        user.setUAge(uAge);
        user.setUName(uName);
        if(flag==0) {
            if (db.saveNote(user) > 0) {
                list_user.add(0, user);
                oAdapter.notifyDataSetChanged();
            }
        }else
        {
            if (db.saveNote(user) > 0) {

                int num = 0;
                for(Users u:list_user)
                {
                    if(u.getId()==uId)
                    {
                        list_user.remove(num);
                        list_user.add(num,user);

                        break;
                    }
                    num++;
                }
                oAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onEditUserOnClick(long id, int position, int flag) {
        if(flag==0) {
            db.deleteNote(id);
            list_user.remove(position);
            oAdapter.notifyDataSetChanged();
            oneItemDialog.dismiss();
        }else
        {
            Users updateUser = db.loadNote(id);
            OneTableDialogFragment oDialog = new OneTableDialogFragment(updateUser.getId(),updateUser.getUName(),updateUser.getUSex(),
                    updateUser.getUAge(), updateUser.getUTelphone(),1);
            oneItemDialog.dismiss();
            oDialog.show(getFragmentManager(),"修改");
        }


    }
}
