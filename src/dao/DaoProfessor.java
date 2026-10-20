package dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Professor;


public class DaoProfessor {
    
   
    Connection con = null;
    PreparedStatement pstm = null;

public List<Professor> getProfessores() {
    List<Professor> lista = new ArrayList<Professor>();
    ResultSet rs = null;
    con = new Conexao().conectaBanco();
    
    try{
  
    pstm = con.prepareStatement("SELECT * FROM professor", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    
    rs =  this.pstm.executeQuery();
    if(rs.first())
    {
        do{
             Professor c = new Professor();
             c.setId(rs.getInt("id"));
             c.setNome(rs.getString("Nome"));
             c.setTelefone(rs.getString("Telefone"));
             c.setDisciplina(rs.getString("Disciplina"));
             
             lista.add(c);
            
        }while(rs.next());
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
      
    
public void salvarProfessor(Professor professor) {
    con = new Conexao().conectaBanco();
    
    try{
    pstm = con.prepareStatement("INSERT INTO professor (nome,telefone,disciplina) VALUES (?,?,?)");
    pstm.setString(1,professor.getNome());
    pstm.setString(2,professor.getTelefone());
    pstm.setString(3, professor.getDisciplina());
    this.pstm.execute();
    
    
    pstm.close();
    }
    catch(SQLException erro) {
        JOptionPane.showMessageDialog(null, "Erro ao salvar professor no BD "+erro);
    }
    finally{
        try{
        con.close();
        }
        catch(SQLException err)
        {
            JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão de salvamento "+err);
        }
    }
    

}



public void excluirProfessor(int id)
{
    con = new Conexao().conectaBanco();
    
    try{
    pstm = con.prepareStatement("DELETE FROM professor WHERE id=?");
    pstm.setInt(1,id);
   
    this.pstm.execute();
    
    
    pstm.close();
    }
    catch(SQLException erro)
    {
        JOptionPane.showMessageDialog(null, "Erro ao excluir professor no BD "+erro);
    }
    finally{
        try{
        con.close();
        }
        catch(SQLException err)
        {
            JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão de salvamento "+err);
        }
    }
    

}
    
}
