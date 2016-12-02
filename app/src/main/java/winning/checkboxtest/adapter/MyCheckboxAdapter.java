package winning.checkboxtest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

import winning.checkboxtest.R;
import winning.checkboxtest.bean.MyCheckboxBean;

/**
 * Created by Jiang on 2016/12/1.
 */

public class MyCheckboxAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private Context mContext;
    private ArrayList<MyCheckboxBean> arr;
    private CheckBox chb_all_check;

    public MyCheckboxAdapter(Context mContext, ArrayList<MyCheckboxBean> arr, CheckBox chb_all_check) {
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
        this.chb_all_check = chb_all_check;
        this.arr = arr;
    }

    @Override
    public int getCount() {
        return arr == null ? 0 : arr.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup group) {
        MyCheckboxBean bean = arr.get(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_lv_layout, null);
            viewHolder = new ViewHolder();
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tv_amount = (TextView) convertView.findViewById(R.id.tv_amount);
            viewHolder.chb_manual = (CheckBox) convertView.findViewById(R.id.chb_manual);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final int tempPosition = position;
        viewHolder.chb_manual.setChecked(bean.isByMeChecked());
        viewHolder.chb_manual.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                arr.get(tempPosition).setByMeChecked(isChecked);
                //如果全选，则将头布局的checkbox选中，反之同理
                int j = 0;
                for (int i = 0; i < arr.size(); i++) {
                    if (arr.get(i).isByMeChecked()) {
                        j++;
                    }
                }
                //CommonUtil.showToast(mContext,j+"");
                if (j == arr.size()) {
                    chb_all_check.setChecked(true);
//                    Intent intent = new Intent(mActivity, LoginActivity.class);
//                   // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    mContext.startActivity(intent);
                } else {
                    chb_all_check.setChecked(false);
                }

            }
        });

        return convertView;
    }

    public static class ViewHolder {
        private TextView tv_name;
        private TextView tv_amount;
        private CheckBox chb_manual;
    }
}
