package jp.te4a.spring.boot.myapp13.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.te4a.spring.boot.myapp13.form.BookForm;
import jp.te4a.spring.boot.myapp13.service.BookService;

/**
 * 書籍関係の遷移制御
 * 
 * @author t.kawana
 *
 */
@Controller
@RequestMapping("books")
public class BookController {

	@Autowired
    BookService bookService;

	/**
	 * 標準の設定フォーム
	 * 
	 * @return 書籍用フォーム
	 */
	@ModelAttribute 
	BookForm setUpForm() {
		return new BookForm();
	}

	/**
	 * トップレベルGET要求
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping
	String list(Model model) {
		model.addAttribute("books", bookService.findAll());
	    return "books/list";
	}

	/**
	 * 書籍作成
	 * 
	 * @param form 書籍フォーム
	 * @param result 結果
	 * @param model モデル
	 * @return
	 */
	@PostMapping(path="create")
	String create(@Validated BookForm form, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return list(model);
		}
		bookService.create(form);
		return "redirect:/books";
	}
	
	/**
	 * 書籍編集
	 * 
	 * @param id
	 * @param form
	 * @return
	 */
	@PostMapping(path = "edit", params = "form")
	String editForm(@RequestParam Integer id, BookForm form) {
		BookForm bookForm = bookService.findOne(id);
	    BeanUtils.copyProperties(bookForm,  form);
	    return "books/edit";
	}
	
	/**
	 * 書籍編集：更新ボタン押下時
	 * 
	 * @param id
	 * @param form
	 * @param result
	 * @return
	 */
	@PostMapping(path = "edit")
	String edit(@RequestParam Integer id, @Validated BookForm form, BindingResult result) {
		if(result.hasErrors()) {
			return editForm(id, form);
		}
	    bookService.update(form);
	    return "redirect:/books";
	}
	
	/**
	 * 書籍削除
	 * 
	 * @param id
	 * @return
	 */
	@PostMapping(path = "delete")
	String delete(@RequestParam Integer id) {
	    bookService.delete(id);
	    return "redirect:/books";
	}

	/**
	 * 書籍編集：キャンセルボタン押下時
	 * 
	 * @return 
	 */
	@PostMapping(path = "edit", params = "goToTop")
	String goToTop() {
	    return "redirect:/books";
	}
}
