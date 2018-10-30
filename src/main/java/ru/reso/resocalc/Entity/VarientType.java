package ru.reso.resocalc.Entity;

import java.sql.Types;

public class VarientType {

    private Integer BIGINTVALUE;
    private Types type;
    private Boolean found;


    public Integer getBIGINTVALUE() {
        return BIGINTVALUE;
    }

    public void setBIGINTVALUE(Integer BIGINTVALUE) {
        this.BIGINTVALUE = BIGINTVALUE;
    }

    public Types getType() {
        return type;
    }

    public void setType(Types type) {
        this.type = type;
    }
}
