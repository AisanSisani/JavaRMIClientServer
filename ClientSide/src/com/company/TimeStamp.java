package com.company;

import java.io.Serializable;

public class TimeStamp implements Serializable {
    private Integer time;
    TimeStamp(Integer value){
        time = value;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public void localIncrease(){
        time++;
    }

    public void adapt(TimeStamp senderTimeStamp){
        if(time< senderTimeStamp.getTime())
            time= senderTimeStamp.getTime();
    }
}
