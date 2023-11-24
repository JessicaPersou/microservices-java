package com.jpersou.userapi.repository;

import com.jpersou.userapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByDocument(String doc);

    List<User> queryByNameLike(String name);
}
