package org.libertas.vendas;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class VendasDAO {

	public void inserir(Vendas venda) {
        Conexao con = new Conexao();

        try {
            String sql = "INSERT INTO venda (nome_produto, preco, quantidade, nome_cliente) VALUES (?, ?, ?, ?)";
            PreparedStatement prep = con.getConnection().prepareStatement(sql);
            prep.setString(1, venda.getNome_produto());
            prep.setFloat(2, venda.getPreco());
            prep.setInt(3, venda.getQuantidade());
            prep.setString(4, venda.getNome_cliente());
            prep.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void alterar(Vendas venda) {
        Conexao con = new Conexao();

        try {
            String sql = "UPDATE venda SET nome_produto = ?, preco = ?, quantidade = ?, nome_cliente = ? WHERE id_venda = ?";
            PreparedStatement prep = con.getConnection().prepareStatement(sql);
            prep.setString(1, venda.getNome_produto());
            prep.setFloat(2, venda.getPreco());
            prep.setInt(3, venda.getQuantidade());
            prep.setString(4, venda.getNome_cliente());
            prep.setInt(5, venda.getId_venda());
            prep.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void excluir(Vendas venda) {
        Conexao con = new Conexao();

        try {
            String sql = "DELETE FROM venda WHERE id_venda = ?";
            PreparedStatement prep = con.getConnection().prepareStatement(sql);
            prep.setInt(1, venda.getId_venda());
            prep.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Vendas consultar(int id) {
        Vendas vendas = new Vendas();
        Conexao con = new Conexao();

        try {
            String sql = "SELECT * FROM venda WHERE id_venda = " + id;
            Statement sta = con.getConnection().createStatement();
            ResultSet res = sta.executeQuery(sql);
            if (res.next()) {
                vendas.setId_venda(res.getInt("id_venda"));
                vendas.setNome_produto(res.getString("nome_produto"));
                vendas.setPreco(res.getFloat("preco"));
                vendas.setQuantidade(res.getInt("quantidade"));
                vendas.setNome_cliente(res.getString("nome_cliente"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        con.desconectar();
        return vendas;
    }

    public List<Vendas> listar() {
        List<Vendas> lista = new LinkedList<>();
        Conexao con = new Conexao();

        try {
            String sql = "SELECT * FROM venda ORDER BY id_venda";
            Statement sta = con.getConnection().createStatement();
            ResultSet res = sta.executeQuery(sql);
            while (res.next()) {
                Vendas vendas = new Vendas();
                vendas.setId_venda(res.getInt("id_venda"));
                vendas.setNome_produto(res.getString("nome_produto"));
                vendas.setPreco(res.getFloat("preco"));
                vendas.setQuantidade(res.getInt("quantidade"));
                vendas.setNome_cliente(res.getString("nome_cliente"));
                lista.add(vendas);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        con.desconectar();
        return lista;
    }
}
