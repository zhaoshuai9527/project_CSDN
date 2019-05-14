package com.zs.client.impl;

import com.zs.client.UserClient;
import com.zs.entity.Result;
import com.zs.entity.StatusCode;

public class UserClientImpl implements UserClient {

    @Override
    public Result updateFans(String userId, int number) {
        System.out.println("熔断器启动了..........");
        return new Result(false, StatusCode.ERROR,"熔断机制启动，启动备用方法");
    }
}
