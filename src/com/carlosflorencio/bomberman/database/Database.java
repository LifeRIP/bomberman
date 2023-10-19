package com.carlosflorencio.bomberman.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Database {

    private static Connection connection;
    private static String[][] _topScores = new String[5][2];
    
    public static void createConnection() {
        try {
            connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/bomberman_database",
                "postgres",
                "postgres");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 0);
            //e.printStackTrace();
        }
    }

    // INSERT, UPDATE, DELETE
    public static void executeUpdate(String sql) {
        createConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 0);
        }
    }

    // SELECT
    public static void executeQuery(String sql) {
        createConnection();
        int rank = 1;
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            DefaultTableModel modelo = new DefaultTableModel(new String[] {"RANK", "NAME", "POINTS"}, 0);
            while(result.next()) {
                String name = result.getString(1);
                int points = result.getInt(2);
                Object[] fila = new Object[3];
                fila[0] = rank++;
                fila[1] = name;
                fila[2] = points;
                modelo.addRow(fila);
            }
            JTable tabla = new JTable(modelo);
            JScrollPane scroll = new JScrollPane(tabla);
            JOptionPane.showMessageDialog(null, scroll, "Top Scores", -1);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 0);
        }
    }

    public static void consult() {
        String sql = "SELECT name, points, " +
                     "RANK () OVER ( " +
                     "ORDER BY points DESC " +
                     ") as rank " +
                     "FROM public.users";
        executeQuery(sql);
    }

    public static String[][] consultTopScores() {
        createConnection();
        fillBlankTopScoresArr();
        String sql = "SELECT name, points, " +
                     "RANK () OVER ( " +
                     "ORDER BY points DESC " +
                     ") rank " +
                     "FROM public.users";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultado = statement.executeQuery(sql);
            for (int i = 0; i < 5; i++) {
                resultado.next();
                String name = resultado.getString(1);
                String points = resultado.getString(2);
                _topScores[i][0] = name;
                _topScores[i][1] = points;
            }
        } catch (SQLException e) {
            if(e.getSQLState().equals("24000")) { // avoid error when database is empty
                return _topScores;
            }
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 0);
        }
        return _topScores;
    }
    
    public static void insert(String name, int points) {
        String sql = "INSERT INTO public.users("+
                     "name, points)" +
                     "SELECT '" + name + "', " + points + " " +
                     "WHERE NOT EXISTS (SELECT 1 FROM public.users WHERE name = '" + name + "')";
        executeUpdate(sql);
    }

    public static void update(String name, int points) {
        String sql = "UPDATE public.users "+
                     "SET points = " + points + " " +
                     "WHERE name = '" + name + "' AND points < " + points;
        executeUpdate(sql);
    }
    
    public static void fillBlankTopScoresArr() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 1; j++) {
                _topScores[i][j] = "-";
                _topScores[i][j+1] = "-";
            }
        }
    }
}
