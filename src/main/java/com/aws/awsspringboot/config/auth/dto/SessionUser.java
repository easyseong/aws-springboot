package com.aws.awsspringboot.config.auth.dto;

import com.aws.awsspringboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable { // todo: User엔티티에 직렬화 구현하지 않는 이유 자세히 보기
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
