package com.erayic.agr.jobs.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.chad.library.adapter.base.BaseViewHolder;
import com.erayic.agr.common.AgrConstant;
import com.erayic.agr.common.config.FullyGridLayoutManager;
import com.erayic.agr.common.net.back.enums.EnumUserRole;
import com.erayic.agr.common.net.back.work.CommonJobsListManagerBean;
import com.erayic.agr.common.net.back.work.CommonJobsListUserBean;
import com.erayic.agr.common.util.ErayicLog;
import com.erayic.agr.common.util.ErayicNetDate;
import com.erayic.agr.common.view.SectionedRecyclerViewAdapter;
import com.erayic.agr.jobs.R;
import com.erayic.agr.jobs.adapter.holder.JobsItemChildByAdminViewHolder;
import com.erayic.agr.jobs.adapter.holder.JobsItemChildByUserViewHolder;
import com.erayic.agr.jobs.adapter.holder.JobsItemGroupByUserViewHolder;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;

import org.joda.time.DateTime;

import java.util.List;

import static com.bumptech.glide.request.RequestOptions.centerCropTransform;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class JobsListItemAdapter extends SectionedRecyclerViewAdapter<BaseViewHolder, BaseViewHolder, BaseViewHolder> {

    private Context context;
    private SparseBooleanArray mBooleanMap;
    private List<CommonJobsListUserBean.JobsInfo> userList;
    private List<CommonJobsListManagerBean.JobsInfo> managerList;
    private int role = -1;
    private int onItemPosition = -1;//点击的section

    private OnItemScrollToPositionWithOffset onItemScrollToPositionWithOffset;
    private OnItemInfoClickListener onItemInfoClickListener;

    public JobsListItemAdapter(Context context, List<CommonJobsListUserBean.JobsInfo> userlist, int role) {
        this.context = context;
        mBooleanMap = new SparseBooleanArray();
        this.userList = userlist;
        this.role = role;

    }

    public void setUserList(List<CommonJobsListUserBean.JobsInfo> userList) {
        this.userList = userList;
    }

    public void setManagerList(List<CommonJobsListManagerBean.JobsInfo> managerList) {
        this.managerList = managerList;
    }

    public void setOnItemScrollToPositionWithOffset(OnItemScrollToPositionWithOffset onItemScrollToPositionWithOffset) {
        this.onItemScrollToPositionWithOffset = onItemScrollToPositionWithOffset;
    }

    public void setOnItemInfoClickListener(OnItemInfoClickListener onItemInfoClickListener) {
        this.onItemInfoClickListener = onItemInfoClickListener;
    }

    @Override
    protected int getSectionCount() {
        switch (role) {
            case EnumUserRole.Role_Usage:
                return userList != null ? userList.size() : 0;
            case EnumUserRole.Role_Admin:
            case EnumUserRole.Role_Manager:
                return managerList != null ? managerList.size() : 0;
            default:
                return 0;
        }

    }

    @Override
    protected int getItemCountForSection(int section) {
        switch (role) {
            case EnumUserRole.Role_Usage: {
                int count = userList.get(section).getContents() == null ? 0 : userList.get(section).getContents().size();
                if (count >= 0 && !mBooleanMap.get(section)) {
                    count = 0;
                }
                return userList.get(section).getContents() == null ? 0 : count;
            }
            case EnumUserRole.Role_Admin:
            case EnumUserRole.Role_Manager: {
                int count = managerList.get(section).getJobs() == null ? 0 : managerList.get(section).getJobs().size();
                if (count >= 0 && !mBooleanMap.get(section)) {
                    count = 0;
                }
                return managerList.get(section).getJobs() == null ? 0 : count;
            }
            default:
                return 0;
        }


    }

    @Override
    protected boolean hasFooterInSection(int section) {
        return false;
    }

//    @Override
//    protected int getSectionHeaderViewType(int section) {
////        return super.getSectionHeaderViewType(section);
//        return TYPE_JOBS_USER;
//    }

    @Override
    protected int getSectionItemViewType(int section, int position) {
        switch (role) {
            case EnumUserRole.Role_Admin:
                return EnumUserRole.Role_Admin;
            case EnumUserRole.Role_Manager:
                return EnumUserRole.Role_Manager;
            case EnumUserRole.Role_Usage:
                return EnumUserRole.Role_Usage;
            default:
                return -1;
        }
    }

    @Override
    protected BaseViewHolder onCreateSectionHeaderViewHolder(ViewGroup parent, int viewType) {
        return new JobsItemGroupByUserViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_jobs_item_group, parent, false));
    }

    @Override
    protected BaseViewHolder onCreateSectionFooterViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected BaseViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        switch (role) {
            case EnumUserRole.Role_Admin:
            case EnumUserRole.Role_Manager:
                return new JobsItemChildByAdminViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_jobs_item_child_admin, parent, false));
            case EnumUserRole.Role_Usage:
                return new JobsItemChildByUserViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_jobs_item_child_user, parent, false));
            default:
                return null;
        }

    }

    @Override
    protected void onBindSectionHeaderViewHolder(BaseViewHolder holder, final int section) {
        switch (role) {
            case EnumUserRole.Role_Admin:
            case EnumUserRole.Role_Manager: {
                if (holder instanceof JobsItemGroupByUserViewHolder) {
                    ((JobsItemGroupByUserViewHolder) holder).jobsContentIcon.setImageDrawable(ContextCompat.getDrawable(context,
                            mBooleanMap.get(section) ? R.drawable.image_jobs_ui_open : R.drawable.image_jobs_ui_close));
                    ((JobsItemGroupByUserViewHolder) holder).jobsContentName.setText(managerList.get(section).getJobName());

                    ((JobsItemGroupByUserViewHolder) holder).jobsContentProgress.setProgress(managerList.get(section).getPercentage());
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            boolean isOpen = mBooleanMap.get(section);
                            mBooleanMap.put(section, !isOpen);
                            if (onItemPosition >= 0 && onItemPosition != section) {
                                mBooleanMap.put(onItemPosition, false);
                            }
                            onItemPosition = section;
                            notifyDataSetChanged();
                            if (onItemScrollToPositionWithOffset != null)
                                onItemScrollToPositionWithOffset.scrollToPositionWithOffset(section);
                        }
                    });
                    holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            return true;
                        }
                    });
                }
            }
            break;
            case EnumUserRole.Role_Usage:
                if (holder instanceof JobsItemGroupByUserViewHolder) {
                    ((JobsItemGroupByUserViewHolder) holder).jobsContentIcon.setImageDrawable(ContextCompat.getDrawable(context,
                            mBooleanMap.get(section) ? R.drawable.image_jobs_ui_open : R.drawable.image_jobs_ui_close));
                    ((JobsItemGroupByUserViewHolder) holder).jobsContentName.setText(userList.get(section).getJobName());

                    ((JobsItemGroupByUserViewHolder) holder).jobsContentProgress.setProgress(userList.get(section).getPercentage());
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            boolean isOpen = mBooleanMap.get(section);
                            mBooleanMap.put(section, !isOpen);
                            if (onItemPosition >= 0 && onItemPosition != section) {
                                mBooleanMap.put(onItemPosition, false);
                            }
                            onItemPosition = section;
                            notifyDataSetChanged();
                            if (onItemScrollToPositionWithOffset != null)
                                onItemScrollToPositionWithOffset.scrollToPositionWithOffset(section);
                        }
                    });
                    holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            return true;
                        }
                    });
                }
                break;
        }

    }

    @Override
    protected void onBindSectionFooterViewHolder(BaseViewHolder holder, int section) {

    }

    @Override
    protected void onBindItemViewHolder(BaseViewHolder holder, final int section, final int position) {
        switch (role) {
            case EnumUserRole.Role_Admin:
            case EnumUserRole.Role_Manager:
                if (holder instanceof JobsItemChildByAdminViewHolder) {
                    ((JobsItemChildByAdminViewHolder) holder).jobsContentName.setText(section + "  " + position);

                    DateTime dateTime = new DateTime(ErayicNetDate.getLongDates(managerList.get(section).getJobs().get(position).getFinishTime()));
                    if (managerList.get(section).getJobs().get(position).isFinish()) {
                        ((JobsItemChildByAdminViewHolder) holder).jobsContentName.setVisibility(View.VISIBLE);
                        ((JobsItemChildByAdminViewHolder) holder).jobsContentName.setText(dateTime.toString("HH:mm"));
                    } else {
                        ((JobsItemChildByAdminViewHolder) holder).jobsContentName.setVisibility(View.GONE);
                    }

                    String strUserName = "";
                    for (CommonJobsListManagerBean.OperatersInfo userInfo : managerList.get(section).getJobs().get(position).getOperaters()) {
                        strUserName += userInfo.getUserName() + "\n";
                    }
                    if (TextUtils.isEmpty(strUserName))
                        ((JobsItemChildByAdminViewHolder) holder).jobsContentSubName.setText("无负责人");
                    else
                        ((JobsItemChildByAdminViewHolder) holder).jobsContentSubName.setText(strUserName.substring(0, strUserName.length() - 1));
                    FullyGridLayoutManager manager = new FullyGridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false);
                    JobsListGridImageAdapter adapter = new JobsListGridImageAdapter(context, null);
                    ((JobsItemChildByAdminViewHolder) holder).jobsContentImg.setLayoutManager(manager);
                    ((JobsItemChildByAdminViewHolder) holder).jobsContentImg.setAdapter(adapter);
//                    ImageAdapter imageAdapter = new ImageAdapter(section, position);
//                    ((JobsItemChildByAdminViewHolder) holder).jobsContentImg.setAdapter(imageAdapter);

                    if (managerList.get(section).getJobs().get(position).isFinish()) {
                        ((JobsItemChildByAdminViewHolder) holder).jobsContentStatus.setTextColor(ContextCompat.getColor(context, R.color.app_base_text_title_2));
                        ((JobsItemChildByAdminViewHolder) holder).jobsContentStatus.setText(TextUtils.isEmpty(managerList.get(section).getJobs().get(position).getRecorder().getDescript())
                                ? "未填写说明" : managerList.get(section).getJobs().get(position).getRecorder().getDescript());
                        ((JobsItemChildByAdminViewHolder) holder).jobsContentImg.setVisibility(View.VISIBLE);
                        //设置图片
                        adapter.setNewData(managerList.get(section).getJobs().get(position).getRecorder().getRecords());

                    } else {
                        ((JobsItemChildByAdminViewHolder) holder).jobsContentStatus.setTextColor(ContextCompat.getColor(context, R.color.ui_btn_background_red));
                        ((JobsItemChildByAdminViewHolder) holder).jobsContentStatus.setText("待完成");
                        ((JobsItemChildByAdminViewHolder) holder).jobsContentImg.setVisibility(View.GONE);
                    }
                    ((JobsItemChildByAdminViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onItemInfoClickListener != null)
                                onItemInfoClickListener.onClick(v, managerList.get(section).getJobs().get(position).getSchID(), managerList.get(section).getJobs().get(position).getUnitID(),
                                        managerList.get(section).getJobName(), managerList.get(section).getJobs().get(position).isFinish());
                        }
                    });
                    ((JobsItemChildByAdminViewHolder) holder).itemView.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            return true;
                        }
                    });
                }
                break;
            case EnumUserRole.Role_Usage:
                if (holder instanceof JobsItemChildByUserViewHolder) {
                    DateTime dateTime = new DateTime(ErayicNetDate.getLongDates(userList.get(section).getContents().get(position).getDemandTime()));
                    ((JobsItemChildByUserViewHolder) holder).jobsContentName.setText(dateTime.toString("HH:mm"));
                    ((JobsItemChildByUserViewHolder) holder).jobsContentUnit.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//下划线
                    ((JobsItemChildByUserViewHolder) holder).jobsContentUnit.getPaint().setAntiAlias(true);//抗锯齿
                    ((JobsItemChildByUserViewHolder) holder).jobsContentUnit.setText(userList.get(section).getContents().get(position).getUnitName());
                    if (userList.get(section).getContents().get(position).isFinish()) {
                        ((JobsItemChildByUserViewHolder) holder).jobsContentStatus.setTextColor(ContextCompat.getColor(context, R.color.app_base_text_status_green));
                        ((JobsItemChildByUserViewHolder) holder).jobsContentStatus.setText("已完成");
                    } else {
                        ((JobsItemChildByUserViewHolder) holder).jobsContentStatus.setTextColor(ContextCompat.getColor(context, R.color.ui_btn_background_red));
                        ((JobsItemChildByUserViewHolder) holder).jobsContentStatus.setText("待完成");
                    }
                    String strContentSub = "";
                    for (CommonJobsListUserBean.JobsContent content : userList.get(section).getContents().get(position).getContent()) {
                        strContentSub += TextUtils.isEmpty(content.getResName()) ? "" : (content.getResName() + "    ");//资源名称
                        strContentSub += (TextUtils.isEmpty(content.getNorm()) ? "" : content.getNorm()) + "\n";//配比
                    }
                    if (TextUtils.isEmpty(userList.get(section).getContents().get(position).getDescript())) {
                        strContentSub = strContentSub.substring(0, strContentSub.length() - 1);
                    } else {
                        strContentSub += userList.get(section).getContents().get(position).getDescript();
                    }
                    ((JobsItemChildByUserViewHolder) holder).jobsContentSub.setText(strContentSub);

                    ((JobsItemChildByUserViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onItemInfoClickListener != null)
                                onItemInfoClickListener.onClick(v, userList.get(section).getContents().get(position).getSchID(), userList.get(section).getContents().get(position).getUnitID(),
                                        userList.get(section).getJobName(), userList.get(section).getContents().get(position).isFinish());
                        }
                    });
                    ((JobsItemChildByUserViewHolder) holder).itemView.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            return true;
                        }
                    });
                }
                break;
            default:
                break;
        }

    }

    /**
     * 点击Item精准定位到具体头部位置
     */
    public interface OnItemScrollToPositionWithOffset {
        void scrollToPositionWithOffset(int position);
    }

    public interface OnItemInfoClickListener {
        void onClick(View view, String schID, String unitID, String JobName, boolean isFinish);
    }

