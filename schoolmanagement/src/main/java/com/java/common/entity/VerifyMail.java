//package com.java.common.entity;
//
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import java.time.LocalDateTime;
//
//@Entity
//@Getter
//@Setter
//public class VerifyMail extends BaseEntity {
//    @Column(nullable =  false)
//    private String token;
//    @Column(nullable =  false)
//    private String email;
//    @Column(nullable =  false)
//    private LocalDateTime createAt;
//    @Column(nullable =  false)
//    private LocalDateTime expireAt;
//    private LocalDateTime confirmAt;
//
//    public VerifyMail( String token, String email, LocalDateTime createAt, LocalDateTime expireAt, LocalDateTime confirmAt) {
//
//        this.token = token;
//        this.email = email;
//        this.createAt = createAt;
//        this.expireAt = expireAt;
//        this.confirmAt = confirmAt;
//    }
//}
