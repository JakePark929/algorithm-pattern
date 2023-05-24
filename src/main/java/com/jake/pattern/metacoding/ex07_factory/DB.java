package com.jake.pattern.metacoding.ex07_factory;

public interface DB {
    int execute(String sql);
    void setUrl(String url);
}
