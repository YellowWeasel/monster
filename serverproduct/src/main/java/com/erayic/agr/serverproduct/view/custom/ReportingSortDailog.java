package com.erayic.agr.serverproduct.view.custom;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;

/**
 * Created by wxk on 2017/5/10.
 */

public class ReportingSortDailog extends Dialog{

    public ReportingSortDailog(@NonNull Context context) {
        super(context);
    }

    public ReportingSortDailog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    protected ReportingSortDailog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public ReportingSortDailog InitDialog(){

        return this;
    }

}
