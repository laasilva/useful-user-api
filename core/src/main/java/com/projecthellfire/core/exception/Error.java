package com.projecthellfire.core.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Error implements Serializable {
    private static final long serialVersionUID = 1905122041950251207L;

    private String code;
    private String message;
}
