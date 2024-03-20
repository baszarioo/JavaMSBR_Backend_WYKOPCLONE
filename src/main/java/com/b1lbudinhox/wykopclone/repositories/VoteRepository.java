package com.b1lbudinhox.wykopclone.repositories;

import com.b1lbudinhox.wykopclone.models.Post;
import com.b1lbudinhox.wykopclone.models.User;
import com.b1lbudinhox.wykopclone.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findTopByOrderByVoteIdDesc(Post post, User currentUser);
    void deleteVoteByPostAndUser(Post post, User currentUser);
}
