package com.example.ProductService.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "UserAuthenticationService", url = "${auth.service.url}")
public interface UserClient {

    @GetMapping("/users/check-role")
    Boolean checkUserRole(@RequestHeader("Authorization") String token,
                          @RequestParam("role") String role);
}
