package com.project.busanHere2.mapper;

import com.project.busanHere2.domain.member.MemberDTO;
import com.project.busanHere2.domain.member.MemberForm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    void save(MemberForm memberForm);

    List<MemberDTO> findAllMembers();

    MemberDTO findByNickName(String nickName);
}
