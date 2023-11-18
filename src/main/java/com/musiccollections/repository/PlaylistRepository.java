package com.musiccollections.repository;

import com.musiccollections.model.Playlist;
import com.musiccollections.model.Song;
import com.musiccollections.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {

    @Query("SELECT p FROM Playlist p WHERE p.user = :user")
    List<Playlist> findByUser(@Param("user") User user);

    @Query("SELECT p FROM Playlist p WHERE p.id = :id")
    Playlist findId(@Param("id") Integer id);

    @Modifying
    @Query("DELETE FROM Playlist p WHERE p.id = :id")
    void deleteById(@Param("id")Integer id);


}
