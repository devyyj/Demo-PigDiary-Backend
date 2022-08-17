package kr.yyj.pigdiary.board.repository;

import kr.yyj.pigdiary.board.entity.FreeBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface FreeBoardRepository extends JpaRepository<FreeBoard, Long>, QuerydslPredicateExecutor<FreeBoard> {
}
