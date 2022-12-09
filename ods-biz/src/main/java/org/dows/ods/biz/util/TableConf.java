package org.dows.ods.biz.util;

/*解析时使用的表配置名称*/
public enum TableConf {
    PRIMARY_KEY("primary_key"),
    TABLE("table"),
    FIELD("field"),
    ;
    private String name;

    TableConf(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
