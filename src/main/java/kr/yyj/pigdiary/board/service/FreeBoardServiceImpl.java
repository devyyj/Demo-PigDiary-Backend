package kr.yyj.pigdiary.board.service;

import kr.yyj.pigdiary.board.dto.FreeBoardDTO;
import kr.yyj.pigdiary.board.dto.PageRequestDTO;
import kr.yyj.pigdiary.board.dto.PageResultDTO;
import kr.yyj.pigdiary.board.entity.FreeBoard;
import kr.yyj.pigdiary.board.repository.FreeBoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor // 의존성 자동 주입
public class FreeBoardServiceImpl implements FreeBoardService {

    private final FreeBoardRepository freeBoardRepository; // 반드시 final로 선언

    @Override
    public Long register(FreeBoardDTO dto) {
        log.info("DTO------------------------");
        log.info(dto);

        FreeBoard entity = dtoToEntity(dto);
        log.info(entity);

        freeBoardRepository.save(entity);

        return entity.getNumber();
    }

    @Override
    public PageResultDTO<FreeBoardDTO, FreeBoard> getList(PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("number"));

        Page<FreeBoard> result =  freeBoardRepository.findAll(pageable);

        Function<FreeBoard, FreeBoardDTO> fn = (entity -> entityToDto(entity));

        return new PageResultDTO<>(result, fn);
    }
}
