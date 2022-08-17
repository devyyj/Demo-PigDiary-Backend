package kr.yyj.pigdiary.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import kr.yyj.pigdiary.board.entity.FreeBoard;
import kr.yyj.pigdiary.board.entity.QFreeBoard;
import kr.yyj.pigdiary.board.repository.FreeBoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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

    @Test
    public void querydslTest() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("number").descending());

        QFreeBoard qFreeBoard = QFreeBoard.freeBoard; // Q 도메인 클래스를 얻어옴

        String keyword = "1";

        BooleanBuilder builder = new BooleanBuilder(); // BooleanBuilder는 where문에 들어가는 조건들을 넣어주는 컨테이너라고 생각하면 됨

        BooleanExpression expression = qFreeBoard.title.contains(keyword); // 조건은 필드 값과 같이 결합해서 생성함.

        builder.and(expression); // 조건은 and나 or 같은 키워드와 결합

        Page<FreeBoard> result = freeBoardRepository.findAll(builder, pageable);

        result.stream().forEach(System.out::println);
    }

    /**
     * title 또는 content에 특정 키워드가 있고 number가 0보다 큰 데이터 검색
     */
    @Test
    public void querydslTest2() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("number").descending());

        QFreeBoard qFreeBoard = QFreeBoard.freeBoard;

        String keyword = "1";

        BooleanBuilder builder = new BooleanBuilder();

        BooleanExpression expression = qFreeBoard.title.contains(keyword);

        BooleanExpression expression1 = qFreeBoard.content.contains(keyword);

        BooleanExpression expression2 = expression.or(expression1);

        builder.and(expression2);

        builder.and(qFreeBoard.number.gt(0L));

        Page<FreeBoard> result = freeBoardRepository.findAll(builder, pageable);

        result.stream().forEach(System.out::println);
    }

}
