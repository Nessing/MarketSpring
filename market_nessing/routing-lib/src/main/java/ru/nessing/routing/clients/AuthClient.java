package ru.nessing.routing.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.nessing.routing.dtos.AuthRequestDto;
import ru.nessing.routing.dtos.AuthResponseDto;
import ru.nessing.routing.dtos.SignUpRequestDto;

@FeignClient("ms-auth")
public interface AuthClient {

    @PostMapping("/signup")
    String signUp(@RequestBody SignUpRequestDto signUpRequest);

    @PostMapping("/login")
    AuthResponseDto login(@RequestBody AuthRequestDto request);
}
