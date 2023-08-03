package com.devyyj.pigdiary.repository;
import com.devyyj.pigdiary.board.entity.FreeBoard;
import com.devyyj.pigdiary.board.repository.FreeBoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class FreeBoardRepositoryTests {

    @Autowired
    private FreeBoardRepository freeBoardRepository;

    @Test
    public void insertDummies() {
        IntStream.rangeClosed(1, 300).forEach(i -> {
            FreeBoard freeBoard = FreeBoard.builder().title("title : " + i).content("content : " + i).user("user : " + (i % 10)).build();
            System.out.println(freeBoardRepository.save(freeBoard));
        });
    }

    @Test
    public void updateTest() {
        Optional<FreeBoard> result = freeBoardRepository.findById(300L);

        if (result.isPresent()) {
            FreeBoard freeBoard = result.get();

            freeBoard.setContent("change content");
            freeBoard.setTitle("change title");

            freeBoardRepository.save(freeBoard);
        }
    }
}
