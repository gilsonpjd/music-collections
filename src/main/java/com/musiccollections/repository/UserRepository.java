package com.musiccollections.repository;


import com.musiccollections.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface UserRepository extends JpaRepository<User, Integer> {

    @Override
    @Query("SELECT u FROM User u")
    List<User> findAll();
    @Query("SELECT u FROM User u WHERE u.id = :id")
    User findId(@NonNull @Param("id") Integer id);
    @Modifying
    @Query("DELETE FROM User u WHERE u.id = :id")
    void deleteById(@NonNull @Param("id")Integer id);

}
