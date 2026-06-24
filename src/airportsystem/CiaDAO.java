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
public class CiaDAO {

    public boolean adiciona(Cia c) {
        String sql = "INSERT INTO companhias_aereas (nome, abreviacao) VALUES (?, ?)";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getAbreviacao());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    c.setId(rs.getLong(1));
                }
                return true;
            }
            return false;

        } catch (SQLException e) {
            System.err.println("Erro ao adicionar companhia aérea: " + e.getMessage());
            return false;
        }
    }

    public List<Cia> mostrarTodos() {
        List<Cia> lista = new ArrayList<>();
        String sql = "SELECT * FROM companhias_aereas ORDER BY nome";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cia c = new Cia();
                c.setId(rs.getLong("id"));
                c.setNome(rs.getString("nome"));
                c.setAbreviacao(rs.getString("abreviacao"));

                Timestamp criacao = rs.getTimestamp("data_criacao");
                if (criacao != null) {
                    c.setDataCriacao(criacao.toLocalDateTime());
                }

                Timestamp modificacao = rs.getTimestamp("data_modificacao");
                if (modificacao != null) {
                    c.setDataModificacao(modificacao.toLocalDateTime());
                }

                lista.add(c);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar companhias aéreas: " + e.getMessage());
        }

        return lista;
    }

    public Cia buscaPorId(long id) {
        String sql = "SELECT * FROM companhias_aereas WHERE id = ?";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Cia c = new Cia();
                c.setId(rs.getLong("id"));
                c.setNome(rs.getString("nome"));
                c.setAbreviacao(rs.getString("abreviacao"));
                return c;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar companhia por ID: " + e.getMessage());
        }

        return null;
    }

    public Cia buscaPorNomeOuAbreviacao(String termo) {
        String sql = "SELECT * FROM companhias_aereas WHERE nome = ? OR abreviacao = ?";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, termo);
            stmt.setString(2, termo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Cia c = new Cia();
                c.setId(rs.getLong("id"));
                c.setNome(rs.getString("nome"));
                c.setAbreviacao(rs.getString("abreviacao"));
                return c;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar companhia: " + e.getMessage());
        }

        return null;
    }

    public boolean alterarNome(String nomeAntigo, String novoNome) {
        String sql = "UPDATE companhias_aereas SET nome = ? WHERE nome = ?";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, novoNome);
            stmt.setString(2, nomeAntigo);

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao alterar nome da companhia: " + e.getMessage());
            return false;
        }
    }

    public boolean remover(String nome) {
        String sql = "DELETE FROM companhias_aereas WHERE nome = ?";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, nome);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao remover companhia: " + e.getMessage());
            return false;
        }
    }

    public boolean removerPorId(long id) {
        String sql = "DELETE FROM companhias_aereas WHERE id = ?";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setLong(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao remover companhia por ID: " + e.getMessage());
            return false;
        }
    }
}
