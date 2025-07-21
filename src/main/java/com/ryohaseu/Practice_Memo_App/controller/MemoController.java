package com.ryohaseu.Practice_Memo_App.controller;

import com.ryohaseu.Practice_Memo_App.model.MemoModel;
import com.ryohaseu.Practice_Memo_App.repository.MemoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MemoController {

    private final MemoRepository memoRepository;

    public MemoController(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    // 전체 조회 컨트롤러
    @GetMapping("/")
    public String listMemo(Model model) {
        model.addAttribute("memos", memoRepository.findAll());
        return "memo-list";
    }

    // 등록 처리 컨트롤러
    @PostMapping("/add")
    public String addMemo(
            @RequestParam String title,
            @RequestParam String content
    ) {
        memoRepository.save(title, content);
        return "redirect:/";
    }

    // 수정 폼 보여주기 컨트롤러
    @GetMapping("/edit/{id}")
    public String editForm(
            @PathVariable int id,
            Model model
    ) {
        MemoModel memoModel = memoRepository.findById(id);
        model.addAttribute("memo", memoModel);
        return "memo-edit";
    }

    // 수정 처리 컨트롤러
    @PostMapping("/edit")
    public String editMemo(
            @RequestParam int id,
            @RequestParam String title,
            @RequestParam String content
    ) {
        memoRepository.update(id, content, content);
        return "redirect:/";
    }

    // 삭제 컨트롤러
    @PostMapping("/delete")
    public String deleteMemo(@RequestParam int id) {
        memoRepository.delete(id);
        return "redirect:/";
    }
}
