package com.uet.spring.practice.repository;

import com.uet.spring.practice.model.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
    Optional<User> findByName(String name);

    @Query("SELECT u FROM User u WHERE u.name = ?1")
    Optional<User> findByNameV2(String name);

    @Query(value = "select * from user u where u.name = :name", nativeQuery = true)
    Optional<User> findByNameV3(@Param("name") String name);
}
