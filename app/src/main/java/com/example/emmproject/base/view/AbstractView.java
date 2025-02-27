package com.example.emmproject.base.view;

import com.kongzue.dialog.v3.WaitDialog;

public interface AbstractView {

    void showToast(String message);

    /**
     * Show snackBar
     *
     * @param message Message
     */
    void showSnackBar(String message);

    void showErrorMessage(String message);
    /**
     * showNormal
     */
    void showNormal();

    /**
     * Show error
     */
    void showError();

    /**
     * Show loading
     */

    public  void  showWaiting();

    public void cancelWaiting();
}
