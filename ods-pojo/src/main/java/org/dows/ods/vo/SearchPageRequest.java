package org.dows.ods.vo;

import lombok.Data;

@Data
public abstract class SearchPageRequest {
    /*页号默认值*/
    public static final int PAGE_NO_DEFAULT = 1;
    /*页大小默认值*/
    public static final int PAGE_SIZE_DEFAULT = 10;
    private int pageNo;
    private int pageSize;
}
