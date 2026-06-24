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
public class DespachoBagagemDAO {

    public boolean adiciona(DespachoBagagem d) {
        String sql = "INSERT INTO despacho_bagagem (ticket_id, documento) VALUES (?, ?)";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setLong(1, d.getTicketId());
            stmt.setString(2, d.getDocumento());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    d.setId(rs.getLong(1));
                }
                return true;
            }
            return false;

        } catch (SQLException e) {
            System.err.println("Erro ao adicionar despacho de bagagem: " + e.getMessage());
            return false;
        }
    }

    public List<DespachoBagagem> mostrarTodos() {
        List<DespachoBagagem> lista = new ArrayList<>();
        String sql = "SELECT * FROM despacho_bagagem ORDER BY id";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                DespachoBagagem d = new DespachoBagagem();
                d.setId(rs.getLong("id"));
                d.setTicketId(rs.getLong("ticket_id"));
                d.setDocumento(rs.getString("documento"));

                Timestamp criacao = rs.getTimestamp("data_criacao");
                if (criacao != null) {
                    d.setDataCriacao(criacao.toLocalDateTime());
                }

                lista.add(d);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar despachos: " + e.getMessage());
        }

        return lista;
    }

    public DespachoBagagem buscaPorId(long id) {
        String sql = "SELECT * FROM despacho_bagagem WHERE id = ?";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                DespachoBagagem d = new DespachoBagagem();
                d.setId(rs.getLong("id"));
                d.setTicketId(rs.getLong("ticket_id"));
                d.setDocumento(rs.getString("documento"));

                Timestamp criacao = rs.getTimestamp("data_criacao");
                if (criacao != null) {
                    d.setDataCriacao(criacao.toLocalDateTime());
                }

                return d;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar despacho por ID: " + e.getMessage());
        }

        return null;
    }

    public boolean removerPorId(long id) {
        String sql = "DELETE FROM despacho_bagagem WHERE id = ?";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setLong(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao remover despacho: " + e.getMessage());
            return false;
        }
    }
}
