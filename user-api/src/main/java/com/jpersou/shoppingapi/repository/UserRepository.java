package com.jpersou.shoppingapi.repository;

import com.jpersou.shoppingapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByDocumentAndKey(String doc, String key);

    List<User> queryByNameLike(String name);
}
