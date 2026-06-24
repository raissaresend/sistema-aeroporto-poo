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
public class BoardingPassDAO {

    public boolean adiciona(BoardingPass bp) {
        String sql = "INSERT INTO boarding_pass (ticket_id, passageiro_id, voo_id, assento_id) VALUES (?, ?, ?, ?)";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setLong(1, bp.getTicketId());
            stmt.setLong(2, bp.getPassageiroId());
            stmt.setLong(3, bp.getVooId());
            stmt.setLong(4, bp.getAssentoId());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    bp.setId(rs.getLong(1));
                }
                return true;
            }
            return false;

        } catch (SQLException e) {
            System.err.println("Erro ao adicionar boarding pass: " + e.getMessage());
            return false;
        }
    }

    public List<BoardingPass> mostrarTodos() {
        List<BoardingPass> lista = new ArrayList<>();
        String sql = "SELECT * FROM boarding_pass ORDER BY id";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                BoardingPass bp = new BoardingPass();
                bp.setId(rs.getLong("id"));
                bp.setTicketId(rs.getLong("ticket_id"));
                bp.setPassageiroId(rs.getLong("passageiro_id"));
                bp.setVooId(rs.getLong("voo_id"));
                bp.setAssentoId(rs.getLong("assento_id"));

                lista.add(bp);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar boarding pass: " + e.getMessage());
        }

        return lista;
    }

    public BoardingPass buscaPorId(long id) {
        String sql = "SELECT * FROM boarding_pass WHERE id = ?";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                BoardingPass bp = new BoardingPass();
                bp.setId(rs.getLong("id"));
                bp.setTicketId(rs.getLong("ticket_id"));
                bp.setPassageiroId(rs.getLong("passageiro_id"));
                bp.setVooId(rs.getLong("voo_id"));
                bp.setAssentoId(rs.getLong("assento_id"));

                return bp;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar boarding pass por ID: " + e.getMessage());
        }

        return null;
    }

    public boolean removerPorId(long id) {
        String sql = "DELETE FROM boarding_pass WHERE id = ?";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setLong(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao remover boarding pass: " + e.getMessage());
            return false;
        }
    }
}
