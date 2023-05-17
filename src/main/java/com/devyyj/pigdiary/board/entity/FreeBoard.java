package com.devyyj.pigdiary.board.entity;

import com.devyyj.pigdiary.common.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FreeBoard extends BaseEntity {

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

    public void setUser(String user) {
        this.user = user;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
