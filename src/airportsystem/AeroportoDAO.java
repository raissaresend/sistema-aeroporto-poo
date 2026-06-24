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
public class AeroportoDAO {

    public boolean adiciona(Aeroporto a) {
        String sql = "INSERT INTO aeroportos (nome, abreviacao, cidade) VALUES (?, ?, ?)";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, a.getNome());
            stmt.setString(2, a.getAbreviacao());
            stmt.setString(3, a.getCidade());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    a.setId(rs.getLong(1));
                }
                return true;
            }
            return false;

        } catch (SQLException e) {
            System.err.println("Erro ao adicionar aeroporto: " + e.getMessage());
            return false;
        }
    }

    public List<Aeroporto> mostrarTodos() {
        List<Aeroporto> lista = new ArrayList<>();
        String sql = "SELECT * FROM aeroportos ORDER BY nome";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Aeroporto a = new Aeroporto();
                a.setId(rs.getLong("id"));
                a.setNome(rs.getString("nome"));
                a.setAbreviacao(rs.getString("abreviacao"));
                a.setCidade(rs.getString("cidade"));

                Timestamp criacao = rs.getTimestamp("data_criacao");
                if (criacao != null) {
                    a.setDataCriacao(criacao.toLocalDateTime());
                }

                Timestamp modificacao = rs.getTimestamp("data_modificacao");
                if (modificacao != null) {
                    a.setDataModificacao(modificacao.toLocalDateTime());
                }

                lista.add(a);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar aeroportos: " + e.getMessage());
        }

        return lista;
    }

    public Aeroporto buscaPorId(long id) {
        String sql = "SELECT * FROM aeroportos WHERE id = ?";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Aeroporto a = new Aeroporto();
                a.setId(rs.getLong("id"));
                a.setNome(rs.getString("nome"));
                a.setAbreviacao(rs.getString("abreviacao"));
                a.setCidade(rs.getString("cidade"));
                return a;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar aeroporto por ID: " + e.getMessage());
        }

        return null;
    }

    public Aeroporto buscaNome(String nome) {
        String sql = "SELECT * FROM aeroportos WHERE nome = ?";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Aeroporto a = new Aeroporto();
                a.setId(rs.getLong("id"));
                a.setNome(rs.getString("nome"));
                a.setAbreviacao(rs.getString("abreviacao"));
                a.setCidade(rs.getString("cidade"));
                return a;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar aeroporto por nome: " + e.getMessage());
        }

        return null;
    }

    public boolean alterarNome(String nomeAntigo, String novoNome) {
        String sql = "UPDATE aeroportos SET nome = ? WHERE nome = ?";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, novoNome);
            stmt.setString(2, nomeAntigo);

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao alterar nome do aeroporto: " + e.getMessage());
            return false;
        }
    }

    public boolean remover(String nome) {
        String sql = "DELETE FROM aeroportos WHERE nome = ?";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, nome);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao remover aeroporto: " + e.getMessage());
            return false;
        }
    }

    public boolean removerPorId(long id) {
        String sql = "DELETE FROM aeroportos WHERE id = ?";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setLong(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao remover aeroporto por ID: " + e.getMessage());
            return false;
        }
    }
}
