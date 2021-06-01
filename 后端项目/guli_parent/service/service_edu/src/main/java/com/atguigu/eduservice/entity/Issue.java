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
@ApiModel(value="Issue对象", description="")
public class Issue implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "事务id")
    //@TableId(value = "i_id", type = IdType.ID_WORKER_STR)
    @TableId(value = "i_id", type = IdType.AUTO)
    private Integer iId;

    @ApiModelProperty(value = "果树id")
    private Integer treeId;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "除草")
    private String weeding;

    @ApiModelProperty(value = "灌溉")
    private String irrigating;

    @ApiModelProperty(value = "打药")
    private String spraying;

    @ApiModelProperty(value = "采摘")
    private String picking;

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
