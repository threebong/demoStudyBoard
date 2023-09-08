package com.example.demo.web.board.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import com.example.demo.web.board.dto.BoardDto;
import com.example.demo.web.board.entity.BoardEntity;
import com.example.demo.web.board.service.BoardService;


import java.util.List;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RestController
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/api/board/list")    
    public List<BoardDto> boardList() { 
    	
    	return boardService.getBoardList();    	
    	}

    @GetMapping("api/board/{id}")
    public BoardDto getBoard(@PathVariable Long id) {
        return boardService.getBoard(id);
    }

    @PostMapping("api/board")
    public BoardEntity create(@RequestBody BoardDto boardDto) {
        return boardService.create(boardDto);
    }

    @PatchMapping("api/board")
    public BoardEntity update(@RequestBody BoardDto boardDto) {
        return boardService.update(boardDto);
    }

    @DeleteMapping("api/board/{id}")
    public void delete(@PathVariable Long id) {
        boardService.delete(id);  
         
    }
}