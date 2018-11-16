# CommonAdapter
### 通用RectclerView 设配器简单封装
###  gradloe  
```xml
implementation 'top.golabe.CommonAdapter:library:1.0.0'
```
### 使用  
1、继承GoAdapter
```java
public class XXXX extends GoAdapter<T> 
```

2、实现convert()方法
```java
   @Override
    public void convert(GoViewHolder holder,  T t, final int position) {


    }
```
3、重写其中一个构造方法
```java
//单布局
public SingletonAdapter(Context ctx, List<T> data, int layoutId) {
        super(ctx, data, layoutId);
    }
    //多布局
 public SingletonAdapter(Context ctx, List<T> data, MultipleItemType itemType) {
        super(ctx, data, itemType);
   }

```
 上拉加载更多 滑动到底部自动加载
 
    adapter.loadMore(mRecyclerView, new OnLoadScrollListener() {
            @Override
            public void onLoadMore() {
              
            }
        });
