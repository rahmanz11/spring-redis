package com.demo.redisoperation.model;

import java.io.Serializable;
import java.util.List;

public class Values implements Serializable {
    List<String> values;

    public Values() {
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }
}
