package com.tiz.learntobank.exception;

import com.tiz.learntobank.untils.Constants;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonException extends NumberFormatException{
    private Constants.CommonException commonException;

    public CommonException(Constants.CommonException commonException) {
        this.commonException = commonException;
    }
}
