package com.uet.spring.practice.repository;

import com.uet.spring.practice.model.tag.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends CrudRepository<Tag, Integer> {}
