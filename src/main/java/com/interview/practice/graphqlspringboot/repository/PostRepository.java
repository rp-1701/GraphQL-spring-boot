package com.interview.practice.graphqlspringboot.repository;

import com.interview.practice.graphqlspringboot.entity.Post;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<List<Post>> findPostByUserId(Long userId);

}
