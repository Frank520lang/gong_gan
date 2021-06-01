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
@ApiModel(value="Fruit对象", description="")
public class Fruit implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "果实id")
    //@TableId(value = "f_id", type = IdType.ID_WORKER_STR)
    @TableId(value = "f_id", type = IdType.AUTO)
    private Integer fId;

    @ApiModelProperty(value = "果树id")
    private Integer treeId;

    @ApiModelProperty(value = "果实大小")
    private String size;

    @ApiModelProperty(value = "果实颜色")
    private String color;

    @ApiModelProperty(value = "果皮")
    private String seedcase;

    //先在config类里添加逻辑删除插件
    //接在逻辑删除必须要添加的注解
    @TableLogic
    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    private Integer isDeleted;

    @ApiModelProperty(value = "乐观锁字段")
    private Integer version;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "更新时间")
    private Date gmtModified;

}
