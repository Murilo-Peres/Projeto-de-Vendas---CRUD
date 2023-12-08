package org.libertas.vendas;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
	private Connection connection;
	
	public Conexao() {
        try {
            String url = "jdbc:mariadb://localhost:3306/vendas";
            String user = "root";
            String senha = "root";

            Class.forName("org.mariadb.jdbc.Driver");

            this.connection = DriverManager.getConnection(url, user, senha);
            System.out.println("Conexão realizada com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void desconectar() {
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
