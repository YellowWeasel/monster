package com.erayic.agr.common.view;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.erayic.agr.common.R;
import com.erayic.agr.common.util.ErayicDensity;

/**
 * 作者：Hkceey
 * 邮箱：hkceey@outlook.com
 * 注解：对话框
 */

public class ErayicDialog extends Dialog {

    private Context context;

    public static final int VIEW_STYLE_NORMAL = 0x00000001;
    public static final int VIEW_STYLE_TITLEBAR = 0x00000002;
    public static final int VIEW_STYLE_TITLEBAR_SKYBLUE = 0x00000003;
    public static final int BUTTON_COUNT_ZERO = 0x00000000;
    public static final int BUTTON_COUNT_ONE = 0x00000001;
    public static final int BUTTON_COUNT_TWO = 0x00000002;
    public static final int BUTTON_COUNT_THREE = 0x00000003;

    public static final int BUTTON_1 = 0x00000001;
    public static final int BUTTON_2 = 0x00000002;
    public static final int BUTTON_3 = 0x00000003;

    protected ErayicDialog(Context context, int theme) {
        super(context, theme);
        this.context = context;
    }

    protected ErayicDialog(Context context) {
        this(context, R.style.ErayicDialogStyle);
    }

    protected ErayicDialog(Context context, boolean cancelableOnTouchOutside) {
        this(context);
        this.setCanceledOnTouchOutside(cancelableOnTouchOutside);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = this.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        int marginBorder = ErayicDensity.dp2px(context, 30);
        params.width = getScreenWidth(context) - marginBorder * 2;
        window.setAttributes(params);
    }

    @SuppressLint({"NewApi", "InflateParams"})
    public static class Builder {

        private ErayicDialog dialog;
        private Context context;

        private CharSequence title;
        private CharSequence message;
        private CharSequence message2;
        private CharSequence button1Text;
        private CharSequence button2Text;
        private CharSequence button3Text;
        private int button1TextColor;
        private int button2TextColor;
        private int button3TextColor;
        private int titleColor;
        private int messageColor;
        private float button1Size;
        private float button2Size;
        private float button3Size;
        private float titleSize;
        private float messageSize;
        private ColorStateList titleColorStateList;
        private ColorStateList messageColorStateList;
        private ColorStateList button1ColorStateList;
        private ColorStateList button2ColorStateList;
        private ColorStateList button3ColorStateList;
        private int titlebarGravity;

        private Drawable icon;
        private boolean cancelable = true;
        private boolean canceledOnTouchOutside;
        private View view;
        private ErayicDialog.OnClickListener button1Listener;
        private ErayicDialog.OnClickListener button2Listener;
        private ErayicDialog.OnClickListener button3Listener;
        private ErayicDialog.OnLinearClickListener linearListener;
        private int button1Flag;
        private int button2Flag;
        private int button3Flag;

        public Builder(Context context, int theme) {
            dialog = new ErayicDialog(context, theme);
            this.context = context;
            initData();
        }

        public Builder(Context context) {
            dialog = new ErayicDialog(context);
            this.context = context;
            initData();
        }

        private void initData() {
            this.button1TextColor = Color.parseColor("#808080");
            this.button2TextColor = Color.parseColor("#808080");
            this.button3TextColor = Color.parseColor("#808080");
            this.messageColor = Color.parseColor("#696969");
            this.titleColor = Color.BLACK;

            this.button1Size = 16;
            this.button2Size = 16;
            this.button3Size = 16;
            this.messageSize = 15;
            this.titleSize = 18;

            this.titlebarGravity = Gravity.CENTER;
        }

        public Context getContext() {
            return context;
        }

        public Builder setTitleBarGravity(int titlebarGravity) {
            this.titlebarGravity = titlebarGravity;
            return this;
        }

        public Builder setTitle(CharSequence title) {
            this.title = title;
            return this;
        }

        public Builder setTitle(int titleResId) {
            this.title = context.getResources().getString(titleResId);
            return this;
        }

        public Builder setTitleColor(int titleColor) {
            this.titleColor = titleColor;
            return this;
        }

        public Builder setTitleColor(ColorStateList titleColor) {
            this.titleColorStateList = titleColor;
            return this;
        }

