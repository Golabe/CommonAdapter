package top.golabe.library.callback;

import android.view.View;

public interface OnItemClickListener<T> {
    void onItemClick(View v,T t, int position);

}
