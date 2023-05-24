package com.jake.pattern.metacoding.ex07_factory;

// 팩토리 패턴 - 인터페이스 구현 순서를 지켜야 될 때 도입
public class App {
    public static void main(String[] args) {
        // DIP 원칙 위배
//        MariaDB mariaDB = new MariaDB();
//        DB mariaDB = new MariaDB();
//        mariaDB.setUrl("jdbc:mariadb://");
//        mariaDB.execute("select");

//        OracleDB oracleDB = new OracleDB();
//        DB oracleDB = new OracleDB();
//        oracleDB.setUrl("jdbc:oracle:thin://");
//        oracleDB.execute("select");

//        DBFactory dbFactory = new DBFactory();
        // 싱글톤 시작
        DBFactory dbFactory = DBFactory.getInstance();

        // 내가 MariaDB 객체를 알 필요가 없고
        // setUrl 부터 해야 할지, execute 부터 해야할 지 생각할 필요가 없다.
        // 문자열만 추가해서 DB를 생성하면 됨 (내가 의존해야할 것은 DBFactory)
        DB maria = dbFactory.createDB("maria");
        maria.execute("select");

        DB mysql = dbFactory.createDB("mysql");
        mysql.execute("select");
    }
}
