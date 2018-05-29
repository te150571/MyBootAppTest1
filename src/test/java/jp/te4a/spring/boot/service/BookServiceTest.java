package jp.te4a.spring.boot.service;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import jp.te4a.spring.boot.BookApplication;
import jp.te4a.spring.boot.form.BookForm;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = BookApplication.class)
@WebAppConfiguration
public class BookServiceTest {
	@Autowired
	BookService bookService;
	
	@Before
	public void createData() {
		for(int i=1; i<3; i++) {
			BookForm bookForm = new BookForm();
			bookForm.setId(i);
			bookForm.setTitle("Test Title" + i);
			bookForm.setWritter("Test Author" + i);
			bookForm.setPublisher("Test Publisher" + i);
			bookForm.setPrice(100 * (i + 1));
			bookService.create(bookForm);
		}
	}
	
	@Test
	public void testCreate()throws Exception {
		BookForm bookForm = new BookForm();
		bookForm.setId(0);
		bookForm.setTitle("Test Title");
		bookForm.setWritter("Test Author");
		bookForm.setPublisher("Test Publisher");
		bookForm.setPrice(100);
		
		assertThat(bookForm, is(bookService.create(bookForm)));
	}
	
	@Test
	public void findAllThree()throws Exception {
		assertEquals(3, bookService.findAll().size());
	}
	
	@Test
	public void findOneOK()throws Exception{
		assertNotNull(bookService.findOne(1));
	}
	
	@Test
	public void findOneNG()throws Exception{
		assertNull(bookService.findOne(5).getId());
	}
}