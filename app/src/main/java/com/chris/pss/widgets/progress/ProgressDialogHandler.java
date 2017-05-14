package com.chris.pss.widgets.progress;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.indicators.LineSpinFadeLoaderIndicator;


/**
 * Created by liukun on 16/3/10.
 */
public class ProgressDialogHandler extends Handler {

    public static final int SHOW_PROGRESS_DIALOG = 1;
    public static final int DISMISS_PROGRESS_DIALOG = 2;

    private Dialog mProgressDialog;

    private Context context;
    private boolean cancelable;
    private ProgressCancelListener mProgressCancelListener;

    public ProgressDialogHandler(Context context, ProgressCancelListener mProgressCancelListener,
                                 boolean cancelable) {
        super();
        this.context = context;
        this.mProgressCancelListener = mProgressCancelListener;
        this.cancelable = cancelable;
    }

    public ProgressDialogHandler(Context context,
                                 boolean cancelable) {
        super();
        this.context = context;
        this.cancelable = cancelable;
    }

    private void initProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new Dialog(context);
            mProgressDialog.setCancelable(cancelable);
            mProgressDialog.setCanceledOnTouchOutside(false);

            Window window = mProgressDialog.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            window.setBackgroundDrawable(new ColorDrawable());

            AVLoadingIndicatorView loadingView = new AVLoadingIndicatorView(context);
            loadingView.setIndicator(new LineSpinFadeLoaderIndicator());
            mProgressDialog.setContentView(loadingView);
            loadingView.show();

            if (cancelable && mProgressCancelListener != null) {
                mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        mProgressCancelListener.onCancelProgress();
                    }
                });
            }


        }
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    private void dismissProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case SHOW_PROGRESS_DIALOG:
                initProgressDialog();
                break;
            case DISMISS_PROGRESS_DIALOG:
                dismissProgressDialog();
                break;
        }
    }

}
