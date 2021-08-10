package com.test.react_spring.service;

import com.test.react_spring.controller.LoginForm;
import com.test.react_spring.domain.MemberVO;
import com.test.react_spring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
//import org.springframework.transaction.annotation.Transactional;

//@Transactional
@Service
public class MemberService {

    @Autowired
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository=memberRepository;
    }

    public void singUp(){

    }

    public boolean signIn(LoginForm form){

        validatePresentMember(form);


    }

    private void validatePresentMember(LoginForm form) {

        Optional<MemberVO> op = memberRepository.findById(form.getId());
        if(!op.isPresent()){
//            throw new Exception();
            //존재 x id
        }else{

        }

    }


    public void logout(){

    }


//    public boolean search(){
//
//    }




}
