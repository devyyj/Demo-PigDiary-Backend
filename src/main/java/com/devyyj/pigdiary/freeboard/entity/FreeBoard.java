package com.devyyj.pigdiary.freeboard.entity;

import com.devyyj.pigdiary.common.entity.CommonEntity;
import lombok.*;
import org.hibernate.Hibernate;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class FreeBoard extends CommonEntity {

    /**
     * 엔티티 객체는 단순히 데이터를 담는 객체가 아니라 실제 데이터베이스와 관련이 있고
     * 내부적으로 엔티티 매니저가 관리하는 객체이다.
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 1500, nullable = false)
    private String content;

    @Column(length = 50, nullable = false)
    private String user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FreeBoard freeBoard = (FreeBoard) o;
        return getNumber() != null && Objects.equals(getNumber(), freeBoard.getNumber());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
