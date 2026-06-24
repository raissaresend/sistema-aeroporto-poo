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
public class TicketDAO {

    public boolean adiciona(Ticket t) {
        String sql = "INSERT INTO tickets (valor, voo_id, passageiro_id, codigo) VALUES (?, ?, ?, ?)";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setDouble(1, t.getValor());
            stmt.setLong(2, t.getVooId());
            stmt.setLong(3, t.getPassageiroId());
            stmt.setLong(4, t.getCodigo());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    t.setId(rs.getLong(1));
                }
                return true;
            }
            return false;

        } catch (SQLException e) {
            System.err.println("Erro ao adicionar ticket: " + e.getMessage());
            return false;
        }
    }

    public List<Ticket> mostrarTodos() {
        List<Ticket> lista = new ArrayList<>();
        String sql = "SELECT * FROM tickets ORDER BY id";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Ticket t = new Ticket();
                t.setId(rs.getLong("id"));
                t.setValor(rs.getDouble("valor"));
                t.setVooId(rs.getLong("voo_id"));
                t.setPassageiroId(rs.getLong("passageiro_id"));
                t.setCodigo(rs.getLong("codigo"));

                Timestamp criacao = rs.getTimestamp("data_criacao");
                if (criacao != null) {
                    t.setDataCriacao(criacao.toLocalDateTime());
                }

                Timestamp modificacao = rs.getTimestamp("data_modificacao");
                if (modificacao != null) {
                    t.setDataModificacao(modificacao.toLocalDateTime());
                }

                lista.add(t);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar tickets: " + e.getMessage());
        }

        return lista;
    }

    public Ticket buscaPorId(long id) {
        String sql = "SELECT * FROM tickets WHERE id = ?";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Ticket t = new Ticket();
                t.setId(rs.getLong("id"));
                t.setValor(rs.getDouble("valor"));
                t.setVooId(rs.getLong("voo_id"));
                t.setPassageiroId(rs.getLong("passageiro_id"));
                t.setCodigo(rs.getLong("codigo"));

                Timestamp criacao = rs.getTimestamp("data_criacao");
                if (criacao != null) {
                    t.setDataCriacao(criacao.toLocalDateTime());
                }

                return t;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar ticket por ID: " + e.getMessage());
        }

        return null;
    }

    public Ticket buscaPorCodigo(long codigo) {
        String sql = "SELECT * FROM tickets WHERE codigo = ?";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setLong(1, codigo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Ticket t = new Ticket();
                t.setId(rs.getLong("id"));
                t.setValor(rs.getDouble("valor"));
                t.setVooId(rs.getLong("voo_id"));
                t.setPassageiroId(rs.getLong("passageiro_id"));
                t.setCodigo(rs.getLong("codigo"));

                Timestamp criacao = rs.getTimestamp("data_criacao");
                if (criacao != null) {
                    t.setDataCriacao(criacao.toLocalDateTime());
                }

                return t;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar ticket por código: " + e.getMessage());
        }

        return null;
    }

    public boolean removerPorId(long id) {
        String sql = "DELETE FROM tickets WHERE id = ?";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setLong(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao remover ticket: " + e.getMessage());
            return false;
        }
    }
}
