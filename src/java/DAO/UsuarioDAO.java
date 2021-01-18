package java.DAO;
import java.Modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Util.FabricaConexao;

/**
 *
 * @author danil
 */
public class UsuarioDAO {

    public Usuario validarLogin(Usuario usuario) throws ClassNotFoundException, SQLException{
        
        
       Connection con = FabricaConexao.getConexao();
       
            PreparedStatement comando = con.prepareStatement("SELECT id, email, senha FROM usuarios WHERE email = ? AND senha = ?");
            comando.setString(1, usuario.getEmail());
            comando.setString(2, usuario.getSenha());
            comando.execute();
            
            ResultSet resultado = comando.executeQuery();

       
        if (resultado.next()) {
            Usuario user = new Usuario();
            user.setId(resultado.getInt("id"));
            user.setEmail(resultado.getString("email"));
            user.setSenha(resultado.getString("senha"));
 
            con.close();
            return user;

        }else
        con.close();
        return null;
    }
    
    public void cadastroUser (Usuario user) throws ClassNotFoundException, SQLException{
        Connection con = FabricaConexao.getConexao();
        
        PreparedStatement comando = con.prepareStatement("insert into usuarios (id, email, senha) values (DEFAULT, ?, ?)");
        comando.setString(1, user.getEmail());
        comando.setString(2, user.getSenha());
        comando.execute();
        
        con.close();
    }
    
}
