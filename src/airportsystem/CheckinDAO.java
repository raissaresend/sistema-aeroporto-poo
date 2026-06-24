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
public class CheckinDAO {

    public boolean adiciona(Checkin c) {
        String sql = "INSERT INTO checkins (ticket_id, documento) VALUES (?, ?)";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setLong(1, c.getTicketId());
            stmt.setString(2, c.getDocumento());

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
            System.err.println("Erro ao adicionar checkin: " + e.getMessage());
            return false;
        }
    }

    public List<Checkin> mostrarTodos() {
        List<Checkin> lista = new ArrayList<>();
        String sql = "SELECT * FROM checkins ORDER BY id";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Checkin c = new Checkin();
                c.setId(rs.getLong("id"));
                c.setTicketId(rs.getLong("ticket_id"));
                c.setDocumento(rs.getString("documento"));

                Timestamp criacao = rs.getTimestamp("data_criacao");
                if (criacao != null) {
                    c.setDataCriacao(criacao.toLocalDateTime());
                }

                lista.add(c);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar checkins: " + e.getMessage());
        }

        return lista;
    }

    public Checkin buscaPorId(long id) {
        String sql = "SELECT * FROM checkins WHERE id = ?";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Checkin c = new Checkin();
                c.setId(rs.getLong("id"));
                c.setTicketId(rs.getLong("ticket_id"));
                c.setDocumento(rs.getString("documento"));

                Timestamp criacao = rs.getTimestamp("data_criacao");
                if (criacao != null) {
                    c.setDataCriacao(criacao.toLocalDateTime());
                }

                return c;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar checkin por ID: " + e.getMessage());
        }

        return null;
    }

    public boolean removerPorId(long id) {
        String sql = "DELETE FROM checkins WHERE id = ?";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setLong(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao remover checkin: " + e.getMessage());
            return false;
        }
    }
}
