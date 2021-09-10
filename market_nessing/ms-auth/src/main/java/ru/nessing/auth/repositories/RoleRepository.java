package ru.nessing.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nessing.auth.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String name);

}
