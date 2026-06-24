/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airportsystem;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author RAISSA
 */
public class VooDAO {

    public boolean adiciona(Voo v) {
        String sql = "INSERT INTO voos (origem, destino, data_voo, duracao, cia, capacidade, status) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, v.getOrigem());
            stmt.setString(2, v.getDestino());
            stmt.setTimestamp(3, v.getData() != null ? Timestamp.valueOf(v.getData()) : null);
            stmt.setTime(4, v.getDuracao() != null ? Time.valueOf(v.getDuracao()) : null);
            stmt.setString(5, v.getCia());
            stmt.setLong(6, v.getCapacidade());
            stmt.setString(7, v.getStatus());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    v.setId(rs.getLong(1));
                }
                return true;
            }
            return false;

        } catch (SQLException e) {
            System.err.println("Erro ao adicionar voo: " + e.getMessage());
            return false;
        }
    }

    public List<Voo> mostrarTodos() {
        List<Voo> lista = new ArrayList<>();
        String sql = "SELECT * FROM voos ORDER BY data_voo";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Voo v = new Voo();
                v.setId(rs.getLong("id"));
                v.setOrigem(rs.getString("origem"));
                v.setDestino(rs.getString("destino"));

                Timestamp dataVoo = rs.getTimestamp("data_voo");
                if (dataVoo != null) {
                    v.setData(dataVoo.toLocalDateTime());
                }

                Time duracao = rs.getTime("duracao");
                if (duracao != null) {
                    v.setDuracao(duracao.toLocalTime());
                }

                v.setCia(rs.getString("cia"));
                v.setCapacidade(rs.getLong("capacidade"));
                v.setStatus(rs.getString("status"));

                lista.add(v);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar voos: " + e.getMessage());
        }

        return lista;
    }

    public Voo buscaPorId(long id) {
        String sql = "SELECT * FROM voos WHERE id = ?";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Voo v = new Voo();
                v.setId(rs.getLong("id"));
                v.setOrigem(rs.getString("origem"));
                v.setDestino(rs.getString("destino"));

                Timestamp dataVoo = rs.getTimestamp("data_voo");
                if (dataVoo != null) {
                    v.setData(dataVoo.toLocalDateTime());
                }

                Time duracao = rs.getTime("duracao");
                if (duracao != null) {
                    v.setDuracao(duracao.toLocalTime());
                }

                v.setCia(rs.getString("cia"));
                v.setCapacidade(rs.getLong("capacidade"));
                v.setStatus(rs.getString("status"));

                return v;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar voo por ID: " + e.getMessage());
        }

        return null;
    }

    public boolean removerPorId(long id) {
        String sql = "DELETE FROM voos WHERE id = ?";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setLong(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao remover voo: " + e.getMessage());
            return false;
        }
    }

    public List<Voo> buscarVoos(String origem, String destino) {
        List<Voo> lista = new ArrayList<>();
        String sql = "SELECT * FROM voos WHERE origem LIKE ? AND destino LIKE ? ORDER BY data_voo";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, "%" + origem + "%");
            stmt.setString(2, "%" + destino + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Voo v = new Voo();
                v.setId(rs.getLong("id"));
                v.setOrigem(rs.getString("origem"));
                v.setDestino(rs.getString("destino"));

                Timestamp dataVoo = rs.getTimestamp("data_voo");
                if (dataVoo != null) {
                    v.setData(dataVoo.toLocalDateTime());
                }

                Time duracao = rs.getTime("duracao");
                if (duracao != null) {
                    v.setDuracao(duracao.toLocalTime());
                }

                v.setCia(rs.getString("cia"));
                v.setCapacidade(rs.getLong("capacidade"));
                v.setStatus(rs.getString("status"));

                lista.add(v);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar voos: " + e.getMessage());
        }

        return lista;
    }

    public List<Voo> buscarVoosPorData(LocalDate data) {
        List<Voo> lista = new ArrayList<>();
        String sql = "SELECT * FROM voos WHERE DATE(data_voo) = ? ORDER BY data_voo";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(data));
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Voo v = new Voo();
                v.setId(rs.getLong("id"));
                v.setOrigem(rs.getString("origem"));
                v.setDestino(rs.getString("destino"));

                Timestamp dataVoo = rs.getTimestamp("data_voo");
                if (dataVoo != null) {
                    v.setData(dataVoo.toLocalDateTime());
                }

                Time duracao = rs.getTime("duracao");
                if (duracao != null) {
                    v.setDuracao(duracao.toLocalTime());
                }

                v.setCia(rs.getString("cia"));
                v.setCapacidade(rs.getLong("capacidade"));
                v.setStatus(rs.getString("status"));

                lista.add(v);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar voos por data: " + e.getMessage());
        }

        return lista;
    }

    public boolean atualizarCapacidade(long id, long novaCapacidade) {
        String sql = "UPDATE voos SET capacidade = ? WHERE id = ?";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setLong(1, novaCapacidade);
            stmt.setLong(2, id);

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar capacidade do voo: " + e.getMessage());
            return false;
        }
    }
}
