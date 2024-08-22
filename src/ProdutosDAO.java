/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public void cadastrarProduto(ProdutosDTO produto) {

        conn = new conectaDAO().connectDB();

        try {

            prep = conn.prepareStatement("INSERT INTO  produtos (nome, valor, status)  VALUES (?,?,?)");

            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            prep.execute();
            prep.close();
            conn.close();
            
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar o produto ");
            System.out.println("erro ao salvar: " + e.getMessage());
        }

       
    }

    public ArrayList<ProdutosDTO> listarProdutos() {

        ArrayList<ProdutosDTO> listagem = new ArrayList<ProdutosDTO>();

        try {

            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement("SELECT * FROM Produtos");
            ResultSet resposta = prep.executeQuery();

            while (resposta.next()) {

                ProdutosDTO p = new ProdutosDTO();
                p.setId(resposta.getInt("id"));
                p.setNome(resposta.getString("nome"));
                p.setValor(resposta.getInt("valor"));
                p.setStatus(resposta.getString("status"));
                listagem.add(p);                
            }
             
            prep.close();
            conn.close();
            
            } catch (SQLException ex) { 
            System.out.println("Erro ao listar produtos!");
        }
        return listagem;
    }
    
    public void venderProduto(int id){
        
        try{
            
            ProdutosDAO produto = new ProdutosDAO();
            conn = new conectaDAO().connectDB();
            
            prep = conn.prepareStatement("UPDATE produtos SET status=? WHERE id=?");
            
            prep.setString(1, "Vendido");
            prep.setInt(2, id);
            prep.executeUpdate();
            
            prep.close();
            conn.close();
            
        
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao Vender Produto");
        }
    }
    
     public ArrayList<ProdutosDTO> listarProdutosVendidos(){
        
         ArrayList<ProdutosDTO> listagem = new ArrayList<ProdutosDTO>();

        try {

            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement("SELECT * FROM Produtos WHERE status=?");
            
            prep.setString(1, "Vendido");
            
            ResultSet resposta = prep.executeQuery();
            
            while (resposta.next()) {

                ProdutosDTO p = new ProdutosDTO();
                p.setId(resposta.getInt("id"));
                p.setNome(resposta.getString("nome"));
                p.setValor(resposta.getInt("valor"));
                p.setStatus(resposta.getString("status"));
                listagem.add(p);                
            }
             
            prep.close();
            conn.close();
            
            } catch (SQLException ex) { 
            System.out.println("Erro ao listar produtos!");
        }
        return listagem;
    }
}
