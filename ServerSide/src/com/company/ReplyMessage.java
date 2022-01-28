package com.company;

import com.company.TimeStamp;

import java.io.Serializable;

public class ReplyMessage<T> implements Serializable {
    T data;
    TimeStamp timeStamp;

    public ReplyMessage(T data, TimeStamp timeStamp) {
        this.data = data;
        this.timeStamp = timeStamp;
    }

    public T getData() {
        return data;
    }

    public TimeStamp getTimeStamp() {
        return timeStamp;
    }
}