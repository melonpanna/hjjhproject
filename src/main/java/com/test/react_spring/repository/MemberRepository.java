package com.test.react_spring.repository;

import com.test.react_spring.domain.MemberVO;

import java.lang.reflect.Member;
import java.util.Optional;



public interface MemberRepository{
    MemberVO save(MemberVO memberVO);

//    Optional<Member> findByCnt(Long cnt);
    Optional<MemberVO> findById(String id);
    Optional<MemberVO> findByName(String name);


}