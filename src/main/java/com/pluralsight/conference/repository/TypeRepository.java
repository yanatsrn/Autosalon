package com.pluralsight.conference.repository;

import com.pluralsight.conference.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {
    Type findByTypeId(int id);
}
