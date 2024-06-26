package controller;

import DAO.AlunoDAO;
import DAO.Conexao;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import model.Aluno;
import view.LoginFrame;
import view.UsuarioFrame;

public class ControllerLogin {
  private LoginFrame view;

  public ControllerLogin(LoginFrame view) {
    this.view = view;
  }

  public void loginAluno(){
    Aluno aluno = new Aluno(null, view.getEntrada_usuario().getText(), view.getEntrada_senha().getText());
    Conexao conexao = new Conexao();
    try{
      Connection conn = conexao.getConnection();
      AlunoDAO dao = new AlunoDAO(conn);
      ResultSet res = dao.consultar(aluno);
      if(res.next()){
        JOptionPane.showMessageDialog(view,"Login realizado com sucesso!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        String nome = res.getString("nome");
        String usuario = res.getString("usuario");
        String senha = res.getString("senha");
        UsuarioFrame = viewUsuario = new UsuarioFrame(nome, usuario, senha);
        viewUsuario.setVisible(true);
        view.setVisible(false);
      }else{
        JOptionPane.showMessageDialog(view,"Login nao efetuado", "Erro", JOptionPane.ERROR_MESSAGE);
        }
      }catch(SQLException e){
        JOptionPane.showMessageDialog(view,"Erro de conexao ", "Erro", JOptionPane.ERROR_MESSAGE);
      }
    }
  }
