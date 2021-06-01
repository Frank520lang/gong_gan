package com.atguigu.eduservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Data
public class IssueQuery {
    @ApiModelProperty(value = "事务表id")
    private Integer id;
    @ApiModelProperty(value = "关联用户id")
    private Integer userId;
    @ApiModelProperty(value = "创建时间")
    private String gmtCreate;
    @ApiModelProperty(value = "修改时间")
    private String gmtModified;
}
