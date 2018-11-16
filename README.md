# CommonAdapter
### 通用RectclerView 设配器简单封装
###  gradloe  
```xml
implementation 'top.golabe.CommonAdapter:library:1.0.1'
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
 4、上拉加载更多 滑动到底部自动加载
 
    adapter.loadMore(mRecyclerView, new OnLoadScrollListener() {
            @Override
            public void onLoadMore() {
              
            }
        });
        
  5、在convert中进行数据绑定
  ```java
   @Override
    public void convert(GoViewHolder holder, final User user, final int position) {
        holder.setText(R.id.tv_username, user.getUsername())
        
       //Item点击
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                })
                //Item长按
                .setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        })
        //加载网络图片
        .setImageLoader(R.id.iv_avatar, new GoViewHolder.ImageLoader() {
            @Override
            public void loadImage(ImageView iv) {
                Glide.with(mContext).load(user.getAvatar()).into(iv);
            }

        })
        //Item子控件点击
        .setChildClickListener(R.id.tv_username, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
            //Item子控件长按
        }).setChildLongClickListener(R.id.tv_username, new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        })
        //加载Drawable
         .setImageDrawable(R.id.iv_avatar,mContext.getResources().getDrawable(R.mipmap.ic_launcher))
         //加载Resouce
         .setImageResouce(R.id.iv_avatar,R.mipmap.ic_launcher)
        
         .setHintText(R.id.tv_username,"")
        .setVisibility(R.id.iv_avatar,View.VISIBLE);

    }
  ```
