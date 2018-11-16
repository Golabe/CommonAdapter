package top.golabe.library;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class GoViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;

    public GoViewHolder(@NonNull View itemView) {
        super(itemView);
        mViews = new SparseArray<>();
    }

    public <T> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public GoViewHolder setText(int id, CharSequence text) {
        TextView view = getView(id);
        view.setText(text + "");
        return this;
    }

    public GoViewHolder setText(int id, @StringRes int text) {
        setText(id, itemView.getResources().getString(text));
        return this;
    }

    public GoViewHolder setHintText(int id, CharSequence text) {
        TextView view = getView(id);
        view.setHint(text);
        return this;
    }

    public GoViewHolder setHintText(int id, @StringRes int text) {
        setHintText(id, itemView.getResources().getString(text));
        return this;
    }

    public GoViewHolder setImageResouce(int id, int resId) {
        ImageView view = getView(id);
        view.setImageResource(resId);
        return this;
    }

    public GoViewHolder setImageDrawable(int id, Drawable resId) {
        ImageView view = getView(id);
        view.setImageDrawable(resId);
        return this;
    }

    public GoViewHolder setImageLoader(int id, ImageLoader loader) {
        ImageView view = getView(id);
        loader.loadImage(view);
        return this;
    }

    public GoViewHolder setVisibility(int id, int visibility) {
        View view = getView(id);
        view.setVisibility(visibility);
        return this;
    }


    public GoViewHolder setOnClickListener(View.OnClickListener listener) {
        itemView.setOnClickListener(listener);
        return this;
    }

    public GoViewHolder setOnLongClickListener(View.OnLongClickListener listener) {
        itemView.setOnLongClickListener(listener);
        return this;
    }

    public GoViewHolder setChildClickListener(int id, View.OnClickListener listener) {
        View view = getView(id);
        view.setOnClickListener(listener);
        return this;
    }

    public GoViewHolder setChildLongClickListener(int id, View.OnLongClickListener listener) {
        View view = getView(id);
        view.setOnLongClickListener(listener);
        return this;
    }

    public interface ImageLoader {
          void loadImage(ImageView iv);
    }

}
