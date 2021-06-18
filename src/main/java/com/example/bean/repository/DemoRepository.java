package com.example.bean.repository;

import com.example.bean.entity.DemoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemoRepository extends JpaRepository<DemoEntity, Long> {

}
