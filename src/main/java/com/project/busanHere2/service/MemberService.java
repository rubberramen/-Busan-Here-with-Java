package com.project.busanHere2.service;

import com.project.busanHere2.domain.member.MemberDTO;
import com.project.busanHere2.domain.member.MemberForm;
import com.project.busanHere2.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberMapper memberMapper;
    @Transactional
    public void join(MemberForm memberForm) {
        validateDuplicateMember(memberForm);
        memberMapper.save(memberForm);
    }

    private void validateDuplicateMember(MemberForm memberForm) {
        MemberDTO findMember = memberMapper.findByNickName(memberForm.getNickName());
        if (findMember != null) {
            throw new IllegalStateException("닉네임이 중복되었습니다. 다른 닉네임으로");
        }
    }

    public List<MemberDTO> findAllMembers() {
        return memberMapper.findAllMembers();
    }

    public MemberDTO login(String nickName, String pw) {
        MemberDTO member = memberMapper.findByNickName(nickName);
        if (member.getPasswd().equals(pw)) {
            return member;
        } else {
            return null; // TODO: 2023-02-17 017
        }
    }

    public void indexGet(@SessionAttribute(name = "loginMember", required = false) MemberDTO loginMember, Model model) {
        log.info("서비스------------");
        model.addAttribute("loginMember", loginMember);
    }
}
