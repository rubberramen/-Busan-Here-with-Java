package com.project.busanHere2.domain.review;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ReviewResponse {
    private Long boardId;                       // PK
    private String title;                  // 제목
    private String content;                // 내용
    private Long memberId;                 // 작성자
    private Long shopId;
    private int viewCnt;                   // 조회 수
    private Boolean noticeYn;              // 공지글 여부
    private Boolean deleteYn;              // 삭제 여부
    private LocalDateTime createdAt;     // 생성일시
    private LocalDateTime modifiedAt;    // 최종 수정일시
}
