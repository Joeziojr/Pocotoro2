package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexao {
    
    private static Connection conexao;

    public static Connection getConexao() {
        
        if(conexao == null){
            try {                
                conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/pocotoro_db", "root", "");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, 
                        "Não foi possível conectar ao banco de dados!");
                System.out.println(ex);
            } 
        }        
        return conexao;
        
    }
    
    

}
