package com.sosadwaden.url_shortener_api.repository;

import com.sosadwaden.url_shortener_api.entity.dao.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
}
