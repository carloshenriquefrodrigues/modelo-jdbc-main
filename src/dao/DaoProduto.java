package dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Produto;


public class DaoProduto {
   
    Connection con = null;
    PreparedStatement pstm = null;

public List<Produto> getProdutos() {
    List<Produto> lista = new ArrayList<Produto>();
    ResultSet rs = null;
    con = new Conexao().conectaBanco();
    
    try {
  
    pstm = con.prepareStatement("SELECT * FROM tb_produto", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    
    rs =  this.pstm.executeQuery();
    if(rs.first()) {
        do {
             Produto p = new Produto();
             p.setId(rs.getInt("id"));
             p.setDescricao(rs.getString("descricao"));             
             p.setQuantidade(rs.getInt("quantidade"));             
             p.setValor(rs.getInt("valor"));             
             lista.add(p);
            
        } while(rs.next());
    }
    
    pstm.close();
  
    }   
    catch(SQLException erro) {
        JOptionPane.showMessageDialog(null, "Erro ao buscar dados no BD "+erro);
    }
    
    
    finally {
        try {
        con.close();
        }
        catch(SQLException err) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão de busca "+err);
        }
    }
    
    return lista;
}
      
    
public void salvarProduto(Produto prod) {
    con = new Conexao().conectaBanco();
    
    try {
    pstm = con.prepareStatement("INSERT INTO tb_produto (descricao,quantidade,valor) VALUES (?,?,?)");
    pstm.setString(1,prod.getDescricao());
    pstm.setInt(2,prod.getQuantidade());
    pstm.setInt(3,(int) prod.getValor()); 
    this.pstm.execute();
    
    
    pstm.close();
    }
    catch(SQLException erro) {
        JOptionPane.showMessageDialog(null, "Erro ao salvar produto no BD "+erro);
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



public void excluirProduto(int id)
{
    con = new Conexao().conectaBanco();
    
    try{
    pstm = con.prepareStatement("DELETE FROM tb_produto WHERE id=?");
    pstm.setInt(1,id);
   
    this.pstm.execute();
    
    
    pstm.close();
    }
    catch(SQLException erro)
    {
        JOptionPane.showMessageDialog(null, "Erro ao excluir produto no BD "+erro);
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
