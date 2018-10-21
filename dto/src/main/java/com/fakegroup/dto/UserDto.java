package com.fakegroup.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("Model that described common user properties.")
public class UserDto extends BaseProjectDto {

    @ApiModelProperty(value = "First name", dataType = "String")
    private String firstName;

    @ApiModelProperty(value = "Last name", dataType = "String")
    private String lastName;

    @ApiModelProperty(value = "Email", dataType = "String")
    private String email;
}
