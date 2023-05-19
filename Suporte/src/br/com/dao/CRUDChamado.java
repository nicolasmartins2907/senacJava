package br.com.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dominio.Chamado;

public class CRUDChamado extends Conexao implements CRUD<Chamado> {
    @Override
    public String registrar(Chamado obj) {
        String txt = "";

        try {
            abrirConexao();
            String sql = "INSERT INTO chamado(solicitacao, departamentosolicitado, descricaochamado) VALUES (?, ?, ?)";
            pst = con.prepareStatement(sql);

            pst.setString(1, obj.getSolicitacao());
            pst.setString(2, obj.getDepSolicitado());
            pst.setString(3, obj.getDescChamado());

            int i = pst.executeUpdate();
            if (i > 0) {
                txt = "Chamado solicitado com sucesso.";
            } else {
                txt = "Não foi possível realizar o chamado.";
            }
        } catch (SQLException se) {
            txt = "Erro de SQL -> " + se.getMessage();
        } catch (Exception e) {
            txt = "Erro inesperado -> " + e.getMessage();
        } finally {
            fecharConexao();
        }
        return txt;
    }

    @Override
    public List<Chamado> listar() {
        List<Chamado> lstChamado = new ArrayList<Chamado>();
        try {
            abrirConexao();
            String sql = "SELECT * FROM chamado";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                Chamado chamado = new Chamado();
                chamado.setIdChamado(rs.getLong(1));
                chamado.setSolicitacao(rs.getString(2));
                chamado.setDepSolicitado(rs.getString(3));
                chamado.setDescChamado(rs.getString(4));
                chamado.setDataAbertura(rs.getString(5));
                chamado.setDataResolucao(rs.getString(6));
                chamado.setStatusChamado(rs.getString(7));
                chamado.setAtendente(rs.getString(8));
                lstChamado.add(chamado);
            }

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fecharConexao();
        }

        return lstChamado;
    }

    @Override
    public List<Chamado> listar(Chamado obj) {
        List<Chamado> lstChamado = new ArrayList<Chamado>();

        try {
            abrirConexao();
            String sql = "";

            if (obj.getIdChamado() != 0) {
                sql = "SELECT * FROM chamado WHERE statusChamado = 'Pendente' and idchamado = " + obj.getIdChamado();
            } else if (obj.getDepSolicitado() != null) {
                sql = "SELECT * FROM chamado WHERE statusChamado = 'Pendente' and departamentosolicitado like '" + obj.getDepSolicitado() + "'";
            } else if (obj.getSolicitacao() != null) {
                sql = "SELECT * FROM chamado WHERE statusChamado = 'Pendente' and solicitacao like '" + obj.getSolicitacao() + "%'";
            } else if (obj.getDescChamado() != null) {
                sql = "SELECT * FROM chamado WHERE statusChamado = 'Pendente' and descricaochamado like '%" + obj.getDescChamado() + "%'";
            } else if (obj.getDataAbertura() != null) {
                sql = "SELECT * FROM chamado WHERE statusChamado = 'Pendente' and dataabertura like '" + obj.getDataAbertura() + "'";
            } else if (obj.getStatusChamado() != null) {
                sql = "SELECT * FROM chamado WHERE statusChamado = 'Pendente' and statuschamado like '" + obj.getStatusChamado() + "'";
            } else {
                sql = "SELECT * FROM chamado";
            }

            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                Chamado chamado = new Chamado();
                chamado.setIdChamado(rs.getLong(1));
                chamado.setSolicitacao(rs.getString(2));
                chamado.setDepSolicitado(rs.getString(3));
                chamado.setDescChamado(rs.getString(4));
                chamado.setDataAbertura(rs.getString(5));
                chamado.setDataResolucao(rs.getString(6));
                chamado.setStatusChamado(rs.getString(7));
                chamado.setAtendente(rs.getString(8));
                lstChamado.add(chamado);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fecharConexao();
        }

        return lstChamado;
    }

    @Override
    public Chamado atualizar(Chamado obj) {
        Chamado chamado = new Chamado();
        try {
            abrirConexao();
            String sql = "UPDATE chamado SET dataresolucao=?, statuschamado=?, atendente=?, observacoes=? WHERE idchamado=?";
            pst = con.prepareStatement(sql);
            pst.setString(1, obj.getDataResolucao());
            pst.setString(2, obj.getStatusChamado());
            pst.setString(3, obj.getAtendente());
            pst.setString(4, obj.getObservacoes());
            pst.setLong(5, obj.getIdChamado());

            int i = pst.executeUpdate();
            if (i > 0) {
                chamado = obj;
            } else {
                throw new Exception("Não foi possível atualizar os dados");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fecharConexao();
        }
        return chamado;
    }

    @Override
    public String apagar(Chamado obj) {
        String txt = "";
        try {
            abrirConexao();
            String sql = "DELETE FROM chamado WHERE idchamado=?";
            pst = con.prepareStatement(sql);
            pst.setLong(1, obj.getIdChamado());

            int i = pst.executeUpdate();
            if (i > 0) {
                txt = "Chamado apagado.";
            } else {
                txt = "Não foi possível apagar o chamado.";
            }
        } catch (SQLException se) {
            txt = "Erro na consulta de SQL -> " + se.getMessage();
        } catch (Exception e) {
            txt = "Ocorreu um erro inesperado durante a consulta -> " + e.getMessage();
        } finally {
            fecharConexao();
        }
        return txt;
    }
}
