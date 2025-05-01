package org.example.javafxxpostgresql;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class PostgreSQLController {
    @FXML
    public TextArea field;

    public Button launcher;

    @FXML
    public String format(String s, int whiteSpace){
        String result="";
        for (int i = 0; i < s.length(); i++) {
            result=result+(s.charAt(i));
            whiteSpace--;
        }
        for (int i = 0; i < whiteSpace; i++) {
            result=result+" ";
        }
        return result;
    }
    public void Query(){
        try {
            Connection conn = HikariCPDataSource.getConnection();
            if (conn != null) {
                // Do things once connection is established
                // In this case, print the PostgreSQL version
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("select first_name||' '||last_name full_name, salary,coalesce(commission_pct,0) commission_pct, coalesce(commission_pct,0)*salary commission, floor(coalesce(commission_pct,0)*salary+salary) total_salary from employees");
                while (rs.next()) {
                    String name=format(rs.getString(1), 20);
                    String salary=format(rs.getString(2), 15);
                    String pct =format(rs.getString(3), 5);
                    String commission= format(rs.getString(4), 10);
                    String salTotal=format(rs.getString(5), 20);
                    field.appendText(name+salary+pct+commission+salTotal+"\n");
                }

            } else {
                System.out.println("Connection failed");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void doQuery(){
        field.clear();
        Query();
    }
}
