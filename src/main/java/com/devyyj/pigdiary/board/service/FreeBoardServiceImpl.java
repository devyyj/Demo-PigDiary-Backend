package com.devyyj.pigdiary.board.service;

import com.devyyj.pigdiary.board.dto.FreeBoardDTO;
import com.devyyj.pigdiary.board.dto.PageRequestDTO;
import com.devyyj.pigdiary.board.dto.PageResultDTO;
import com.devyyj.pigdiary.board.entity.FreeBoard;
import com.devyyj.pigdiary.board.repository.FreeBoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor // 의존성 자동 주입
public class FreeBoardServiceImpl implements FreeBoardService {

    private final FreeBoardRepository freeBoardRepository; // 반드시 final로 선언

    @Override
    public Long createPost(FreeBoardDTO dto) {
        log.info("DTO------------------------");
        log.info(dto);
        FreeBoard entity = dtoToEntity(dto);
        log.info(entity);
        freeBoardRepository.save(entity);
        return entity.getNumber();
    }

    @Override
    public PageResultDTO<FreeBoardDTO, FreeBoard> getList(PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("number").descending());
        Page<FreeBoard> result = freeBoardRepository.findAll(pageable);
//        Function<FreeBoard, FreeBoardDTO> fn = (entity -> entityToDto(entity));
        Function<FreeBoard, FreeBoardDTO> fn = (this::entityToDto);
        return new PageResultDTO<>(result, fn);
    }

    @Override
    public FreeBoardDTO getPost(Long postNumber) {
        Optional<FreeBoard> result = freeBoardRepository.findById(postNumber);
        // isPresent() 공부하기
//        return result.isPresent() ? entityToDto(result.get()) : null;
        return result.map(this::entityToDto).orElse(null);
    }

    @Override
    public void update(Long postNumber, FreeBoardDTO freeBoardDTO) {
        Optional<FreeBoard> result = freeBoardRepository.findById(postNumber);

        if (result.isPresent()) {
            FreeBoard freeBoard = result.get();

            freeBoard.setTitle(freeBoardDTO.getTitle());
            freeBoard.setContent(freeBoardDTO.getContent());

            freeBoardRepository.save(freeBoard);
        }
    }

    @Override
    public void delete(Long postNumber) {
        freeBoardRepository.deleteById(postNumber);
    }
}
