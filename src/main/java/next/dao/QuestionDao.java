package next.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.KeyHolder;
import core.jdbc.PreparedStatementCreator;
import core.jdbc.RowMapper;
import next.model.Question;

import java.sql.*;
import java.util.List;

public class QuestionDao {

    public void insert(Question question) {
        JdbcTemplate jdbcTemplate = JdbcTemplate.of();
        String sql = "INSERT INTO QUESTIONS (writer, title, contents, createdDate, countOfAnswer) VALUES (?, ?, ?, ?,?)";

        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setString(1, question.getWriter());
                pstmt.setString(2, question.getTitle());
                pstmt.setString(3, question.getContents());
                pstmt.setTimestamp(4,new Timestamp(question.getTimeFromCreateDate()));
                pstmt.setLong(5, 0);

                return pstmt;
            }
        };

        KeyHolder keyHolder = new KeyHolder();
        jdbcTemplate.update(psc, keyHolder);
    }

    public void update(Long id, boolean isAdd) {
        JdbcTemplate jdbcTemplate = JdbcTemplate.of();
        String sql = "UPDATE QUESTIONS SET countOfAnswer = countOfAnswer + 1 where questionId = ?";

        if(!isAdd) {
            sql = "UPDATE QUESTIONS SET countOfAnswer = countOfAnswer - 1 where questionId = ?";
        }
        jdbcTemplate.update(sql, preparedStatement -> {
            preparedStatement.setLong(1, id);
        });
    }

    public void update(Long id, Question question) {
        JdbcTemplate jdbcTemplate = JdbcTemplate.of();
        String sql = "UPDATE QUESTIONS SET writer=?, title=?, contents=? where questionId = ?";

        jdbcTemplate.update(sql, preparedStatement -> {
            preparedStatement.setString(1, question.getWriter());
            preparedStatement.setString(2, question.getTitle());
            preparedStatement.setString(3, question.getContents());
            preparedStatement.setLong(4,id);
        });
    }

    public List<Question> findAll() {
        JdbcTemplate jdbcTemplate = JdbcTemplate.of();
        String sql = "SELECT questionId, writer, title, createdDate, countOfAnswer FROM QUESTIONS "
                + "order by questionId desc";

        RowMapper<Question> rm = new RowMapper<Question>() {
            @Override
            public Question mapRow(ResultSet rs) throws SQLException {
                return new Question(rs.getLong("questionId"), rs.getString("writer"), rs.getString("title"), null,
                        rs.getTimestamp("createdDate"), rs.getInt("countOfAnswer"));
            }

        };

        return jdbcTemplate.query(sql, rm);
    }

    public Question findById(long questionId) {
        JdbcTemplate jdbcTemplate = JdbcTemplate.of();
        String sql = "SELECT questionId, writer, title, contents, createdDate, countOfAnswer FROM QUESTIONS "
                + "WHERE questionId = ?";

        RowMapper<Question> rm = new RowMapper<Question>() {
            @Override
            public Question mapRow(ResultSet rs) throws SQLException {
                return new Question(rs.getLong("questionId"), rs.getString("writer"), rs.getString("title"),
                        rs.getString("contents"), rs.getTimestamp("createdDate"), rs.getInt("countOfAnswer"));
            }
        };

        return jdbcTemplate.queryForObject(sql, rm, questionId);
    }
}
