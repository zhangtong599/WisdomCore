package com.javacore.wisdom.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlitedbUtility {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private final String dbFilePath="test.db";

    /**
     * 构造函数
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public SqlitedbUtility() throws ClassNotFoundException, SQLException {
        connection = getConnection(this.dbFilePath);
    }

    /**
     * 获取数据库连接
     * @param dbFilePath db文件路径
     * @return 数据库连接
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Connection getConnection(String dbFilePath) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:" + dbFilePath);
        return conn;
    }

    /**
     * 执行sql查询
     * @param sql sql select 语句
     * @param rse 结果集处理类对象
     * @return 查询结果
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public <T> T executeQuery(String sql, ResultSetExtractor<T> rse) throws SQLException, ClassNotFoundException {
        resultSet = getStatement().executeQuery(sql);
        T rs = rse.extractData(resultSet);
        return rs;
    }

    /**
     * 执行select查询，返回结果列表
     *
     * @param sql sql select 语句
     * @param rm 结果集的行数据处理类对象
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public <T> List<T> executeQuery(String sql, RowMapper<T> rm) throws SQLException, ClassNotFoundException {
        List<T> rsList = new ArrayList<T>();
        resultSet = getStatement().executeQuery(sql);
        while (resultSet.next()) {
            rsList.add(rm.mapRow(resultSet, resultSet.getRow()));
        }
        return rsList;
    }

    /**
     * 执行数据库更新sql语句
     * @param sql
     * @return 更新行数
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int executeUpdate(String sql) throws SQLException, ClassNotFoundException {
        int c = getStatement().executeUpdate(sql);
        return c;

    }

    /**
     * 执行多个sql更新语句
     * @param sqls
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void executeUpdate(String...sqls) throws SQLException, ClassNotFoundException {
        for (String sql : sqls) {
            getStatement().executeUpdate(sql);
        }
    }

    /**
     * 执行数据库更新 sql List
     * @param sqls sql列表
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void executeUpdate(List<String> sqls) throws SQLException, ClassNotFoundException {
        for (String sql : sqls) {
            getStatement().executeUpdate(sql);
        }
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        if (null == connection) connection = getConnection(dbFilePath);
        return connection;
    }

    private Statement getStatement() throws SQLException, ClassNotFoundException {
        if (null == statement) statement = getConnection().createStatement();
        return statement;
    }

    /**
     * 数据库资源关闭和释放
     */
    public void destroyed() {
        try {
            if (null != connection) {
                connection.close();
                connection = null;
            }

            if (null != statement) {
                statement.close();
                statement = null;
            }

            if (null != resultSet) {
                resultSet.close();
                resultSet = null;
            }
        } catch (SQLException e) {
            System.out.println("Sqlite数据库关闭时异常:"+e);
        }
    }


    /*public static void main(String[] args){
        try{
            sqlliteUtility sqllite=new sqlliteUtility();
            //删除表
            sqllite.executeUpdate("drop table if exists test;");
           String sql = "CREATE TABLE TEST " +
                    "(NAME           AVRCHAR(20)    NOT NULL, " +
                    " AGE            INT     NOT NULL)";
            //创建表
            sqllite.executeUpdate(sql);
            //插入单数据
             String insertsql="INSERT INTO test (NAME,AGE) VALUES ('Paul', 32);";
            sqllite.executeUpdate(insertsql);
            User u=sqllite.executeQuery("select * from test",new ResultSetExtractor<User>(){

                @Override
                public User extractData(ResultSet rs) {
                    User us=new User();
                    try {
                        us.setName(rs.getString("name"));
                        us.setAge(rs.getInt("age"));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    return us;
                }
            });
            System.out.println(u.toString());
            sqllite.destroyed();*//*

            //插入多数据
            List<String> l=new ArrayList<>();
            l.add("INSERT INTO test (NAME,AGE) VALUES ('Paul', 32);");
            l.add("INSERT INTO test (NAME,AGE) VALUES ('Yunmin', 29);");
            l.add("INSERT INTO test (NAME,AGE) VALUES ('Zhangtong', 26);");
            sqllite.executeUpdate(l);
            List<User> ls=sqllite.executeQuery("select * from test", new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet rs, int index) throws SQLException {
                    User u=new User();
                    u.setName(rs.getString("name"));
                    u.setAge(rs.getInt("age"));
                    return u;
                }
            });
            for(User u: ls){
                System.out.println(u.toString());
            }
            sqllite.destroyed();*//*
        }catch (Exception e){
            System.out.println(e);
        }


    }*/
}

//单数据
interface ResultSetExtractor<T> {

    public abstract T extractData(ResultSet rs);

}

//列数据
interface RowMapper<T> {
    public abstract T mapRow(ResultSet rs, int index) throws SQLException;
}

