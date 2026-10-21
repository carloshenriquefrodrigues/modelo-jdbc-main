package dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Aluno;

public class DaoAluno {

    Connection con = null;
    PreparedStatement pstm = null;

    public List<Aluno> getAlunos() {
        List<Aluno> lista = new ArrayList<Aluno>();
        ResultSet rs = null;
        con = new Conexao().conectaBanco();

        try {

            pstm = con.prepareStatement("SELECT * FROM aluno", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            rs = this.pstm.executeQuery();
            if (rs.first()) {
                do {
                    Aluno p = new Aluno();
                    p.setId(rs.getInt("id"));
                    p.setNome(rs.getString("nome"));
                    p.setTelefone(rs.getString("telefone"));
                    p.setCurso(rs.getString("curso"));
                    lista.add(p);

                } while (rs.next());
            }

            pstm.close();

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar dados no BD " + erro);
        } finally {
            try {
                con.close();
            } catch (SQLException err) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão de busca " + err);
            }
        }

        return lista;
    }

    public void salvarAluno(Aluno aluno) {
        con = new Conexao().conectaBanco();

        try {
            pstm = con.prepareStatement("INSERT INTO aluno (nome,telefone,curso) VALUES (?,?,?)");
            pstm.setString(1, aluno.getNome());
            pstm.setString(2, aluno.getTelefone());
            pstm.setString(3, aluno.getCurso());
            this.pstm.execute();

            pstm.close();
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar aluno no BD " + erro);
        } finally {
            try {
                con.close();
            } catch (SQLException err) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão de salvamento " + err);
            }
        }

    }

    public void editarAluno(Aluno aluno) {
        con = new Conexao().conectaBanco();
        try {
            pstm = con.prepareStatement("UPDATE aluno SET nome=?, telefone=?, curso=? WHERE id = ?");
            pstm.setString(1, aluno.getNome());
            pstm.setString(2, aluno.getTelefone());
            pstm.setString(3, aluno.getCurso());
            pstm.setString(4, String.valueOf(aluno.getId()));
            this.pstm.execute();

            pstm.close();
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar aluno no BD " + erro);
        } finally {
            try {
                con.close();
            } catch (SQLException err) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão de salvamento " + err);
            }
        }
    }

    public void excluirAluno(int id) {
        con = new Conexao().conectaBanco();

        try {
            pstm = con.prepareStatement("DELETE FROM aluno WHERE id=?");
            pstm.setInt(1, id);

            this.pstm.execute();

            pstm.close();
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir aluno no BD " + erro);
        } finally {
            try {
                con.close();
            } catch (SQLException err) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão de salvamento " + err);
            }
        }

    }

}
