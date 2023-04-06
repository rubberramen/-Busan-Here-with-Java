package com.project.busanHere2.service;

import com.project.busanHere2.domain.review.ReviewRequest;
import com.project.busanHere2.domain.review.ReviewResponse;
import com.project.busanHere2.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    final ReviewMapper reviewMapper;

    @Transactional
    public Long save(final ReviewRequest reviewRequest) {
        reviewMapper.save(reviewRequest);
        return reviewRequest.getBoardId();
    }

    public ReviewResponse findById(Long boardId) {
        return reviewMapper.findById(boardId);
    }

    @Transactional
    public Long update(ReviewRequest reviewRequest) {
        reviewMapper.update(reviewRequest);
        return reviewRequest.getBoardId();
    }

    public Long delete(Long boardId) {
        reviewMapper.deleteById(boardId);
        return boardId;
    }

    public List<ReviewResponse> findAll() {
        return reviewMapper.findAll();
    }
}
