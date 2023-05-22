package com.devyyj.pigdiary.common.dto;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
public class CommonDTO {
    private LocalDateTime regDate, modDate, delDate;
}
