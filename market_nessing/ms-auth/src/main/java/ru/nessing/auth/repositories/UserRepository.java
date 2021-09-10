package ru.nessing.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nessing.auth.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

}
