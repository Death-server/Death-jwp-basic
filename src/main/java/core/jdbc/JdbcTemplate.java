package core.jdbc;

import next.exception.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {

    private final static JdbcTemplate jdbcTemplate = new JdbcTemplate();
    private JdbcTemplate() {}

    public static JdbcTemplate of() {
        return jdbcTemplate;
    }
    public void update(String sql, Object... parameters) throws DataAccessException {
        PreparedStatementSetter pss = createPreparedStatementSetter(parameters);
        update(sql, pss);
    }

    public void update(String sql, PreparedStatementSetter pss) throws DataAccessException {
        try (Connection con = ConnectionManager.getConnection(); PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            setValuesForUpdate(preparedStatement, pss);
        } catch(SQLException e) {
            throw new DataAccessException(e);
        }
    }

    public void update(PreparedStatementCreator psc, KeyHolder holder) {
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement ps = psc.createPreparedStatement(conn);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                holder.setId(rs.getLong(1));
            }
            rs.close();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }



    private void setValuesForUpdate(PreparedStatement preparedStatement, PreparedStatementSetter pss) throws DataAccessException {
        try {
            pss.setValues(preparedStatement);
            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            throw new DataAccessException(e);
        }
    }

    public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters) throws DataAccessException {
        ResultSet rs;
        PreparedStatementSetter pss = createPreparedStatementSetter(parameters);
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pss.setValues(pstmt);
            rs =  pstmt.executeQuery();
            List<T> objects = new ArrayList<>();
            while (rs.next()) {
                objects.add(rowMapper.mapRow(rs));
            }
            return objects;
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    public <T> T queryForObject(String sql, RowMapper<T> rowMapper, Object... parameters) throws DataAccessException {
        return queryForObject(sql, rowMapper, createPreparedStatementSetter(parameters));
    }

    public <T> T queryForObject(String sql, RowMapper<T> rowMapper, PreparedStatementSetter pss) throws DataAccessException {
        ResultSet rs = null;

        try(Connection con = ConnectionManager.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            pss.setValues(pstmt);
            rs = pstmt.executeQuery();
            T object = null;
            if(rs.next()) {
                object = rowMapper.mapRow(rs);
            }
            return object;
        } catch(SQLException e) {
            throw new DataAccessException(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch(SQLException e) {
                throw new DataAccessException(e);
            }
        }
    }

    private PreparedStatementSetter createPreparedStatementSetter(Object... parameters) {
        return (preparedStatement) -> {
            for(int i = 0; i < parameters.length; i++) {
                preparedStatement.setObject(i + 1, parameters[i]);
            }
        };
    }
}
