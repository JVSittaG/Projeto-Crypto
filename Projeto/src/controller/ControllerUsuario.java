package controller;
import DAO.AlunoDAO;
import DAO.Conexao;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Aluno;
import view.UsuarioFrame;

public class ControllerUsuario{
  private UsuarioFrame view;
  private Aluno aluno;

  public ControllerUsuario(UsuarioFrame view, Aluno aluno){
    this.view = view;
    this.aluno= aluno;
    
  }
  public void atualizar(){
    String usuario = view.getTxtLogin().getText();
    String senha = view.getTxtNovaSenha().getText();
    Aluno aluno = new Aluno("", usuario, senha);
    Conexao conexao = new Conexao();
    try{
      Connection conn = conexao.getConnection();
      AlunoDAO dao = new AlunoDAO(conn);
      dao.atualizar(aluno);
      JOptionPane.showMessageDialog(view, "Senha atualizada com sucesso!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
    }catch(SQLException e){
      JOptionPane.showMessageDialog(view, "Falha de conexao", "Erro", JOptionPane.ERROR_MESSAGE);
    }
  }

  public void remover(){
    String usuario = aluno.getUsuario();
    int option = JOptionPane.showConfirmDialog(view,"Deseja excluir o cadastro? ", "Aviso", JOptionPane.YES_NO_OPTION);
    if (option !=1){
      Conexao conexao = new Conexao();
      try{
        Connection conn = conexao.getConnection();
        AlunoDAO dao = new AlunoDAO(conn);
        dao.remover(aluno);
        JOptionPane.showMessageDialog(view, "Cadastro removido com sucesso!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
      }catch(SQLException e){
        JOptionPane.showMessageDialog(view,"Falha de conexao", "Erro", JOptionPane.ERROR_MESSAGE);
      }
    }
  }
}