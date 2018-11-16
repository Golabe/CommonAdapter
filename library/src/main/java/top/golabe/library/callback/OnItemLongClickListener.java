package top.golabe.library.callback;

import android.view.View;

public interface OnItemLongClickListener<T> {
      void onItemLongClick(View v, T t, int position);
}
