package com.erayic.agr.common.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.erayic.agr.common.AgrConstant;
import com.erayic.agr.common.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * author：luck
 * project：LeTuGolf
 * package：com.tongyu.luck.paradisegolf.adapter
 * email：893855882@qq.com
 * data：16/7/27
 */
public class CommonGridImageAdapter extends
        RecyclerView.Adapter<CommonGridImageAdapter.ViewHolder> {
    public static final int TYPE_CAMERA = 1;
    public static final int TYPE_PICTURE = 2;
    private LayoutInflater mInflater;
    private List<CommonLocalMedia> list = new ArrayList<>();
    private int selectMax = 9;
    private boolean isEdit = false;
    /**
     * 点击添加图片跳转or删除图片
     */
    private onAddPicClickListener mOnAddPicClickListener;

    public interface onAddPicClickListener {
        void onAddPicClick(int type, int position);
    }

    public CommonGridImageAdapter(Context context, onAddPicClickListener mOnAddPicClickListener) {
        mInflater = LayoutInflater.from(context);
        this.mOnAddPicClickListener = mOnAddPicClickListener;
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
    }

    public void setSelectMax(int selectMax) {
        this.selectMax = selectMax;
    }

    public void setList(List<CommonLocalMedia> list) {
        this.list = list;
    }

    public List<CommonLocalMedia> getList() {
        return list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mImg;
        LinearLayout ll_del;

        public ViewHolder(View view) {
            super(view);
            mImg = (ImageView) view.findViewById(R.id.fiv);
            ll_del = (LinearLayout) view.findViewById(R.id.ll_del);
        }
    }

    @Override
    public int getItemCount() {
        if (isEdit && (list.size() < selectMax)) {
            return list.size() + 1;
        } else {
            return list.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isShowAddItem(position)) {
            return TYPE_CAMERA;
        } else {
            return TYPE_PICTURE;
        }
    }

    /**
     * 创建ViewHolder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.gv_filter_image,
                viewGroup, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        //itemView 的点击事件
        if (mItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onItemClick(viewHolder.getAdapterPosition(), v);
                }
            });
        }
        return viewHolder;
    }

    private boolean isShowAddItem(int position) {
        int size = list.size() == 0 ? 0 : list.size();
        return position == size;
    }

    /**
     * 设置值
     */
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        //少于8张，显示继续添加的图标
        if (isEdit && (getItemViewType(position) == TYPE_CAMERA)) {
            viewHolder.mImg.setImageResource(R.drawable.addimg_1x);
            viewHolder.mImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnAddPicClickListener != null)
                        mOnAddPicClickListener.onAddPicClick(0, viewHolder.getAdapterPosition());
                }
            });
            viewHolder.ll_del.setVisibility(View.INVISIBLE);
        } else {
            if (isEdit)
                viewHolder.ll_del.setVisibility(View.VISIBLE);
            else
                viewHolder.ll_del.setVisibility(View.INVISIBLE);
            viewHolder.ll_del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnAddPicClickListener != null)
                        mOnAddPicClickListener.onAddPicClick(1, viewHolder.getAdapterPosition());
                }
            });
            CommonLocalMedia media = list.get(position);
            int type = media.isUpload() ? 1 : media.getLocalMedia().getMimeType();
            String path = "";
            if (media.isUpload()) {
                path = AgrConstant.IMAGE_URL_IMAGE + media.getResultImage().getRamFileName();
            } else if (media.getLocalMedia().isCut() && !media.getLocalMedia().isCompressed()) {
                // 裁剪过
                path = media.getLocalMedia().getCutPath();
            } else if (media.getLocalMedia().isCompressed() || (media.getLocalMedia().isCut() && media.getLocalMedia().isCompressed())) {
                // 压缩过,或者裁剪同时压缩过,以最终压缩过图片为准
                path = media.getLocalMedia().getCompressPath();
            } else {
                // 原图
                path = media.getLocalMedia().getPath();
            }

            switch (type) {
                case 1:
                    // 图片
                    if ((!media.isUpload()) && media.getLocalMedia().isCompressed()) {
                        Log.i("compress image result", new File(media.getLocalMedia().getCompressPath()).length() / 1024 + "k");
                        Log.i("原图地址::", media.getLocalMedia().getPath());
                        Log.i("压缩地址::", media.getLocalMedia().getCompressPath());
                    }

//                    RequestOptions options = new RequestOptions()
//                            .centerCrop()
//                            .placeholder(R.color.color_f6)
//                            .diskCacheStrategy(DiskCacheStrategy.ALL);
                    Glide.with(viewHolder.itemView.getContext())
                            .load(path)
                            .apply(AgrConstant.iconOptions)
                            .into(viewHolder.mImg);
                    break;
                case 2:
                    // 视频
                    Log.i("时长:", media.getLocalMedia().getDuration() + "");
                    Glide.with(viewHolder.itemView.getContext()).load(path).thumbnail(0.5f).into(viewHolder.mImg);
                    break;
                default:

                    break;
            }

        }
    }

    protected OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position, View v);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }
}
