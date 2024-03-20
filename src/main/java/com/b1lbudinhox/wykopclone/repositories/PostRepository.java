package com.b1lbudinhox.wykopclone.repositories;

import com.b1lbudinhox.wykopclone.models.Post;
import com.b1lbudinhox.wykopclone.models.Thread;
import com.b1lbudinhox.wykopclone.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByTag(Thread thread);

    List<Post> findByUser(User user);
}
