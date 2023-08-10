package com.aws.awsspringboot.config.auth;

import com.aws.awsspringboot.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {
    //HandlerMethodArgumentResolver 인터페이스 : 조건에 맞는 경우 메소드가 있다면 지정한 값으로 해당 메소드의 파라미터로 넘길 수 있다.

    private final HttpSession httpSession;

    //컨트롤러 메서드의 특정 파라미터를 지원하는지 판단
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null;
        boolean isUserClass = SessionUser.class.equals(parameter.getParameterType());
        return isLoginUserAnnotation && isUserClass;
    }

    //파라미터에 전달할 객체를 생성
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        //user로 저장된 세션 가져옴
        return httpSession.getAttribute("user");
    }
}
