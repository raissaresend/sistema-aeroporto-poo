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
public class VooAssentosDAO {

    public boolean adiciona(VooAssentos va) {
        String sql = "INSERT INTO voo_assentos (voo_id, cod_assento, passageiro_id) VALUES (?, ?, ?)";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setLong(1, va.getVooId());
            stmt.setLong(2, va.getCodAssento());
            stmt.setLong(3, va.getPassageiroId());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    va.setId(rs.getLong(1));
                }
                return true;
            }
            return false;

        } catch (SQLException e) {
            System.err.println("Erro ao adicionar assento: " + e.getMessage());
            return false;
        }
    }

    public List<VooAssentos> mostrarTodos() {
        List<VooAssentos> lista = new ArrayList<>();
        String sql = "SELECT * FROM voo_assentos ORDER BY id";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                VooAssentos va  = new VooAssentos();
                va.setId(rs.getLong("id"));
                va.setVooId(rs.getLong("voo_id"));
                va.setCodAssento(rs.getLong("cod_assento"));
                va.setPassageiroId(rs.getLong("passageiro_id"));

                lista.add(va);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar assentos: " + e.getMessage());
        }

        return lista;
    }

    public VooAssentos buscaPorId(long id) {
        String sql = "SELECT * FROM voo_assentos WHERE id = ?";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                VooAssentos va  = new VooAssentos();
                va.setId(rs.getLong("id"));
                va.setVooId(rs.getLong("voo_id"));
                va.setCodAssento(rs.getLong("cod_assento"));
                va.setPassageiroId(rs.getLong("passageiro_id"));
                return va;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar assento por ID: " + e.getMessage());
        }

        return null;
    }

    public VooAssentos buscaPorPassageiroEVoo(long passageiroId, long vooId) {
        String sql = "SELECT * FROM voo_assentos WHERE passageiro_id = ? AND voo_id = ?";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setLong(1, passageiroId);
            stmt.setLong(2, vooId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                VooAssentos va  = new VooAssentos();
                va.setId(rs.getLong("id"));
                va.setVooId(rs.getLong("voo_id"));
                va.setCodAssento(rs.getLong("cod_assento"));
                va.setPassageiroId(rs.getLong("passageiro_id"));
                return va;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar assento: " + e.getMessage());
        }

        return null;
    }

    public boolean removerPorId(long id) {
        String sql = "DELETE FROM voo_assentos WHERE id = ?";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setLong(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao remover assento: " + e.getMessage());
            return false;
        }
    }
}
