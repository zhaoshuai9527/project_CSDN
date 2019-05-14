package com.zs.client;

import com.zs.client.impl.UserClientImpl;
import com.zs.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@Component
//说明熔断之后的备胎：UserClientImpl.class
@FeignClient(value="project-User" ,fallback = UserClientImpl.class)
public interface UserClient {
    @PutMapping("user/updateFans/{userId}/{number}")
    public Result updateFans(@PathVariable("userId") String userId, @PathVariable("number") int number) ;
}
