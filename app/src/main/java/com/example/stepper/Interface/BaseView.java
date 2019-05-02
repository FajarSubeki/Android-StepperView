package com.example.stepper.Interface;

public interface BaseView {
    void onShow();
    void onHide();
    void onError(String error);
    void getMessage(String message);
    void getHttp(String http);
}
