package top.golabe.commonadapter;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import top.golabe.commonadapter.adapter.SingletonAdapter;
import top.golabe.commonadapter.entity.User;
import top.golabe.library.callback.OnLoadScrollListener;

public class SingletonActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singleton);
        mRecyclerView = findViewById(R.id.recycler_view);
        mRefreshLayout = findViewById(R.id.refresh_layout);
        mRefreshLayout.setRefreshing(true);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this,1000 );
                mRefreshLayout.setRefreshing(false);
            }
        }, 1000);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        final List<User> users = initData();
        final SingletonAdapter adapter = new SingletonAdapter(this, users, R.layout.item_singleton);
        mRecyclerView.setAdapter(adapter);

        adapter.loadMore(mRecyclerView, new OnLoadScrollListener() {
            @Override
            public void onLoadMore() {
                adapter.addAll(users);
            }
        });
    }

    @NonNull
    private List<User> initData() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setUsername("username" + i);
            user.setNickname("nickname" + i);
            user.setAvatar("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3197537752,2095789724&fm=27&gp=0.jpg");
            user.setAge(i);
            user.setAddress("address" + i);
            users.add(user);

        }
        return users;
    }
}
