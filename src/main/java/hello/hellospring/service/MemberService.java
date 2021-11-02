package hello.hellospring.service;

import hello.hellospring.domain.MemberForm;
import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class MemberService implements UserDetailsService {

    //logger
    private static final Logger logger = LoggerFactory.getLogger(MemberService.class);

    //repository
    private final MemberRepository memberRepository;                  ////////

    @Autowired
    public MemberService(MemberRepository memberRepository) {                  ////////
        this.memberRepository = memberRepository;
    }

    /**
     *  Spring security method
     * @param userId
     * @return Member
     * @throws UsernameNotFoundException 유저 존재하지 않을 때 예외 발생
     */
    //권한부여 해야되낭??
    @Override
    public Member loadUserByUsername(String userId) throws UsernameNotFoundException {
//        return memberRepository.findByUserId(userId)
//                .orElseThrow(() -> new UsernameNotFoundException((userId)));
        return memberRepository.findByUserId(userId);
    }

    /**
     *회원가입
     * 1.id중복확인 2.pw해싱 3.입력받는 데이터 길이 제한
     */
    public Long join(MemberForm memberForm){

        Member member=Member.builder()
                .name(memberForm.getName())
                .userid(memberForm.getUserId())
                .email(memberForm.getEmail())
                .birth(memberForm.getBirth())
                .auth("ROLE_USER").build();

//        //같은 id가 있는 중복 회원x
//        if(validateDuplicateMember(member)){
//            logger.info("중복 회원" + member.getUsername());        //
////            m-> {
//                throw new IllegalStateException("이미 존재하는 아이디입니다.");
////            }
//        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        member.setPassword(encoder.encode(memberForm.getPassword()));       //password 암호화

        logger.info("new password : "+member.getPassword());
        logger.info("Save : " + member.getUsername());
        Long saveId=memberRepository.save(member);//중복회원이 아닐 경우 저장
        logger.info("Save id : " + saveId);

        return saveId;
    }

    //유효성 check
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            logger.info("error!!!!!!!" + error.getField());             //
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }

//    //중복체크
//    private boolean validateDuplicateMember(Member member) {
//
//        //id 중복 검사
////        Optional<Member> result =memberRepository.findByUserId(member.getUsername());
//        Member findMember = memberRepository.findByUserId(member.getUsername());
//
//        if(findMember){
//            return true;
//        }else{
//            return false;
//        }
//    }
    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }  //회원목록에서 사용할 회원조회기능
/*
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    } //회원 찾기 기능
                                                               ////////
 */

    /*
    회원정보 수정(영속성 컨텍스트 자동 변경)
    <password, name 만 변경 가능하도록 함>
     */

    @Transactional
    public void update(MemberForm memberForm){
        Member mem=loadUserByUsername(memberForm.getUserId());

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        mem.setPassword(encoder.encode(memberForm.getPassword()));
        mem.setName(memberForm.getName());

        logger.info("new password : "+mem.getPassword());
    }
}
