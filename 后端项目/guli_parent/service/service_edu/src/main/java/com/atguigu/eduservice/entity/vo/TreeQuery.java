package com.atguigu.eduservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

//查询对象
@Data
public class TreeQuery {
    @ApiModelProperty(value = "果树期,模糊查询")
    private String season;

    @ApiModelProperty(value = "果树的id")
    private Integer id;

    @ApiModelProperty(value = "查询开始时间", example = "2019-01-01 10:10:10")
    private String begin;//注意，这里使用的是String类型，前端传过来的数据无需进行类型转换

    @ApiModelProperty(value = "查询结束时间", example = "2019-12-01 10:10:10")
    private String end;
}
