/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.ejemplo.idgs13_2_01.evaluacion.lizard2.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @authors manue, emma, fati, fer
 * 
 */

public class ConnSingleton {
    
    private static ConnSingleton instance;
    private Connection conn;

    private ConnSingleton(){
        
    }

    // crear instancia
    public static ConnSingleton getInstance(){
        if (instance == null) {
            instance = new ConnSingleton();
        }
        return instance;
    }

    // crear conexión
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        if (conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/evaluacion2?useSSL=false&allowPublicKeyRetrieval=true", "root", "Perfect97");
        }
        return conn;
    }

    // cerrar conexión
    public void closeConnection() throws SQLException{
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }

    public Connection getConn() {
        return conn;
    }
    
}
