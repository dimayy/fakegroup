package com.fakegroup.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class BaseProjectDto extends AuditableDto {

    @ApiModelProperty(value = "User identifier.", dataType = "Long")
    private Long id;

}
