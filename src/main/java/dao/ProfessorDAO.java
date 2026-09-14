package dao;

import factory.ConnectionFactory;
import model.Professor;
import java.sql.ResultSet;
import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProfessorDAO implements GenericDAO<Professor, Integer> {
    private int gerarProximoId(Connection c) throws SQLException {
        String sql = "select max(id_professor) from java_professor";
        try (PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                if (rs.getObject(1) == null) {
                    return 1000;
                } else {
                    return rs.getInt(1) + 1;
                }
            }
        }
        return 1000;
    }

    public void inserir(Professor entidade) {
        String sql = "insert into java_professor(id_professor, nome, aulas_semanais, valor_hora, salario) values (?, ?, ?, ?, ?)";
        try (Connection connection = ConnectionFactory.obterConexao()) {
            int novoId = gerarProximoId(connection);
            entidade.setIdProfessor(novoId);
            entidade.calcularSalarioTotal();
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, entidade.getIdProfessor());
                ps.setString(2, entidade.getNome());
                ps.setInt(3, entidade.getAulasSemanais());
                ps.setDouble(4, entidade.getValorHora());
                ps.setDouble(5, entidade.getSalario());
                ps.execute();
            }

            }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
    public List<Professor> listar(){
        List<Professor> lista = new ArrayList<>();
        String sql = "select * from java_professor";
        try(Connection connection = ConnectionFactory.obterConexao(); PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()){
            while (rs.next()){
               Professor p = new Professor();
               p.setIdProfessor(rs.getInt("id_professor"));
               p.setNome(rs.getString("nome"));
               p.setAulasSemanais(rs.getInt("aulas_semanais"));
               p.setValorHora(rs.getDouble("valor_hora"));
               p.setSalario(rs.getDouble("salario"));
               lista.add(p);

            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return lista;
    }
    public Optional<Professor> buscarPorId(Integer id){
        String sql="select * from java_professor where id_professor = ?";
        try(Connection connection= ConnectionFactory.obterConexao(); PreparedStatement ps= connection.prepareStatement(sql)){
            ps.setInt(1,id);
            try(ResultSet rs =ps.executeQuery()){
                if(rs.next()){
                    Professor p = new Professor();
                    p.setIdProfessor(rs.getInt("id_professor"));
                    p.setNome(rs.getString("nome"));
                    p.setAulasSemanais(rs.getInt("aulas_semanais"));
                    p.setValorHora(rs.getDouble("valor_hora"));
                    p.setSalario(rs.getDouble("salario"));

                    return Optional.of(p);
                }
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }
    public void deletar(Professor professor){
        String sql= "delete from java_professor where id=?";
        try(Connection connection=ConnectionFactory.obterConexao(); PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, professor.getIdProfessor());
            ps.execute();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

}