        public Builder setTitleSize(float titleSize) {
            this.titleSize = titleSize;
            return this;
        }

        public Builder setIcon(Drawable icon) {
            this.icon = icon;
            return this;
        }

        public Builder setIcon(int iconResId) {
            this.icon = context.getResources().getDrawable(iconResId);
            return this;
        }

        public Builder setMessage(CharSequence message, ErayicDialog.OnLinearClickListener listener) {
            this.message = message;
            this.linearListener = listener;
            return this;
        }

        public Builder setMessage(int messageResId, ErayicDialog.OnLinearClickListener listener) {
            this.message = context.getResources().getString(messageResId);
            this.linearListener = listener;
            return this;
        }

        public Builder setMessage2(CharSequence message, ErayicDialog.OnLinearClickListener listener) {
            this.message2 = message;
            this.linearListener = listener;
            return this;
        }

        public Builder setMessage2(int messageResId, ErayicDialog.OnLinearClickListener listener) {
            this.message2 = context.getResources().getString(messageResId);
            this.linearListener = listener;
            return this;
        }

        public Builder setMessageColor(int color) {
            this.messageColor = color;
            return this;
        }

        public Builder setMessageColor(ColorStateList color) {
            this.messageColorStateList = color;
            return this;
        }

        public Builder setMessageSize(float size) {
            this.messageSize = size;
            return this;
        }

        public Builder setButton1(CharSequence text,
                                  ErayicDialog.OnClickListener listener) {
            this.button1Text = text;
            this.button1Listener = listener;
            button1Flag = 1;
            return this;
        }

        public Builder setButton1(int textId,
                                  ErayicDialog.OnClickListener listener) {
            this.button1Text = context.getResources().getString(textId);
            this.button1Listener = listener;
            button1Flag = 1;
            return this;
        }

        public Builder setButton1TextColor(int color) {
            this.button1TextColor = color;
            return this;
        }

        public Builder setButton1TextColor(ColorStateList color) {
            this.button1ColorStateList = color;
            return this;
        }

        public Builder setButton1Size(float button1Size) {
            this.button1Size = button1Size;
            return this;
        }

        public Builder setButton2(CharSequence text,
                                  ErayicDialog.OnClickListener listener) {
            this.button2Text = text;
            this.button2Listener = listener;
            button2Flag = 2;
            return this;
        }

        public Builder setButton2(int textId,
                                  ErayicDialog.OnClickListener listener) {
            this.button2Text = context.getResources().getString(textId);
            this.button2Listener = listener;
            button2Flag = 2;
            return this;
        }

        public Builder setButton2TextColor(int color) {
            this.button2TextColor = color;
            return this;
        }

        public Builder setButton2TextColor(ColorStateList color) {
            this.button2ColorStateList = color;
            return this;
        }

        public Builder setButton2Size(float button2Size) {
            this.button2Size = button2Size;
            return this;
        }

        public Builder setButton3(CharSequence text,
                                  ErayicDialog.OnClickListener listener) {
            this.button3Text = text;
            this.button3Listener = listener;
            button3Flag = 4;
            return this;
        }

        public Builder setButton3(int textId,
                                  ErayicDialog.OnClickListener listener) {
            this.button3Text = context.getResources().getString(textId);
            this.button3Listener = listener;
            button3Flag = 4;
            return this;
        }

        public Builder setButton3TextColor(int color) {
            this.button3TextColor = color;
            return this;
        }

        public Builder setButton3TextColor(ColorStateList color) {
            this.button3ColorStateList = color;
            return this;
        }

