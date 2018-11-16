package top.golabe.library;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import top.golabe.library.callback.MultipleItemType;
import top.golabe.library.callback.OnItemClickListener;
import top.golabe.library.callback.OnItemLongClickListener;
import top.golabe.library.callback.OnLoadScrollListener;

public abstract class GoAdapter<T> extends RecyclerView.Adapter<GoViewHolder> {
    private View mLoadView=null;

    protected List<T> mData;
    public Context mContext;
    private MultipleItemType mMultipleItemType = null;
    private int mLayoutId;

    public GoAdapter(Context ctx, List<T> data, int layoutId) {
        this.mContext = ctx;
        this.mData = data;
        this.mLayoutId = layoutId;
    }

    public GoAdapter(Context ctx, List<T> data, MultipleItemType itemType) {
        this(ctx, data, -1);
        this.mMultipleItemType = itemType;
    }

    public void setLoadView(View view){
        this.mLoadView=view;
    }
    @NonNull
    @Override
    public GoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mMultipleItemType != null) {
            mLayoutId = viewType;
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(mLayoutId, parent, false);
        return new GoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GoViewHolder holder, int position) {
        convert(holder, mData.get(position), position);
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(holder.itemView, mData.get(position), position);
        }
        if (mOnItemLongClickListener != null) {
            mOnItemLongClickListener.onItemLongClick(holder.itemView, mData.get(position), position);
        }
    }

    public abstract void convert(GoViewHolder holder, T t, int position);

    @Override
    public int getItemViewType(int position) {
        return mMultipleItemType != null ? mMultipleItemType.getLayoutId(mData.get(position), position) : super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    private OnItemClickListener mOnItemClickListener = null;
    private OnItemLongClickListener mOnItemLongClickListener = null;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.mOnItemLongClickListener = listener;
    }

    public void loadMore(RecyclerView rv,int layoutId,OnLoadScrollListener listener){

    }
    public  void loadMore(RecyclerView rv, OnLoadScrollListener listener){
        rv.addOnScrollListener(listener);
    }
}
