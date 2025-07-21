package com.ryohaseu.Practice_Memo_App.repository;

import com.ryohaseu.Practice_Memo_App.model.MemoModel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemoRepository {
    private final JdbcTemplate jdbcTemplate;

    public MemoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<MemoModel> memoModelRowMapper = (rs, rowNum) ->
            new MemoModel(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("content")
            );

    // 전체 조회 레포지토리
    public List<MemoModel> findAll() {
        return jdbcTemplate.query(
                "SELECT * FROM memo ORDER BY id DESC",
                memoModelRowMapper
        );
    }

    public MemoModel findById(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM memo WHERE id = ?",
                memoModelRowMapper,
                id
        );
    }

    // 등록 처리 레포지토리
    public void save(String title, String content) {
        jdbcTemplate.update(
                "INSERT INTO memo (title, content) VALUES (?, ?)",
                title,
                content
        );
    }

    // 수정 레포지토리
    public void update(int id, String title, String content) {
        jdbcTemplate.update(
                "UPDATE memo SET title = ?, content = ? WHERE id = ?",
                title,
                content,
                id
        );
    }

    // 삭제 레포지토리
    public void delete(int id) {
        jdbcTemplate.update(
                "DELETE FROM memo WHERE id = ?",
                id
        );
    }

}
