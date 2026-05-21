package com.callofdevops.backend.repository;

import com.callofdevops.backend.entity.ReturnRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReturnRecordRepository extends JpaRepository<ReturnRecord, Long> {
}
