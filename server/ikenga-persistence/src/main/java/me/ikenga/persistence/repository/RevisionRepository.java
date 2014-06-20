package me.ikenga.persistence.repository;

import me.ikenga.persistence.entity.RevisionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RevisionRepository extends
        JpaRepository<RevisionEntity, Long> {

    RevisionEntity findByHostAndPath(String host, String path);

}
