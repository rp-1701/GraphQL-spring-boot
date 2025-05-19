package com.interview.practice.graphqlspringboot.repository;

import com.interview.practice.graphqlspringboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
