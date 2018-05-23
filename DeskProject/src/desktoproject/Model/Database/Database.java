/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktoproject.Model.Database;

import desktoproject.Utils.Pairs.QueryResult;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author gabriel
 */
public class Database {

    private Configs config;
    private final Connection connection;

    public void execute(String sql, Object... params) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql);
        int i = 1;
        for (Object o : params) {
            statement.setObject(i++, o);
        }
        statement.executeUpdate();
    }

    public QueryResult query(String sql, Object... params) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql);
        int i = 0;
        for (Object o : params) {
            statement.setObject(i++, o);
        }
        ResultSet rs = statement.executeQuery();
        return new QueryResult(rs, statement);
    }

    public QueryResult query(String sql) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        return new QueryResult(rs, statement);
    }
    public void disconnect() throws SQLException{
        connection.close();
    }

    ////////////////////////////////////////
    private Database() throws SQLException, ClassNotFoundException {
        String url = "jdbc:";
        url += config.getTYPE() + "://";
        url += config.getHOST() + ":";
        url += config.getPORT() + "/";
        url += config.getBASE() + "?useUnicode=true&characterEncoding=utf-8";
        Class.forName(config.getDRIV());
        connection = DriverManager.getConnection(url, config.getUSER(), config.getPASS());
    }

    public Database getInstance() throws SQLException, ClassNotFoundException {
        if (INSTANCE == null || connection == null) {
            INSTANCE = new Database();
        } else if (connection.isClosed()) {
            INSTANCE = new Database();
        }
        return INSTANCE;
    }

    private Database INSTANCE;

    ///////////////////////////////////////
    ///////////////////////////////////////
    public Configs getConfig() {
        return config;
    }

    public void setConfig(Configs config) {
        this.config = config;
    }
    ///////////////////////////////////////
}
