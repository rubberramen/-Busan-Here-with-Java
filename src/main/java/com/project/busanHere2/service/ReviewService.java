package com.project.busanHere2.service;

import com.project.busanHere2.common.dto.SearchDto;
import com.project.busanHere2.common.paging.Pagination;
import com.project.busanHere2.common.paging.PagingResponse;
import com.project.busanHere2.domain.review.ReviewRequest;
import com.project.busanHere2.domain.review.ReviewResponse;
import com.project.busanHere2.domain.review.ReviewResponse2;
import com.project.busanHere2.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
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

//    public List<ReviewResponse> findAll(SearchDto searchDto) {
//        return reviewMapper.findAll(searchDto);
//    }

    public PagingResponse<ReviewResponse> findAll(SearchDto searchDto) {
        int count = reviewMapper.count(searchDto);
        if (count < 1) {
            return new PagingResponse<>(Collections.emptyList(), null);
        }
        Pagination pagination = new Pagination(count, searchDto);
        searchDto.setPagination(pagination);

        List<ReviewResponse> list = reviewMapper.findAll(searchDto);
        return new PagingResponse<>(list, pagination);
    }

    public PagingResponse<ReviewResponse2> findAll2(SearchDto searchDto) {
        int count = reviewMapper.count2(searchDto);
        if (count < 1) {
            return new PagingResponse<>(Collections.emptyList(), null);
        }
        Pagination pagination = new Pagination(count, searchDto);
        searchDto.setPagination(pagination);

        List<ReviewResponse2> list = reviewMapper.findAll2(searchDto);
        return new PagingResponse<>(list, pagination);
    }
}
