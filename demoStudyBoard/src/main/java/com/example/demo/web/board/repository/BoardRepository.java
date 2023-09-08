package com.example.demo.web.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.web.board.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

}
