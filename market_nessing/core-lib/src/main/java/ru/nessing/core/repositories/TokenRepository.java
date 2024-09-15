package ru.nessing.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<String, String> {

    Boolean findStringBY(String s);
}
