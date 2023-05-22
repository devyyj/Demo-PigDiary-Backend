package com.devyyj.pigdiary.user.dto;

import com.devyyj.pigdiary.common.dto.CommonDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
public class UserDTO extends CommonDTO {
    private Long id;
    private String social_id;
    private String nick_name;
}
