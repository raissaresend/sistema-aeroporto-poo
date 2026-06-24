/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airportsystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author RAISSA
 */
public class PassageiroDAO {

    public boolean adiciona(Passageiro p) {
        String sql = "INSERT INTO passageiros (nome, nascimento, documento, login, senha) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, p.getNome());
            stmt.setDate(2, p.getNascimento() != null ? Date.valueOf(p.getNascimento()) : null);
            stmt.setString(3, p.getDocumento());
            stmt.setString(4, p.getLogin());
            stmt.setString(5, p.getSenha());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    p.setId(rs.getLong(1));
                }
                return true;
            }
            return false;

        } catch (SQLException e) {
            System.err.println("Erro ao adicionar passageiro: " + e.getMessage());
            return false;
        }
    }

    public List<Passageiro> mostrarTodos() {
        List<Passageiro> lista = new ArrayList<>();
        String sql = "SELECT * FROM passageiros ORDER BY nome";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Passageiro p = new Passageiro();
                p.setId(rs.getLong("id"));
                p.setNome(rs.getString("nome"));

                Date nascimentoDate = rs.getDate("nascimento");
                if (nascimentoDate != null) {
                    p.setNascimento(nascimentoDate.toLocalDate());
                }

                p.setDocumento(rs.getString("documento"));
                p.setLogin(rs.getString("login"));
                p.setSenha(rs.getString("senha"));

                Timestamp criacao = rs.getTimestamp("data_criacao");
                if (criacao != null) {
                    p.setDataCriacao(criacao.toLocalDateTime());
                }

                Timestamp modificacao = rs.getTimestamp("data_modificacao");
                if (modificacao != null) {
                    p.setDataModificacao(modificacao.toLocalDateTime());
                }

                lista.add(p);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar passageiros: " + e.getMessage());
        }

        return lista;
    }

    public Passageiro buscaPorId(long id) {
        String sql = "SELECT * FROM passageiros WHERE id = ?";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Passageiro p = new Passageiro();
                p.setId(rs.getLong("id"));
                p.setNome(rs.getString("nome"));

                Date nascimentoDate = rs.getDate("nascimento");
                if (nascimentoDate != null) {
                    p.setNascimento(nascimentoDate.toLocalDate());
                }

                p.setDocumento(rs.getString("documento"));
                p.setLogin(rs.getString("login"));
                p.setSenha(rs.getString("senha"));

                Timestamp criacao = rs.getTimestamp("data_criacao");
                if (criacao != null) {
                    p.setDataCriacao(criacao.toLocalDateTime());
                }

                Timestamp modificacao = rs.getTimestamp("data_modificacao");
                if (modificacao != null) {
                    p.setDataModificacao(modificacao.toLocalDateTime());
                }

                return p;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar passageiro por ID: " + e.getMessage());
        }

        return null;
    }

    public Passageiro buscaNome(String nome) {
        String sql = "SELECT * FROM passageiros WHERE nome = ?";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Passageiro p = new Passageiro();
                p.setId(rs.getLong("id"));
                p.setNome(rs.getString("nome"));

                Date nascimentoDate = rs.getDate("nascimento");
                if (nascimentoDate != null) {
                    p.setNascimento(nascimentoDate.toLocalDate());
                }

                p.setDocumento(rs.getString("documento"));
                p.setLogin(rs.getString("login"));
                p.setSenha(rs.getString("senha"));

                return p;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar passageiro por nome: " + e.getMessage());
        }

        return null;
    }

    public Passageiro buscaPorDocumento(String documento) {
        String sql = "SELECT * FROM passageiros WHERE documento = ?";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, documento);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Passageiro p = new Passageiro();
                p.setId(rs.getLong("id"));
                p.setNome(rs.getString("nome"));

                Date nascimentoDate = rs.getDate("nascimento");
                if (nascimentoDate != null) {
                    p.setNascimento(nascimentoDate.toLocalDate());
                }

                p.setDocumento(rs.getString("documento"));
                p.setLogin(rs.getString("login"));
                p.setSenha(rs.getString("senha"));

                return p;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar passageiro por documento: " + e.getMessage());
        }

        return null;
    }

    public boolean alterarNome(String nomeAntigo, String novoNome) {
        String sql = "UPDATE passageiros SET nome = ? WHERE nome = ?";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, novoNome);
            stmt.setString(2, nomeAntigo);

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao alterar nome do passageiro: " + e.getMessage());
            return false;
        }
    }

    public boolean remover(String nome) {
        String sql = "DELETE FROM passageiros WHERE nome = ?";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, nome);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao remover passageiro: " + e.getMessage());
            return false;
        }
    }

    public boolean removerPorId(long id) {
        String sql = "DELETE FROM passageiros WHERE id = ?";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setLong(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao remover passageiro por ID: " + e.getMessage());
            return false;
        }
    }
}
