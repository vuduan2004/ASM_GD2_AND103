package com.example.ass_and_rest_api.model;

public class ResponseModel<T>{
    private int status;
    private String messenger;
    //T là kiểu Generic
    private T data;

    public ResponseModel() {
    }

    public ResponseModel(int status, String messenger, T data) {
        this.status = status;
        this.messenger = messenger;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessenger() {
        return messenger;
    }

    public void setMessenger(String messenger) {
        this.messenger = messenger;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
