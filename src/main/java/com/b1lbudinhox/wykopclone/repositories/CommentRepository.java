package com.b1lbudinhox.wykopclone.repositories;

import com.b1lbudinhox.wykopclone.models.Comment;
import com.b1lbudinhox.wykopclone.models.Post;
import com.b1lbudinhox.wykopclone.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);
    List<Comment> findAllByUser(User user);
}
