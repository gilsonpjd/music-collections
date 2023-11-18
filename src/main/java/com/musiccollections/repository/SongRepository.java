package com.musiccollections.repository;

import com.musiccollections.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface SongRepository extends JpaRepository<Song, Integer> {

    @Override
    @Query("SELECT s FROM Song s")
    List<Song> findAll();
    @Query("SELECT s FROM Song s WHERE s.id = :id")
    Song findId(@NonNull @Param("id") Integer id);
    @Modifying
    @Query("DELETE FROM Song s WHERE s.id = :id")
    void deleteById(@NonNull @Param("id")Integer id);


}
