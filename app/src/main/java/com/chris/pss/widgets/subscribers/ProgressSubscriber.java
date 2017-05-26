package com.chris.pss.widgets.subscribers;

import android.content.Context;

import com.chris.pss.data.entity.BaseResponse;
import com.chris.pss.data.interceptor.ApiException;
import com.chris.pss.widgets.progress.ProgressCancelListener;
import com.chris.pss.widgets.progress.ProgressDialogHandler;

import rx.Subscriber;

/**
 * 用于在Http请求开始时，自动显示一个ProgressDialog
 * 在Http请求结束是，关闭ProgressDialog
 * 调用者自己对请求数据进行处理
 */
public class ProgressSubscriber<T> extends Subscriber<T> implements ProgressCancelListener {

    private GeneralSubscriber<T> mGeneralSubscriber;
    private ProgressDialogHandler mProgressDialogHandler;

    private Context context;

    public ProgressSubscriber(GeneralSubscriber<T> mGeneralSubscriber, Context context) {
        this.mGeneralSubscriber = mGeneralSubscriber;
        this.context = context;
        mProgressDialogHandler = new ProgressDialogHandler(context, this, true);
    }

    private void showProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    private void dismissProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            mProgressDialogHandler = null;
        }
    }

    /**
     * 订阅开始时调用
     * 显示ProgressDialog
     */
    @Override
    public void onStart() {
        showProgressDialog();
    }

    /**
     * 完成，隐藏ProgressDialog
     */
    @Override
    public void onCompleted() {
        dismissProgressDialog();
    }

    /**
     * 对错误进行统一处理
     * 隐藏ProgressDialog
     *
     * @param e
     */
    @Override
    public void onError(Throwable e) {
//        if (e instanceof SocketTimeoutException) {
//            ToastUtils.showToast("网络中断，请检查您的网络状态");
//        } else if (e instanceof ConnectException) {
//            ToastUtils.showToast("网络中断，请检查您的网络状态");
//        } else if (e instanceof NetworkConnectionException) {
//            ToastUtils.showToast("网络不可用，请检查您的网络状态");
//        } else {
//            ToastUtils.showToast("未知错误");
//            LogUtils.e(e);
//        }
        if (mGeneralSubscriber != null) {
            mGeneralSubscriber.onError(e);
        }
        dismissProgressDialog();
    }

    /**
     * 将onNext方法中的返回结果交给Activity或Fragment自己处理
     *
     * @param t 创建Subscriber时的泛型类型
     */
    @Override
    public void onNext(T t) {
        if (mGeneralSubscriber != null) {
            if (t == null) {
                mGeneralSubscriber.onError(new NullPointerException("返回数据异常"));
                return;
            }
            if (t instanceof BaseResponse) {
                BaseResponse response = (BaseResponse) t;
                if (response.getCode() != 200) {
                    mGeneralSubscriber.onError(new ApiException(response.getMsg(), response.getCode()));
                    return;
                }
            }
            mGeneralSubscriber.onNext(t);
        }
    }

    /**
     * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
     */
    @Override
    public void onCancelProgress() {
        dismissProgressDialog();
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
    }
}