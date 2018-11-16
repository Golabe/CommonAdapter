package top.golabe.commonadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import top.golabe.commonadapter.entity.Multiple;
import top.golabe.library.GoAdapter;
import top.golabe.library.GoViewHolder;
import top.golabe.library.callback.MultipleItemType;

public class MultipleActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private static final String TAG = "MultipleActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple);
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Multiple> list=new ArrayList<>();

        Multiple multiple = new Multiple();
        multiple.setItemType(0);
        Multiple multiple1 = new Multiple();
        multiple1.setItemType(1);
        Multiple multiple2 = new Multiple();
        multiple2.setItemType(2);


        list.add(multiple);
        list.add(multiple1);
        list.add(multiple2);
        mRecyclerView.setAdapter(new GoAdapter<Multiple>(this, list, new MultipleItemType<Multiple>() {
            @Override
            public int getLayoutId(Multiple item, int position) {
                switch (item.getItemType()) {
                    case 0:
                        return R.layout.item_multiple_a;
                    case 1:
                        return R.layout.item_multiple_b;
                    case 2:
                        return R.layout.item_multiple_c;
                    default:
                        return R.layout.item_multiple_a;
                }


            }
        }) {
            @Override
            public void convert(GoViewHolder holder, Multiple multiple, int position) {

            }


        });
    }
}
