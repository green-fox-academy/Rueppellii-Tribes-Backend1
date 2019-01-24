package com.greenfox.tribes1.Exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ErrorMsg {
    private String status;
    private String message;

    public ErrorMsg(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
