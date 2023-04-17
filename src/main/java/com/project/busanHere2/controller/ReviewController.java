package com.project.busanHere2.controller;

import com.project.busanHere2.domain.review.ReviewRequest;
import com.project.busanHere2.domain.review.ReviewResponse;
import com.project.busanHere2.domain.shop.ShopForm;
import com.project.busanHere2.service.ReviewService;
import com.project.busanHere2.service.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/review")
@RequiredArgsConstructor
@Slf4j
public class ReviewController {

    private final ReviewService reviewService;
    private final ShopService shopService;

    @GetMapping("/new")
    public String reviewWriteForm(@RequestParam(value = "boardId", required = false) final Long boardId, Model model) {
        if (boardId != null) {
            ReviewResponse post = reviewService.findById(boardId);
            model.addAttribute("post", post);
        }
        List<ShopForm> allShops = shopService.findAllShops();
        model.addAttribute("allShops", allShops);
        return "review/reviewWrite";
//        return "review/reviewWrite-sample";
    }

    @GetMapping("/newTest")
    public String reviewWriteFormTest(@RequestParam(value = "boardId", required = false) final Long boardId, Model model) {
        if (boardId != null) {
            ReviewResponse post = reviewService.findById(boardId);
            model.addAttribute("post", post);
        }
        List<ShopForm> allShops = shopService.findAllShops();
        model.addAttribute("allShops", allShops);
//        return "review/reviewWrite";
        return "review/reviewWrite_xxx";
    }

    @PostMapping("/new")
    public String saveReview(ReviewRequest reviewRequest) {
        reviewService.save(reviewRequest);
        return "redirect:/review/list";
    }

    @GetMapping("/list")
    public String reviewList(Model model) {
        List<ReviewResponse> posts = reviewService.findAll();
        model.addAttribute("posts", posts);
        return "review/reviewList";
    }

    @GetMapping("/{boardId}")
    public String reviewDetail(@PathVariable Long boardId, Model model) {
        ReviewResponse post = reviewService.findById(boardId);
        model.addAttribute("post", post);

        return "review/reviewDetail";   //reviewDetail
    }

    @PostMapping("/update")
    public String updatePost(ReviewRequest reviewRequest, RedirectAttributes redirectAttributes) {
        reviewService.update(reviewRequest);
        Long reviewId = reviewRequest.getBoardId();
        redirectAttributes.addAttribute("reviewId", reviewId);
        return "redirect:/review/{reviewId}";
    }

    @PostMapping("/{boardId}")
    public String delete(@PathVariable Long boardId) {
        System.out.println("boardId = " + boardId);
        reviewService.delete(boardId);
        return "redirect:/review/list";
//        return "review/reviewList";
    }
}
