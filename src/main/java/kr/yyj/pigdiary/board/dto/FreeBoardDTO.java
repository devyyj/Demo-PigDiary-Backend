package kr.yyj.pigdiary.board.dto;

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
    private Long number;
    private String title;
    private String content;
    private String user;
    private LocalDateTime regDate, modDate;
}
