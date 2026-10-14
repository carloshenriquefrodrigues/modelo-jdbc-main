package dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Pessoa;

public class DaoPessoa {

    Connection con = null;
    PreparedStatement pstm = null;

    public List<Pessoa> getPessoas() {
        List<Pessoa> lista = new ArrayList<Pessoa>();
        ResultSet rs = null;
        con = new Conexao().conectaBanco();
        
        try{
    
        pstm = con.prepareStatement("SELECT * FROM tb_pessoa", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        
        rs =  this.pstm.executeQuery();
        if(rs.first()) {
            do {
                Pessoa p = new Pessoa();
                p.setNome(rs.getString("nome"));             
                p.setCpf(rs.getString("cpf"));             
                p.setIdade(rs.getInt("idade"));             
                lista.add(p);
                
            } while(rs.next());
        }
        
        pstm.close();
    
        }   
        catch(SQLException erro)
        {
            JOptionPane.showMessageDialog(null, "Erro ao buscar dados no BD "+erro);
        }
        
        
        finally{
            try{
            con.close();
            }
            catch(SQLException err)
            {
                JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão de busca "+err);
            }
        }
        
        return lista;
    }
          
    public void salvarPessoa(Pessoa pessoa) {
        con = new Conexao().conectaBanco();
        
        try {
            pstm = con.prepareStatement("INSERT INTO tb_pessoa (nome,cpf,idade) VALUES (?,?,?)");
            pstm.setString(1,pessoa.getNome());
            pstm.setString(2,pessoa.getCpf());
            pstm.setInt(3,pessoa.getIdade());
            this.pstm.execute();
            pstm.close();
        } catch(SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar pessoa no BD "+erro);
        } finally{
            try {
                con.close();
            } catch(SQLException err) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão de salvamento "+err);
            }
        }
    } 

    public void excluirPessoa(String cpf) {
        con = new Conexao().conectaBanco();
        
        try {
            pstm = con.prepareStatement("DELETE FROM tb_pessoa  WHERE cpf=?");
            pstm.setString(1, cpf);
            this.pstm.execute();
            pstm.close();
        } catch(SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir pessoa no BD "+erro);
        }
        finally{
            try {
                con.close();
            }
            catch(SQLException err) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão de salvamento "+err);
            }
        }
    }
}
