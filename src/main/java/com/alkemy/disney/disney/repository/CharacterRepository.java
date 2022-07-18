package com.alkemy.disney.disney.repository;

import com.alkemy.disney.disney.entity.CharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<CharacterEntity, Long> {
}
