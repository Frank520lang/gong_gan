package com.atguigu.eduservice.entity;

import com.baomidou.mybatisplus.annotation.*;
import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author testjava
 * @since 2021-03-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Tree对象", description="果树")
public class Tree implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "果树id")
    //@TableId(value = "t_id", type = IdType.ID_WORKER_STR)
    @TableId(value = "t_id", type = IdType.AUTO)
    private Integer tId;

    @ApiModelProperty(value = "果树生长期")
    private String season;

    @ApiModelProperty(value = "果树根")
    private String root;

    @ApiModelProperty(value = "果树茎")
    private String steam;

    @ApiModelProperty(value = "果树树叶")
    private String leaf;

    @ApiModelProperty(value = "果树树干")
    private String trunk;

    @ApiModelProperty(value = "果树花")
    private String flower;

    @ApiModelProperty(value = "果树果实")
    private String fruit;

    //逻辑删除的注解
    //config类中还要添加逻辑删除的插件
    @TableLogic
    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    private Integer isDeleted;

    @ApiModelProperty(value = "乐观锁字段")
    private Integer version;

    //插入的时候自动更新时间
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;

    //更新的时候自动更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "更新时间")
    private Date gmtModified;


}
