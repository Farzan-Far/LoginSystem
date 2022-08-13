package com.farzan.Repository;

import com.farzan.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Repo extends JpaRepository<User, Long>
{
      User findByEmail(String email);
}
