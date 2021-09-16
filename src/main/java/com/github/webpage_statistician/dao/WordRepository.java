package com.github.webpage_statistician.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.webpage_statistician.dao.entity.Word;

public interface WordRepository extends JpaRepository<Word, Integer> {
}