package com.whx.pojo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2019/7/25.
 */
@Data
@ToString
public class BaseValue implements Serializable{
    private static final long serialVersionUID = -1541382775521790103L;
    private Date createdTime;
    private Date modifiedTime;
}
