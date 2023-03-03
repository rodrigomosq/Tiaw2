package maven.demo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FilmeDAO {
private Connection conexao;
	
	
	public FilmeDAO() {
		conexao = null;
	}
	
	
	public boolean conectar() {
		String driverName = "org.postgresql.Driver";                    
		String serverName = "localhost";
		String mydatabase = "Filmes";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" + mydatabase;
		String username = "postgres";
		String password = "Diguinho90";
		boolean status = false;

		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexão efetuada com o postgres!");
		} catch (ClassNotFoundException e) { 
			System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
		}

		return status;
	}
	
	public boolean close() {
		boolean status = false;
		
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}
	public boolean inserirFilme(Filme filme) {
		
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO filme (nome, id, categoria, classificacao) "
	                + "VALUES ('" + filme.getNome() + "', '" + filme.getId() + "', '"
	                + filme.getCategoria() + "', '" + filme.getClassificacao() + "');");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	public boolean atualizarFilme(Filme filme) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE filme SET nome = '" + filme.getNome() + "', classificacao = '"  
				       + filme.getClassificacao() + "',categoria='"+ filme.getCategoria() + "'"
					   + " WHERE id = " + filme.getId();
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	public boolean excluirFilme(int id) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            st.executeUpdate("DELETE FROM filme WHERE id = " + id);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }
	public Filme[] getFilme() {
        Filme[] filmes= null;

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM filme");

             if(rs.next()){
                 rs.last();
                 filmes = new Filme[rs.getRow()];
                 rs.beforeFirst();

                 for(int i = 0; rs.next(); i++) {
                    filmes[i] = new Filme(rs.getInt("id"), rs.getString("nome"), rs.getString("categoria"), rs.getInt("classificacao"));

                 }

              }
              st.close();
        } catch (Exception e) {

            System.err.println(e.getMessage());
        }
        return filmes;
    }
}
