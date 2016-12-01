package winning.checkboxtest;

import android.databinding.DataBindingUtil;
import android.databinding.tool.DataBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.Toast;

import java.util.ArrayList;

import winning.checkboxtest.adapter.MyCheckboxAdapter;
import winning.checkboxtest.bean.MyCheckboxBean;
import winning.checkboxtest.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    //相关数据
    private ArrayList<MyCheckboxBean> arr = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        //初始化数据
        initDatas();
        //初始化布局控件
        initViews();
    }

    private void initViews() {
        final MyCheckboxAdapter adapter = new MyCheckboxAdapter(MainActivity.this,arr,binding.manualChec);
        binding.lvManualCheck.setAdapter(adapter);
        binding.lvManualCheck.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> view, View view1, int i, long l) {
                arr.get(i).setByMeChecked(!arr.get(i).isByMeChecked());
                adapter.notifyDataSetChanged();
            }
        });

        //全选以及全部反选状态的操作
        binding.manualChec.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(MainActivity.this,"全选啦",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //顶部Checkbox的监听
        binding.manualChec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.manualChec.isChecked()) {
                    //  MyApplicationUtil.putCheckMap(true);

                    for (int i = 0; i < arr.size(); i++) {
                        if (!arr.get(i).isByMeChecked()) {
                            arr.get(i).setByMeChecked(true);
                            adapter.notifyDataSetChanged();
                        }
                    }
                } else {
                    // MyApplicationUtil.putCheckMap(false);
                    for (int i = 0; i < arr.size(); i++) {
                        if (arr.get(i).isByMeChecked()) {
                            arr.get(i).setByMeChecked(false);
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        });


    }

    private void initDatas() {
        arr = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            MyCheckboxBean bean = new MyCheckboxBean("张"+i,false);
                arr.add(bean);
        }
    }
}
