/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Usuario;

/**
 *
 * @author dskaster
 */
public class UsuarioDAO extends DAO<Usuario> {

    private static final String CREATE_QUERY
            = "INSERT INTO j2ee.usuario"
            + "(login, senha, nome, nascimento, avatar)"
            + "VALUES (?,md5(?),?,?,?) RETURNING id";

    private static final String READ_QUERY
            = "SELECT login, nome, nascimento, avatar "
            + "FROM j2ee.usuario "
            + "WHERE id = ?;";

    private static final String UPDATE_QUERY
            = "UPDATE j2ee.usuario "
            + "SET login = ?, nome = ?, nascimento = ? "
            + "WHERE id = ?;";

    private static final String DELETE_QUERY
            = "DELETE FROM j2ee.usuario "
            + "WHERE id = ?;";

    private static final String UPDATE_WITH_PASSWORD_QUERY
            = "UPDATE j2ee.usuario "
            + "SET login = ?, nome = ?, nascimento = ?, senha = md5(?) "
            + "WHERE id = ?;";

    private static final String ALL_QUERY
            = "SELECT id, login "
            + "FROM j2ee.usuario "
            + "ORDER BY login;";

    private static final String AUTHENTICATE_QUERY
            = "SELECT id, nome, nascimento, avatar "
            + "FROM j2ee.usuario "
            + "WHERE login = ? AND senha = md5(?);";

    public UsuarioDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Usuario usuario) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_QUERY);) {
            statement.setString(1, usuario.getLogin());
            statement.setString(2, usuario.getSenha());
            statement.setString(3, usuario.getNome());
            statement.setDate(4, usuario.getNascimento());
            statement.setString(5, usuario.getAvatar());

            try (ResultSet result = statement.executeQuery();) {
                if (result.next()) {
                    usuario.setId(result.getInt("id"));
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            if (ex.getMessage().contains("uq_usuario_login")) {
                throw new SQLException("Erro ao inserir usuário: login já existente.");
            } else if (ex.getMessage().contains("not-null")) {
                throw new SQLException("Erro ao inserir usuário: pelo menos um campo está em branco.");
            } else {
                throw new SQLException("Erro ao inserir usuário.");
            }
        }
    }

    @Override
    public Usuario read(Integer id) throws SQLException {
        Usuario usuario = new Usuario();

        try (PreparedStatement statement = connection.prepareStatement(READ_QUERY)) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    usuario.setId(id);
                    usuario.setLogin(result.getString("login"));
                    usuario.setNome(result.getString("nome"));
                    usuario.setNascimento(result.getDate("nascimento"));
                    usuario.setAvatar(result.getString("avatar"));
                } else {
                    throw new SQLException("Erro ao visualizar: usuário não encontrado.");
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            if (ex.getMessage().equals("Erro ao visualizar: usuário não encontrado.")) {
                throw ex;
            } else {
                throw new SQLException("Erro ao visualizar usuário.");
            }
        }

        return usuario;
    }

    @Override
    public void update(Usuario usuario) throws SQLException {
        String query;

        if (usuario.getSenha() == null) {
            query = UPDATE_QUERY;
        } else {
            query = UPDATE_WITH_PASSWORD_QUERY;
        }

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, usuario.getLogin());
            statement.setString(2, usuario.getNome());
            statement.setDate(3, usuario.getNascimento());

            if (usuario.getSenha() == null) {
                statement.setInt(4, usuario.getId());
            } else {
                statement.setString(4, usuario.getSenha());
                statement.setInt(5, usuario.getId());
            }

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao editar: usuário não encontrado.");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            if (ex.getMessage().equals("Erro ao editar: usuário não encontrado.")) {
                throw ex;
            } else if (ex.getMessage().contains("uq_usuario_login")) {
                throw new SQLException("Erro ao editar usuário: login já existente.");
            } else if (ex.getMessage().contains("not-null")) {
                throw new SQLException("Erro ao editar usuário: pelo menos um campo está em branco.");
            } else {
                throw new SQLException("Erro ao editar usuário.");
            }
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, id);

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao excluir: usuário não encontrado.");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            if (ex.getMessage().equals("Erro ao excluir: usuário não encontrado.")) {
                throw ex;
            } else {
                throw new SQLException("Erro ao excluir usuário.");
            }
        }
    }

    @Override
    public List<Usuario> all() throws SQLException {
        List<Usuario> usuarioList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(ALL_QUERY);
                ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(result.getInt("id"));
                usuario.setLogin(result.getString("login"));

                usuarioList.add(usuario);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            throw new SQLException("Erro ao listar usuários.");
        }

        return usuarioList;
    }

    public void authenticate(Usuario usuario) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(AUTHENTICATE_QUERY)) {
            statement.setString(1, usuario.getLogin());
            statement.setString(2, usuario.getSenha());

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    usuario.setId(result.getInt("id"));
                    usuario.setNome(result.getString("nome"));
                    usuario.setNascimento(result.getDate("nascimento"));
                    usuario.setAvatar(result.getString("avatar"));
                } else {
                    throw new SecurityException("Login ou senha incorretos.");
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            throw new SQLException("Erro ao autenticar usuário.");
        }
    }

}
