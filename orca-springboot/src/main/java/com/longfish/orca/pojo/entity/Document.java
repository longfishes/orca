package com.longfish.orca.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author longfish
 * @since 2024-05-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("document")
public class Document implements Serializable {

    @Serial
    private static final long serialVersionUID = 15431L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long userId;

    private String title;

    private String cover;

    @TableField(value = "abstract")
    private String docAbstract;

    private String content;

    private String path;

    private Boolean isTop;

    private Boolean isDeleted;

    private Boolean isLocked;

    private Boolean status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
