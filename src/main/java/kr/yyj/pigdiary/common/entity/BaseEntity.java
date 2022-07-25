package kr.yyj.pigdiary.common.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

// MappedSuperclass 어노테이션이 적용된 클래스는 테이블로 생성되지 않는다.
// 실제 테이블은 BaseEntity 클래스를 상속한 엔티티의 클래스로 생성된다.
@MappedSuperclass
// 엔티티 객체에 변경이 일어나는 것을 감지하는 리스너 -> AuditingEntityListener
@EntityListeners(value = {AuditingEntityListener.class})
@Getter
public abstract class BaseEntity {
    @CreatedDate // 엔티티 생성 시간 자동으로 처리
    @Column(name = "regdate", updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate // 최종 수정 시간 자동으로 처리
    @Column(name = "moddate")
    private LocalDateTime modDate;
}