//    private class ImageAdapter extends NineGridImageViewAdapter<CommonJobsListManagerBean.RecordsBean> {
//        private int section;
//        private int position;
//
//        public ImageAdapter(int section, int position) {
//            this.section = section;
//            this.position = position;
//        }
//
//        @Override
//        protected void onDisplayImage(Context mContext, ImageView imageView, CommonJobsListManagerBean.RecordsBean s) {
//            //加载
////            imageView.setTag(s);
//            Glide.with(context.getApplicationContext())
//                    .load(TextUtils.isEmpty(s.getImgPath()) ? "" : (AgrConstant.IMAGE_URL_IMAGE + s.getImgPath()))
//                    .apply(AgrConstant.iconOptions)
//                    .into(imageView);
//        }
//
//        @Override
//        protected ImageView generateImageView(Context mContext) {
//            return super.generateImageView(context);
//        }
//
//        @Override
//        protected void onItemImageClick(Context mContext, ImageView imageView, int index, List<CommonJobsListManagerBean.RecordsBean> list) {
////            super.onItemImageClick(context, imageView, index, list);
//            //点击事件
////            ErayicToast.TextToast(context, list.get(index));
////            PictureConfig.getInstance().externalPicturePreview((Activity) context, "/Erayic/image", index, list);
////            PictureSelector.create((Activity) context).externalPicturePreview(index, "/Erayic/image", list);
//            if (onItemInfoClickListener != null)
//                onItemInfoClickListener.onClick(imageView, managerList.get(section).getJobs().get(position).getSchID(), managerList.get(section).getJobs().get(position).getUnitID(),
//                        managerList.get(section).getJobName(), managerList.get(section).getJobs().get(position).isFinish());
//        }
//    }


}
