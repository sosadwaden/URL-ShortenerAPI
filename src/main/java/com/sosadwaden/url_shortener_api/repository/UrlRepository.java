package com.sosadwaden.url_shortener_api.repository;

import com.sosadwaden.url_shortener_api.entity.dao.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {

    /**
     * Запрос для поиска минимального не занятого id в таблице url
     * @return id
     */
    @Query("""
            SELECT (t.id + 1)                     \s
            FROM Url AS t                         \s
            LEFT JOIN Url s on s.id = (t.id + 1)  \s
            WHERE s.id IS NULL                    \s
            ORDER BY t.id                         \s
            LIMIT 1""")
    long findFirstMinId();
}
