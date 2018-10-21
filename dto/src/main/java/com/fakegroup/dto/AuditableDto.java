package com.fakegroup.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
abstract class AuditableDto {

    @ApiModelProperty(value = "Created date.", readOnly = true, dataType = "Date")
    private Date createdDate;

    @ApiModelProperty(value = "Updated date.", readOnly = true, dataType = "Date")
    private Date updatedDate;


}
