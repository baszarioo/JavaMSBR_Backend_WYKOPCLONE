package com.b1lbudinhox.wykopclone.repositories;

import com.b1lbudinhox.wykopclone.models.Thread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ThreadRepository extends JpaRepository<Thread, Long> {
    Optional<Thread> findByName(String threadName);
}
