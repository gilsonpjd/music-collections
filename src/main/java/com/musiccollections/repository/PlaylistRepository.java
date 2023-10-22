package com.musiccollections.repository;

import com.musiccollections.model.Playlist;
import com.musiccollections.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {

    List<Playlist> findByUser(User user);


}
