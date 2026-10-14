package aula2;

import conexao.Conexao;
import view.FrmPrincipal;

public class Principal {

    public static void main(String[] args) {
        new FrmPrincipal().setVisible(true);
        new Conexao().conectaBanco();
    }
}
