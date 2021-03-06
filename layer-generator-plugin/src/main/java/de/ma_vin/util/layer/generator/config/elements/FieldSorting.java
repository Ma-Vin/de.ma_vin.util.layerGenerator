package de.ma_vin.util.layer.generator.config.elements;


import lombok.Data;

import javax.xml.bind.annotation.XmlTransient;

/**
 * Class to combine a field with som sorting direction for {@link Index}
 * <br>
 * SuppressWarnings("java:S1068"): getter and Setter are provided by lombok
 */
@XmlTransient
@Data
@SuppressWarnings("java:S1068")
public class FieldSorting {
    private Field field;
    private boolean isAscending = true;
}
