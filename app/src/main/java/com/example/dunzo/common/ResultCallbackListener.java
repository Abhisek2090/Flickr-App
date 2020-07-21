package com.example.dunzo.common;

public interface ResultCallbackListener<R, E> {
    void onSuccess(R response);

    void onFailure();
}
