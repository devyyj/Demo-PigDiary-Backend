package com.devyyj.pigdiary.board.controller;

import com.devyyj.pigdiary.board.dto.FreeBoardDTO;
import com.devyyj.pigdiary.board.dto.PageRequestDTO;
import com.devyyj.pigdiary.board.dto.PageResultDTO;
import com.devyyj.pigdiary.board.service.FreeBoardServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/freeboard")
@Log4j2
@RequiredArgsConstructor
public class FreeBoardController {
    private final FreeBoardServiceImpl freeBoardService;

    // 게시글 목록 조회
    @GetMapping({"", "/"})
    public ResponseEntity<PageResultDTO> list(PageRequestDTO pageRequestDTO) {
        log.info("list...........................");

        return new ResponseEntity<>(freeBoardService.getList(pageRequestDTO), HttpStatus.OK);
    }

    // 게시글 생성
    @PostMapping({"", "/"})
    public ResponseEntity<Long> createPost(FreeBoardDTO freeBoardDTO) {
        log.info("create...........................");

        return new ResponseEntity<>(freeBoardService.createPost(freeBoardDTO), HttpStatus.OK);
    }

    // 게시글 조회
    @GetMapping("/{postNumber}")
    public ResponseEntity<FreeBoardDTO> readPost(@PathVariable Long postNumber, PageRequestDTO pageRequestDTO) {
        log.info("readPost...........................");

        return new ResponseEntity<>(freeBoardService.getPost(postNumber), HttpStatus.OK);
    }

    // 게시글 수정
    @PutMapping("/{postNumber}")
    public ResponseEntity<String> updatePost(@PathVariable Long postNumber, FreeBoardDTO freeBoardDTO, PageRequestDTO pageRequestDTO) {
        log.info("updatePost...........................");

        freeBoardService.update(postNumber, freeBoardDTO);
        return new ResponseEntity<>(postNumber + " post updated.", HttpStatus.OK);
    }

    // 게시글 삭제
    @DeleteMapping("/{postNumber}")
    public ResponseEntity<String> deletePost(@PathVariable Long postNumber, PageRequestDTO pageRequestDTO) {
        log.info("deletePost...........................");
        freeBoardService.delete(postNumber);
        return new ResponseEntity<>(postNumber + " post deleted.", HttpStatus.OK);
    }
}
