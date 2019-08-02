package com.whx.dubboService;

import com.whx.pojo.Role;
import com.whx.pojo.vo.PageObject;

import java.util.List;

/**
 * Created by Administrator on 2019/7/27.
 */
public interface RoleService {
    PageObject<Role> doFindPageObjects(String name,Integer pageCurrent);
}
