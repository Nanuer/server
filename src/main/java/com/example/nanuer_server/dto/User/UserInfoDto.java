package com.example.nanuer_server.dto.User;

import com.example.nanuer_server.domain.entity.MyPageEntity;
import com.example.nanuer_server.domain.entity.PostEntity;
import com.example.nanuer_server.domain.entity.UserEntity;
import com.example.nanuer_server.domain.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfoDto {
    private String password;
    private String name;
    private String email;
    private String nickName;
    private String phone;
    private String birth;
    private String profileImg;
    private String university;
    private String userStatus;
    private int  userScore;
    private UserRole role;
    private List<PostEntity> postEntities;
    private MyPageEntity myPageEntity;

    public UserInfoDto(UserEntity userEntity){
        this.email = userEntity.getEmail();
        this.password = userEntity.getPassword();
        this.name = userEntity.getName();
        this.nickName = userEntity.getNickName();
        this.phone = userEntity.getPhone();
        this.birth = userEntity.getBirth();
        this.profileImg = userEntity.getProfileImg();
        this.university = userEntity.getUniversity();
        this.userStatus = userEntity.getUserStatus();
        this.userScore = userEntity.getUserScore();
        this.role = userEntity.getRole();
        this.postEntities = userEntity.getPostEntities();
        this.myPageEntity = userEntity.getMyPageEntity();
    }

    public UserEntity UserInfoToEntity() {
        UserEntity userEntity = UserEntity.builder()
                .password(password)
                .name(name)
                .nickName(nickName)
                .email(email)
                .phone(phone)
                .birth(birth)
                .profileImg(profileImg)
                .university(university)
                .userStatus(userStatus)
                .userScore(userScore)
                .role(role)
                .myPageEntity(myPageEntity)
                .postEntities(postEntities)
                .build();
        return userEntity;
    }
}