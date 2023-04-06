package com.project.busanHere2.mapper;

import com.project.busanHere2.domain.review.ReviewRequest;
import com.project.busanHere2.domain.review.ReviewResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {

    void save(ReviewRequest reviewRequest);

    ReviewResponse findById(Long boradId);

    void update(ReviewRequest reviewRequest);

    void deleteById(Long boradId);

    /**
     * 게시글 리스트 조회
     * @return 게시글 리스트
     */
    List<ReviewResponse> findAll();

    /**
     * 게시글 수 카운팅
     * @return 게시글 수
     */
    int count();
}
