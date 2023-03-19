package com.devyyj.pigdiary.service;

import com.devyyj.pigdiary.board.dto.FreeBoardDTO;
import com.devyyj.pigdiary.board.dto.PageRequestDTO;
import com.devyyj.pigdiary.board.dto.PageResultDTO;
import com.devyyj.pigdiary.board.entity.FreeBoard;
import com.devyyj.pigdiary.board.service.FreeBoardService;
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

        System.out.println(fbs.createPost(freeBoardDTO));
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
