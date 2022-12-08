package com.devyyj.pigdiary.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FreeBoardDTO {
    /**
     * DTO(Data Transfer Object)는 엔티티 객체와 달리 각 계층끼리 주고받는 우편물이나 상자의 개념
     * 순수하게 데이터를 담고 있다는 점에서는 엔티티 객체와 유사하지만,
     * 목적 자체가 데이터의 전달이므로 읽고, 쓰는 것이 모두 허용된다.
     * 일회성으로 사용되는 성격이 강하다.
     *
     * DTO를 사용하는 경우 가장 큰 단점은 엔티티와 유사한 코드를 중복으로 개발한다는 점과
     * 엔티티 객체를 DTO로 변환하거나 그 반대로 변환하는 과정이 필요하는 점이다.
     */

    private Long number;
    private String title;
    private String content;
    private String user;
    private LocalDateTime regDate, modDate;
}
