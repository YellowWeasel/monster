package com.erayic.agr.common.view.intro;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.erayic.agr.common.R;
import com.erayic.agr.common.base.BaseFragment;
import com.github.paolorotolo.appintro.ISlideBackgroundColorHolder;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

@Route(path = "/common/fragment/AgrIntroFragment", name = "引导页")
public class AgrIntroFragment extends BaseFragment implements ISlideBackgroundColorHolder {

    private RelativeLayout intro_layout;
    private ImageView intro_img;

    @Autowired
    int position;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_common_intro;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        intro_img = (ImageView) view.findViewById(R.id.intro_img);
        intro_layout = (RelativeLayout) view.findViewById(R.id.intro_layout);
    }

    @Override
    protected void initData() {
        switch (position) {
            case 0:
                intro_img.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.app_base_plant_intro_1));
                break;
            case 1:
                intro_img.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.app_base_plant_intro_2));
                break;
            case 2:
                intro_img.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.app_base_plant_intro_3));
                break;
            case 3:
                intro_img.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.app_base_plant_intro_4));
                break;
            default:
                intro_img.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.app_base_plant_intro_1));
                break;
        }
        setBackgroundColor(getDefaultBackgroundColor());
    }

    @Override
    public int getDefaultBackgroundColor() {
        switch (position) {
            case 0:
                return Color.parseColor("#dba248");
            case 1:
                return Color.parseColor("#0f95ac");
            case 2:
                return Color.parseColor("#2b83cd");
            case 3:
                return Color.parseColor("#2fad6d");
            default:
                return Color.parseColor("#000000");//返回幻灯片的默认背景颜色（即幻灯片在非滑动状态下的背景颜色）
        }

    }

    @Override
    public void setBackgroundColor(@ColorInt int backgroundColor) {
        if (intro_layout != null) {
            intro_layout.setBackgroundColor(backgroundColor);
        }
    }
}
