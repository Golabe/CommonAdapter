package top.golabe.commonadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import top.golabe.intent.IntentGo;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mSingletonLayout,mMultipleLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSingletonLayout=findViewById(R.id.btn_singleton_layout);
        mMultipleLayout=findViewById(R.id.btn_multiple_layout);
        mSingletonLayout.setOnClickListener(this);
        mMultipleLayout.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_singleton_layout:
                IntentGo.with(this)
                        .target(SingletonActivity.class)
                        .go();
                break;
            case R.id.btn_multiple_layout:
                IntentGo.with(this)
                        .target(MultipleActivity.class)
                        .go();
                break;
        }
    }
}
