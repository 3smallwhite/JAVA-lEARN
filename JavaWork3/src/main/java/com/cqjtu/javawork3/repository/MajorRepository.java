package com.cqjtu.javawork3.repository;

import com.cqjtu.javawork3.pojo.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MajorRepository extends JpaRepository<Major, String> {
    @Query(value = "select m from Major m where m.name = :name")
    Major findByName(String name);

    @Query(value = "select m from Major m where m.name like :findCondition")
    List<Major> findByFindCondition(String findCondition);

    @Query(value = "select max(m.id) from Major m")
    Long findMaxId();

    @Query(value = "select m from Major m where m.id = :id")
    Major findByStringId(String id);
}
