package com.github.ma_vin.util.layer_generator.sources;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Parameter implements IComparableWithText<Parameter> {
    private String parameterType;
    private String parameterName;

    public String getText() {
        return String.format("%s %s", parameterType, parameterName);
    }

    @Override
    @SuppressWarnings("java:S1210")
    public int compareTo(Parameter o) {
        int res = parameterName.compareTo(o.parameterName);
        if (res == 0) {
            res = parameterType.compareTo(o.parameterType);
        }
        return res;
    }
}
