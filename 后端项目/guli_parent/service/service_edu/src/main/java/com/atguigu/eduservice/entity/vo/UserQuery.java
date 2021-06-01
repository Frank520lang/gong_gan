package com.atguigu.eduservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserQuery {
    private Integer uId;
    private String name;
    @ApiModelProperty(value = "创建时间")
    private String gmtCreate;
    @ApiModelProperty(value = "修改时间")
    private String gmtModified;
}
