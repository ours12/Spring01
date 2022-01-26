package com.sparta.hwk03.controller;

import com.sparta.hwk03.domain.Memo;
import com.sparta.hwk03.domain.MemoRepository;
import com.sparta.hwk03.domain.MemoRequestDto;
import com.sparta.hwk03.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.*;

@RequiredArgsConstructor
@RestController
public class MemoController {

    private final MemoRepository memoRepository;
    private final MemoService memoService;

    //게시글 생성
    @PostMapping("/api/memos")
    public Memo createMemo(@RequestBody MemoRequestDto requestDto) {
        Memo memo = new Memo(requestDto);
        return memoRepository.save(memo);
    }
    //게시글 생성 페이지로 이동
    @RequestMapping("/write")
    public ModelAndView write(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("write.html");
        return modelAndView;
    }

    //게시글 전체 조회
    @GetMapping("/api/memos")
    public List<Memo> getMemos() {
        return memoRepository.findAllByOrderByCreatedAtDesc();
    }

    //게시글 상세페이지 조회
    @GetMapping("/detail/{id}")
    public ModelAndView detailpage(@PathVariable("id") Long Id){
        Optional<Memo> memo = memoRepository.findById(Id);
        ModelAndView modelAndView = new ModelAndView("detail.html");
        modelAndView.addObject("id",memo.get().getId());
        modelAndView.addObject("title",memo.get().getTitle());
        modelAndView.addObject("username",memo.get().getUsername());
        modelAndView.addObject("contents",memo.get().getContents());
        modelAndView.addObject("createdAt",memo.get().getCreatedAt());

        return modelAndView;
    }


    //게시글 수정
    @PutMapping("/api/memos/{id}")
    public Long updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto){
        memoService.update(id, requestDto);
        return id;
    }

    //게시글 삭제
    @DeleteMapping("/api/memos/{id}")
    public Long deleteMemo(@PathVariable Long id) {
        memoRepository.deleteById(id);
        return id;
    }
}