        public Builder setButton3Size(float button3Size) {
            this.button3Size = button3Size;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public Builder setCanceledOnTouchOutside(boolean canceled) {
            this.canceledOnTouchOutside = canceled;
            return this;
        }

        public Builder setView(View view) {
            this.view = view;
            return this;
        }

        @SuppressLint("InflateParams")
        public ErayicDialog create() {

            if (dialog == null) {
                return null;
            }
            this.titleColor = Color.WHITE;
            this.titlebarGravity = Gravity.LEFT;
            View mView = null;
            LinearLayout mTitleBar = null;
            TextView mTitle = null;
            TextView mMessage = null;
            TextView mMessage2 = null;
            TextView btnLeft = null;
            TextView btnCenter = null;
            TextView btnRight = null;
            LinearLayout addView = null;
            LinearLayout addView2 = null;
            LinearLayout btnView = null;
            View btnDivider1 = null;
            View btnDivider2 = null;
            View msgBtnDivider = null;
            View title_msg_divider2;
            mView = LayoutInflater.from(context).inflate(
                    R.layout.hkceey_dialog_titlebar, null);
            mTitleBar = (LinearLayout) mView.findViewById(R.id.titlebar);
            mTitle = (TextView) mView.findViewById(R.id.title);
            mMessage = (TextView) mView.findViewById(R.id.message);
            mMessage2 = (TextView) mView.findViewById(R.id.message2);
            addView = (LinearLayout) mView.findViewById(R.id.layout_addview);
            addView2 = (LinearLayout) mView.findViewById(R.id.layout_addview2);
            addView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    // TODO Auto-generated method stub
                    if (linearListener != null) {
                        linearListener.onClick(0);
                    }
                }
            });
            addView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    // TODO Auto-generated method stub
                    if (linearListener != null) {
                        linearListener.onClick(1);
                    }
                }
            });
            btnLeft = (TextView) mView.findViewById(R.id.button_left);
            btnCenter = (TextView) mView.findViewById(R.id.button_center);
            btnRight = (TextView) mView.findViewById(R.id.button_right);
            btnDivider1 = (View) mView.findViewById(R.id.btn_divider1);
            btnDivider2 = (View) mView.findViewById(R.id.btn_divider2);
            msgBtnDivider = (View) mView.findViewById(R.id.msg_btn_divider);
            btnView = (LinearLayout) mView.findViewById(R.id.btn_view);
            title_msg_divider2 = (View) mView
                    .findViewById(R.id.title_msg_divider2);
            if ((title != null) || (icon != null)) {
                mTitle.setVisibility(View.VISIBLE);
                mTitle.setText(title);
                mTitle.setTextSize(titleSize);
                mTitle.setTextColor(titleColor);
                if (titleColorStateList != null) {
                    mTitle.setTextColor(titleColorStateList);
                }
                mTitle.setCompoundDrawables(icon, null, null, null);
                mTitleBar.setGravity(titlebarGravity);
            } else {
                mTitle.setVisibility(View.GONE);
            }

            if (message != null) {
                mMessage.setVisibility(View.VISIBLE);
                mMessage.setText(message);
                mMessage.setTextSize(messageSize);
                mMessage.setTextColor(messageColor);
                if (messageColorStateList != null) {
                    mMessage.setTextColor(messageColorStateList);
                }
            } else {
                mMessage.setVisibility(View.GONE);
            }
            if (message2 != null) {
                title_msg_divider2.setVisibility(View.VISIBLE);
                addView2.setVisibility(View.VISIBLE);
                mMessage2.setVisibility(View.VISIBLE);
                mMessage2.setText(message2);
                mMessage2.setTextSize(messageSize);
                mMessage2.setTextColor(messageColor);
                if (messageColorStateList != null) {
                    mMessage2.setTextColor(messageColorStateList);
                }
            } else {
                title_msg_divider2.setVisibility(View.GONE);
                addView2.setVisibility(View.GONE);
                mMessage2.setVisibility(View.GONE);
            }
            if (view != null) {
                addView.removeAllViews();
                addView.addView(view);
                addView.setGravity(Gravity.CENTER);
            }
            if (view != null) {
                addView2.removeAllViews();
                addView2.addView(view);
                addView2.setGravity(Gravity.CENTER);
            }
            int btnCountFlag = button1Flag + button2Flag + button3Flag;
            switch (btnCountFlag) {
                // one button
                case 1:
                case 5:
                    btnCenter.setVisibility(View.VISIBLE);
                    btnLeft.setVisibility(View.GONE);
                    btnRight.setVisibility(View.GONE);
                    btnCenter
                            .setBackgroundResource(R.drawable.erayic_dialog_btn_single_selector);
                    if (button1Text != null) {
                        btnCenter.setText(button1Text);
                        btnCenter.setTextSize(button1Size);
                        btnCenter.setTextColor(button1TextColor);
                        if (button1ColorStateList != null) {
                            btnCenter.setTextColor(button1ColorStateList);
                        }
                        if (button1Listener != null) {
                            btnCenter
                                    .setOnClickListener(new View.OnClickListener() {

                                        @Override
                                        public void onClick(View arg0) {
                                            button1Listener.onClick(dialog,
                                                    BUTTON_1);
                                        }
                                    });
                        }
                    }
                    break;

                case 3:
                    // two button
                    btnLeft.setVisibility(View.VISIBLE);
                    btnRight.setVisibility(View.VISIBLE);
                    btnCenter.setVisibility(View.GONE);
                    btnDivider1.setVisibility(View.VISIBLE);
                    btnDivider2.setVisibility(View.GONE);

                    if (button1Text != null) {
                        btnLeft.setText(button1Text);
                        btnLeft.setTextSize(button1Size);
                        btnLeft.setTextColor(button1TextColor);

                        if (button1ColorStateList != null) {
                            btnLeft.setTextColor(button1ColorStateList);
                        }

                        if (button1Listener != null) {
                            btnLeft.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View arg0) {
                                    button1Listener.onClick(dialog, BUTTON_1);
                                }
                            });
                        }
                    }

                    if (button2Text != null) {
                        btnRight.setText(button2Text);
                        btnRight.setTextSize(button2Size);
                        btnRight.setTextColor(button2TextColor);

                        if (button2ColorStateList != null) {
                            btnRight.setTextColor(button2ColorStateList);
                        }

                        if (button2Listener != null) {
                            btnRight.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View arg0) {
                                    button2Listener.onClick(dialog, BUTTON_2);
                                }
                            });
                        }
                    }
                    break;
                case 7:
                    // three button
                    btnLeft.setVisibility(View.VISIBLE);
                    btnCenter.setVisibility(View.VISIBLE);
                    btnRight.setVisibility(View.VISIBLE);
                    btnDivider1.setVisibility(View.VISIBLE);
                    btnDivider2.setVisibility(View.VISIBLE);

                    if (button1Text != null) {
                        btnLeft.setText(button1Text);
                        btnLeft.setTextSize(button1Size);
                        btnLeft.setTextColor(button1TextColor);

                        if (button1ColorStateList != null) {
                            btnLeft.setTextColor(button1ColorStateList);
                        }

                        if (button1Listener != null) {
                            btnLeft.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View arg0) {
                                    button1Listener.onClick(dialog, BUTTON_1);
                                }
                            });
                        }
                    }
                    if (button2Text != null) {
                        btnCenter.setText(button2Text);
                        btnCenter.setText(button2Text);
                        btnCenter.setTextSize(button2Size);
                        btnCenter.setTextColor(button2TextColor);

                        if (button2ColorStateList != null) {
                            btnCenter.setTextColor(button2ColorStateList);
                        }

                        if (button2Listener != null) {
                            btnCenter
                                    .setOnClickListener(new View.OnClickListener() {

                                        @Override
                                        public void onClick(View arg0) {
                                            button2Listener.onClick(dialog,
                                                    BUTTON_2);
                                        }
                                    });
                        }
                    }

                    if (button3Text != null) {
                        btnRight.setText(button3Text);
                        btnRight.setTextSize(button3Size);
                        btnRight.setTextColor(button3TextColor);

                        if (button3ColorStateList != null) {
                            btnRight.setTextColor(button3ColorStateList);
                        }

                        if (button3Listener != null) {
                            btnRight.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View arg0) {
                                    button3Listener.onClick(dialog, BUTTON_3);
                                }
                            });
                        }
                    }
                    break;

                default:
                    btnView.setVisibility(View.GONE);
                    msgBtnDivider.setVisibility(View.GONE);
                    break;
            }

            dialog.setCancelable(cancelable);
            dialog.setCanceledOnTouchOutside(canceledOnTouchOutside);

            dialog.setContentView(mView);
            return dialog;
        }

        public ErayicDialog show() {
            create().show();
            return dialog;
        }
    }

    public interface OnClickListener {
        void onClick(Dialog dialog, int which);
    }

    public interface OnLinearClickListener {
        void onClick(int which);
    }

    /**
     * 获得屏幕高度
     */
    private static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }
}

