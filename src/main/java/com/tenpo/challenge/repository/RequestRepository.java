package com.tenpo.challenge.repository;

import com.tenpo.challenge.model.RequestHttp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RequestRepository extends JpaRepository<RequestHttp, UUID> {
}
