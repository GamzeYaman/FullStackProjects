package com.patika.atmProject.app.securityGeneral.security;

import com.patika.atmProject.app.customer.entity.CustomerDetail;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JwtUserDetails implements UserDetails {

    private Long id;
    private Long customerTc;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    private JwtUserDetails(Long id, Long customerTc, String password, Collection<? extends GrantedAuthority> authorities ){
        this.id =id;
        this.customerTc = customerTc;
        this.password = password;
        this.authorities = authorities;
    }

    public static JwtUserDetails create(CustomerDetail customerDetail){

        Long id = customerDetail.getId();
        Long customerTc = customerDetail.getCustomerTc();
        String password = customerDetail.getPassword();

        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority("user"));

        return new JwtUserDetails(id, customerTc, password, grantedAuthorityList);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return customerTc.toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }
}
