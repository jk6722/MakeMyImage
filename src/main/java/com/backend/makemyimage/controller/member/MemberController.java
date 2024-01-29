package com.backend.makemyimage.controller.member;

import com.backend.makemyimage.base.BaseResponse;
import com.backend.makemyimage.dto.request.member.LoginRequest;
import com.backend.makemyimage.dto.response.member.LoginResponse;
import com.backend.makemyimage.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class MemberController {
    private final MemberService memberService;

    //로그인
    @PostMapping("/login")
    public BaseResponse<LoginResponse> memberLogin(@RequestBody LoginRequest req, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()) {
            //에러 발생
            //Todo: 예외 처리 필요
            ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();
            return BaseResponse.onFailure(400, objectError.getDefaultMessage(), null);
        }

        LoginResponse response = memberService.userLogin(req);
        return BaseResponse.onSuccess(response);
    }
}