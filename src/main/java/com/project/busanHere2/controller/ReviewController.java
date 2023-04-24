package com.project.busanHere2.controller;

import com.project.busanHere2.common.MessageDto;
import com.project.busanHere2.domain.member.MemberDTO;
import com.project.busanHere2.domain.review.ReviewRequest;
import com.project.busanHere2.domain.review.ReviewResponse;
import com.project.busanHere2.domain.shop.ShopForm;
import com.project.busanHere2.service.MemberService;
import com.project.busanHere2.service.ReviewService;
import com.project.busanHere2.service.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/review")
@RequiredArgsConstructor
@Slf4j
public class ReviewController {

    private final ReviewService reviewService;
    private final ShopService shopService;
    private final MemberService memberService;

    @GetMapping("/list")
    public String reviewList(@SessionAttribute(name = "loginMember", required = false) MemberDTO loginMember,
                             Model model, HttpServletRequest request) {

        model.addAttribute("loginMember", loginMember);

        List<ReviewResponse> posts = reviewService.findAll();
        model.addAttribute("posts", posts);
        return "review/reviewList";
    }


    @GetMapping("/new")
    public String reviewWrite(@SessionAttribute(name = "loginMember", required = false) MemberDTO loginMember,
                                  @RequestParam(value = "boardId", required = false) final Long boardId,
                                  Model model) {

        model.addAttribute("memberId", loginMember.getMemberId());
        model.addAttribute("memberNickName", loginMember.getNickName());

        if (boardId != null) {
            ReviewResponse post = reviewService.findById(boardId);
            model.addAttribute("post", post);
        }
        List<ShopForm> allShops = shopService.findAllShops();
        model.addAttribute("allShops", allShops);
        model.addAttribute("loginMember", loginMember);
        return "review/reviewWrite";
    }

    @PostMapping("/new")
    public String saveReview(ReviewRequest reviewRequest, Model model) {

        reviewService.save(reviewRequest);
        MessageDto message = new MessageDto("리뷰 생성이 완료되었습니다.", "/review/list",
                RequestMethod.GET, null);
        return showMessageAndRedirect(message, model);
    }

    @GetMapping("/{boardId}")
    public String reviewDetail(@SessionAttribute(name = "loginMember", required = false) MemberDTO loginMember,
                               @PathVariable Long boardId, Model model) {

        ReviewResponse post = reviewService.findById(boardId);
        model.addAttribute("post", post);
        model.addAttribute("loginMember", loginMember);

        return "review/reviewDetail";   //reviewDetail
    }

    @PostMapping("/update")    // TODO: 2023-04-20 020
    public String updatePost(ReviewRequest reviewRequest, RedirectAttributes redirectAttributes, Model model) {

        reviewService.update(reviewRequest);
        Long reviewId = reviewRequest.getBoardId();

        MessageDto message = new MessageDto("리뷰가 수정되었습니다.", "/review/"+reviewId,
                RequestMethod.GET, null);
        return showMessageAndRedirect(message, model);
    }

    @PostMapping("/{boardId}")
    public String delete(@PathVariable Long boardId, Model model) {
        reviewService.delete(boardId);

        MessageDto message = new MessageDto("리뷰 삭제가 완료되었습니다.", "/review/list",
                RequestMethod.GET, null);
        return showMessageAndRedirect(message, model);
    }

    private String showMessageAndRedirect(final MessageDto params, Model model) {
        model.addAttribute("params", params);
        return "common/messageRedirect";
    }
}
