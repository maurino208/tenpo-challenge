package com.tenpo.challenge.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;
import java.util.UUID;

import static jakarta.persistence.GenerationType.AUTO;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestHttp {
    @Id
    @GeneratedValue(strategy = AUTO)
    private UUID id;
    private String method;
    private String uri;
    private String queryString;
    @Column(length = 2500)
    private String headers;
    @Column(length = 5000)
    private String request;
    @Column(length = 5000)
    private String response;
    private int httpStatus;
    @CreationTimestamp
    private OffsetDateTime date;
}
