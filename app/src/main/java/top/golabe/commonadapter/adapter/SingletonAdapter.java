package top.golabe.commonadapter.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import top.golabe.commonadapter.R;
import top.golabe.commonadapter.entity.User;
import top.golabe.library.GoAdapter;
import top.golabe.library.GoViewHolder;

public class SingletonAdapter extends GoAdapter<User> {


    public SingletonAdapter(Context ctx, List<User> data, int layoutId) {
        super(ctx, data, layoutId);
    }

    @Override
    public void convert(GoViewHolder holder, final User user, final int position) {
        holder.setText(R.id.tv_username, user.getUsername())
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                }).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        }).setImageLoader(R.id.iv_avatar, new GoViewHolder.ImageLoader() {
            @Override
            public void loadImage(ImageView iv) {
                Glide.with(mContext).load(user.getAvatar()).into(iv);
            }

        }).setChildClickListener(R.id.tv_username, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }).setChildLongClickListener(R.id.tv_username, new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });

    }


}
