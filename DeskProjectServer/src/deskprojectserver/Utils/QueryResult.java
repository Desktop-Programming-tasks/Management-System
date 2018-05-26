/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deskprojectserver.Utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author gabriel
 */
public class QueryResult {
    private final ResultSet rs;
    private final Statement st;

    public QueryResult(ResultSet rs, Statement st) {
        this.rs = rs;
        this.st = st;
    }
    public void closeAll() throws SQLException{
        rs.close();
        st.close();
    }
    public ResultSet getResultSet() {
        return rs;
    }  
}
