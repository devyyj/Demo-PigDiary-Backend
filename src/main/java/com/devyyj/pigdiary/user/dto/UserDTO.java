package com.devyyj.pigdiary.user.dto;

import com.devyyj.pigdiary.common.dto.CommonDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@NoArgsConstructor
public class UserDTO extends CommonDTO {
    private Long id;
    private String socialId;
    private String nickName;
}
