package com.sparta.security0531.security;

import com.sparta.security0531.model.User;
import com.sparta.security0531.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//DB 의 회원 정보 조회 →  스프링 시큐리티의 "인증 관리자" 에게 전달
//인증매니저가 db에게 클라이언트가 로그인시 username이 있는지 확인해달라고 request하는 것이 serviceimpl
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    //이 메소드는 꼭필요함 implements를 컨트롤 클릭해서 확인해보자
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //유저 리파지토리에서 유저이름이 존재하는지 검사해서 유저에다가 넣어줘라
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Can't find " + username));

        return new UserDetailsImpl(user);
    }
}