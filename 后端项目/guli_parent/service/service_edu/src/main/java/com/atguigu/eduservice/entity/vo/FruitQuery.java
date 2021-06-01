package com.atguigu.eduservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class FruitQuery {
    @ApiModelProperty(value = "果实表id")
    private Integer id;
    @ApiModelProperty(value = "果树id")
    private Integer treeId;
    @ApiModelProperty(value = "创建时间")
    private String begin;
    @ApiModelProperty(value = "修改时间")
    private String end;
}
