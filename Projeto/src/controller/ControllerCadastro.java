/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.AlunoDAO;
import DAO.Conexao;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Aluno;
import view.CadastroFrame;


public class ControllerCadastro {
  private CadastroFrame view;
  public ControllerCadastro(CadastroFrame view){
    this.view = view;
    
  }
  public void salvarAluno(){
    String nome = view.getEntrada_nome().getText();
    String usuario = view.getEntrada_usuario().getText();
    String senha = view.getEntrada_senha().getText();

    Aluno aluno = new Aluno(nome, usuario, senha);

    Conexao conexao = new Conexao();
    try{
      Connection conn = conexao.getConnection();
      System.out.println("conectou");
      AlunoDAO dao = new AlunoDAO(conn);
      dao.inserir(aluno);
      JOptionPane.showMessageDialog(view, "Aluno cadastrado com sucesso!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
      
  }catch(SQLException ex) {
      JOptionPane.showMessageDialog(view, "Erro ao cadastrar aluno: ", "Erro", JOptionPane.ERROR_MESSAGE);
      ex.printStackTrace();
    }
  }
    
}
