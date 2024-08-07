package com.github.ma_vin.util.layer_generator.sources;

import lombok.Getter;

@Getter
public enum Qualifier {
    PRIVATE("private"), PROTECTED("protected"), PUBLIC("public"), DEFAULT("");
    private String text;

    Qualifier(String text) {
        this.text = text;
    }
}
