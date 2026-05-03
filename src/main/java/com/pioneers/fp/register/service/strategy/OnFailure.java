package com.pioneers.fp.register.service.strategy;

@FunctionalInterface
public interface OnFailure {
    void fail(String message);
}
