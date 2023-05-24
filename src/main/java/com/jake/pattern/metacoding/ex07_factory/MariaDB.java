package com.jake.pattern.metacoding.ex07_factory;

public class MariaDB implements DB {
    private String url;

    // 쿼리 실행 메소드
    public int execute(String sql) {
        if (url == null) {
            throw new NullPointerException("db driver not found exception");
        }

        if (sql.equals("select")) {
            System.out.println("query execute : " + url + sql);
            return 1;
        } else {
            System.out.println("query fail : syntax error");
            return -1;
        }
    }

    // url 세팅 메소드
    public void setUrl(String url) {
        this.url = url;
    }
}
