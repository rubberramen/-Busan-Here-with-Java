package com.project.busanHere2.service;

import com.project.busanHere2.domain.member.MemberDTO;
import com.project.busanHere2.domain.review.ReviewRequest;
import com.project.busanHere2.mapper.MemberMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReviewServiceTest {

    @Autowired
    ReviewService reviewService;
    @Autowired
    MemberMapper memberMapper;

    @Test
    public void save() throws Exception {
        // given
        ReviewRequest reviewRequest = new ReviewRequest();
        MemberDTO rubberramen = memberMapper.findByNickName("rubberramen");

        reviewRequest.setTitle("서비스 테스트");
        reviewRequest.setContent("zzzz");
        reviewRequest.setMemberId(rubberramen.getMemberId());
        reviewRequest.setShopId(3L);
        reviewRequest.setNoticeYn(false);

        // when
        Long savedId = reviewService.save(reviewRequest);
        System.out.println("savedId = " + savedId);

        // then
//        Assertions.assertThat(savedId).isEqualTo(rubberramen.getMemberId());
    }



}