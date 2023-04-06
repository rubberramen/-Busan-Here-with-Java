package com.project.busanHere2.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.project.busanHere2.domain.review.ReviewRequest;
import com.project.busanHere2.domain.review.ReviewResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@Transactional
class ReviewMapperTest {

    @Autowired
    ReviewMapper reviewMapper;

    @Test
    public void save() throws Exception {
        // given
        ReviewRequest reviewRequest = new ReviewRequest();
        reviewRequest.setTitle("동방명주 리뷰4");
        reviewRequest.setContent("test test");
        reviewRequest.setMemberId(3L);
        reviewRequest.setShopId(4L);
        reviewRequest.setNoticeYn(false);
        // when
        reviewMapper.save(reviewRequest);

        // then
        List<ReviewResponse> reviews = reviewMapper.findAll();
        for (ReviewResponse review : reviews) {
            System.out.println("review.toString() = " + review.toString());
        }
    }
    
    @Test
    public void findById() throws Exception {
        // given
        ReviewResponse byId = reviewMapper.findById(3L);
        System.out.println("byId.toString() = " + byId.toString());

        // when
        
        // then
    }

    ObjectMapper om = new ObjectMapper();


    @Test
    public void findById2() throws Exception {
        // given
        ReviewResponse byId = reviewMapper.findById(3L);
//        try {
//            String postJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(byId);
//            System.out.println(postJson);
//
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
        System.out.println("byId.toString() = " + byId.toString());
//        String s = om.writeValueAsString(byId);
        String postJson = om.registerModule(new JavaTimeModule()).writeValueAsString(byId);
        System.out.println("s = " + postJson);

        // when

        // then
    }

    @Test
    public void update() throws Exception {
        // given
        ReviewResponse response = reviewMapper.findById(4L);
        ReviewRequest reviewRequest = new ReviewRequest();

        reviewRequest.setBoardId(response.getBoardId());
        reviewRequest.setTitle("업데이트 테스트");
        reviewRequest.setContent("zzzzzzzzzzzzzzzzz");
        reviewRequest.setMemberId(response.getMemberId());
        reviewRequest.setNoticeYn(true);
        reviewMapper.update(reviewRequest);

        // when

        // then
    }

    @Test
    public void delete() throws Exception {
        // given
        ReviewResponse response = reviewMapper.findById(4L);
        ReviewRequest reviewRequest = new ReviewRequest();

        reviewRequest.setBoardId(response.getBoardId());
        reviewMapper.deleteById(reviewRequest.getBoardId());

        // when

        // then
    }

    @Test
    public void count() throws Exception {
        // given
        int count = reviewMapper.count();
        System.out.println("count = " + count);

        // when

        // then
    }





}