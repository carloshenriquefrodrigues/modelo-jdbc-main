package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexao {

    private Connection conexao = null;

    public Connection conectaBanco() {
        try {
            // Driver JDBC do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            conexao = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/prova",
                "carllos",
                "123"
            );

            // JOptionPane.showMessageDialog(
            //     null,
            //     "Conexão realizada com sucesso!"
            // );

            System.out.println("Conexão realizada com sucesso.");

        } catch (ClassNotFoundException drive) {

            JOptionPane.showMessageDialog(
                null,
                "Driver MySQL não encontrado: " + drive.getMessage(),
                "Erro Driver",
                JOptionPane.ERROR_MESSAGE
            );

        } catch (SQLException fonte) {

            JOptionPane.showMessageDialog(
                null,
                "Erro SQL: " + fonte.getMessage(),
                "Erro de conexão",
                JOptionPane.ERROR_MESSAGE
            );
        }

        return conexao;
    }
}