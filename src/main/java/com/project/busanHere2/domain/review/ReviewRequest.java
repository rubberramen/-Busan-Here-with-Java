package com.project.busanHere2.domain.review;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewRequest {

    private Long boardId;             // PK
    private String title;        // 제목
    private String content;      // 내용
    private Long memberId;       // 작성자
    private Long shopId;
    private Boolean noticeYn;    // 공지글 여부
}
