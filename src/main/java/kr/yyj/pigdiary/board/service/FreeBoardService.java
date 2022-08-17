package kr.yyj.pigdiary.board.service;

import kr.yyj.pigdiary.board.dto.FreeBoardDTO;
import kr.yyj.pigdiary.board.dto.PageRequestDTO;
import kr.yyj.pigdiary.board.dto.PageResultDTO;
import kr.yyj.pigdiary.board.entity.FreeBoard;

public interface FreeBoardService {
    Long register(FreeBoardDTO dto);

    PageResultDTO<FreeBoardDTO, FreeBoard> getList(PageRequestDTO pageRequestDTO);

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
        FreeBoardDTO dto = FreeBoardDTO.builder()
                .number(entity.getNumber())
                .title(entity.getTitle())
                .content(entity.getContent())
                .user(entity.getUser())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();

        return dto;
    }


}
