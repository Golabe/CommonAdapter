package top.golabe.library.callback;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

public abstract class OnLoadScrollListener extends RecyclerView.OnScrollListener implements OnLoadListener {

    @Override
    public abstract void onLoadMore();

    public enum LAYOUT_MANAGER_TYPE {
        LINEAR, GRID, STAGGERED_GRID
    }

    protected LAYOUT_MANAGER_TYPE layoutManagerType;
    //最后一个位置
    private int[] lastPositions;
    //最后一个可见Item位置
    private int lastVisibleItemPosition;
    //是否正在加载
    private boolean isLoadMore = false;
    //当前滑动状态
    private int cureentScrollState = 0;

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManagerType == null) {

            if (layoutManager instanceof LinearLayoutManager) {
                layoutManagerType = LAYOUT_MANAGER_TYPE.LINEAR;
            } else if (layoutManager instanceof GridLayoutManager) {
                layoutManagerType = LAYOUT_MANAGER_TYPE.GRID;
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                layoutManagerType = LAYOUT_MANAGER_TYPE.STAGGERED_GRID;
            } else {
                throw new RuntimeException("Unsupported LayoutManager used. Valid ones are LinearLayoutManager, GridLayoutManager and StaggeredGridLayoutManager");
            }
        }
        switch (layoutManagerType) {
            case LINEAR:
                lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                break;
            case GRID:
                lastVisibleItemPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
                break;
            case STAGGERED_GRID:
                StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
                if (lastPositions == null) {
                    lastPositions = new int[staggeredGridLayoutManager.getSpanCount()];
                }
                staggeredGridLayoutManager.findLastVisibleItemPositions(lastPositions);
                lastVisibleItemPosition = findMax(lastPositions);
                break;
        }
    }

    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        cureentScrollState = newState;
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        //返回附着于母RecyclerView子视图当前数量。(可以看见RecyclerView中视图的数量)
        int visibleItemCount = layoutManager.getChildCount();
        // 返回结合到母体RecyclerView适配器的项数。(RecyclerView中视图的总数量)
        int totalItemCount = layoutManager.getItemCount();
        // 条件：当前可见视图数量 > 0 &&满一屏幕 && RecyclerView的状态为静止(没有滑动) && 滑动到视图的底部
        if (visibleItemCount > 0
                && totalItemCount > visibleItemCount
                && cureentScrollState == RecyclerView.SCROLL_STATE_IDLE
                && (lastVisibleItemPosition) >= totalItemCount - 1) {
            onLoadMore();
        } else if (visibleItemCount > 0
                && cureentScrollState == RecyclerView.SCROLL_STATE_IDLE) {

        }

    }
}
