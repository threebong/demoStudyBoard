package com.example.demo.web.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class BoardDto implements Serializable {
	private int idx;
    private String title;
    private String contents;
    private String author;
    private String createdAt;
}
