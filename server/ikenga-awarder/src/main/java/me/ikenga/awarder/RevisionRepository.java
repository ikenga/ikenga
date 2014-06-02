package me.ikenga.awarder;

import org.springframework.data.repository.CrudRepository;

public interface RevisionRepository extends
        CrudRepository<RevisionEntity, Long> {

    RevisionEntity findByHostAndPath(String host, String path);

}
