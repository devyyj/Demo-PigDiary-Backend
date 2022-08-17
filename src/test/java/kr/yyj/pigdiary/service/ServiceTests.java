package kr.yyj.pigdiary.service;

import kr.yyj.pigdiary.board.dto.FreeBoardDTO;
import kr.yyj.pigdiary.board.dto.PageRequestDTO;
import kr.yyj.pigdiary.board.dto.PageResultDTO;
import kr.yyj.pigdiary.board.entity.FreeBoard;
import kr.yyj.pigdiary.board.service.FreeBoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ServiceTests {
    @Autowired
    private FreeBoardService fbs;

    @Test
    public void testRegister() {
        FreeBoardDTO freeBoardDTO = FreeBoardDTO.builder()
                .title("test title")
                .content("test contetn")
                .user("test user")
                .build();

        System.out.println(fbs.register(freeBoardDTO));
    }

    @Test
    void listTest() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();
        PageResultDTO<FreeBoardDTO, FreeBoard> resultDTO = fbs.getList(pageRequestDTO);

        for (FreeBoardDTO freeBoardDTO : resultDTO.getDtoList()) {
            System.out.println(freeBoardDTO);
        }
    }
}
