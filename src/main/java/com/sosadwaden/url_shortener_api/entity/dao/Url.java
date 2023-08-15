package com.sosadwaden.url_shortener_api.entity.dao;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "url")
public class Url {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "short_url")
    private String shortUrl;

    @Column(name = "long_url", nullable = false)
    private String longUrl;

    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    @Column(name = "expiration_date")
    private Date expirationDate;
}
