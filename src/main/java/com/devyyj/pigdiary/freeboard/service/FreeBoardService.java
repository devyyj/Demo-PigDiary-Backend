package com.devyyj.pigdiary.freeboard.service;

import com.devyyj.pigdiary.freeboard.dto.FreeBoardDTO;
import com.devyyj.pigdiary.freeboard.dto.PageRequestDTO;
import com.devyyj.pigdiary.freeboard.dto.PageResultDTO;
import com.devyyj.pigdiary.freeboard.entity.FreeBoard;

public interface FreeBoardService {
    // 게시글 목록
    PageResultDTO<FreeBoardDTO, FreeBoard> getList(PageRequestDTO pageRequestDTO);

    // 게시글 생성
    Long createPost(FreeBoardDTO dto);

    // 게시글 내용
    FreeBoardDTO getPost(Long postNumber);

    // 게시글 수정
    void update(Long postNumber, FreeBoardDTO freeBoardDTO);

    // 게시글 삭제
    void delete(Long postNumber);

    default FreeBoard dtoToEntity(FreeBoardDTO dto) {
        return FreeBoard
                .builder()
                .number(dto.getNumber())
                .title(dto.getTitle())
                .content(dto.getContent())
                .user(dto.getUser())
                .build();
    }

    default FreeBoardDTO entityToDto(FreeBoard entity) {
        return FreeBoardDTO.builder()
                .number(entity.getNumber())
                .title(entity.getTitle())
                .content(entity.getContent())
                .user(entity.getUser())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .delDate(entity.getDelDate())
                .build();
    }


}
