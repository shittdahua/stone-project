package com.stone.stoneproject.base;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@ToString
@Slf4j
public abstract class Base implements Serializable {
    private static final long serialVersionUID = 3801808290713296746L;

    protected static void error(Throwable throwable) {
        log.error(StringUtils.EMPTY,throwable);
    }
}
