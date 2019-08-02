package com.whx.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Created by Administrator on 2019/7/27.
 */
@Data
@ToString
@Accessors(chain = true)
@TableName("sap_roles")
public class Role extends BaseValue{
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String note;
    private String createdUser;
    private String modifiedUser;
}
