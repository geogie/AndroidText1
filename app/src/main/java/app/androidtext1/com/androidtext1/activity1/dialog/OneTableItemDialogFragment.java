package app.androidtext1.com.androidtext1.activity1.dialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import app.androidtext1.com.androidtext1.R;

/**
 * Created by Administrator on 2016/4/26 0026.
 */
public class OneTableItemDialogFragment extends DialogFragment {
    private long id;
    private int position;
    private TextView txt_onetable_update;
    private TextView txt_onetable_delete;

    public interface EditUserOnClickListener {
        void onEditUserOnClick(long id, int position, int flag);
    }

    public OneTableItemDialogFragment(long id, int position) {
        this.id = id;
        this.position = position;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.fragment_onetable_itemdialog, null);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txt_onetable_update = (TextView) view.findViewById(R.id.txt_onetable_update);
        txt_onetable_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditUserOnClickListener listener = (EditUserOnClickListener) getActivity();
                listener.onEditUserOnClick(id, position, 1);
            }
        });
        txt_onetable_delete = (TextView) view.findViewById(R.id.txt_onetable_delete);
        txt_onetable_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditUserOnClickListener listener = (EditUserOnClickListener) getActivity();
                listener.onEditUserOnClick(id, position, 0);
            }
        });
    }
}
