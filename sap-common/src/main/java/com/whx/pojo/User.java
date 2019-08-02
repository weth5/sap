package com.whx.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * Created by Administrator on 2019/7/25.
 */
@Data
@ToString
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName("sap_users")
public class User extends BaseValue {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private String salt;//盐值
    private String email;
    private String mobile;
    private Integer valid=1;
    private Integer deptId;
    private String createdUser;
    private String modifiedUser;
}
