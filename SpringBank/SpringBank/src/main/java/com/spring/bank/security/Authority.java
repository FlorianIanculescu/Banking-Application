package com.spring.bank.security;

import jakarta.persistence.*;

@Entity
@Table(name = "authorities")
@IdClass(AuthorityId.class)
public class Authority {

    @Id
    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;

    @Id
    @Column(name = "authority")
    private String authority;

    public Authority() {

    }

    public Authority(User user, String authority) {
        this.user = user;
        this.authority = authority;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "Authority{" +
                "user=" + user +
                ", authority='" + authority + '\'' +
                '}';
    }
}