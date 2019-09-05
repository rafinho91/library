package com.library.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.library.input.BookInput;
import com.library.input.Isbn;
import com.library.input.VolumeInfo;

public class BookTest {

	@Test
	public void getMatchingIsbnTest() {
		Isbn[] isbns1 = {new Isbn("ISBN_10", "0123456789"), new Isbn("ISBN_13", "0123456789123")};
		VolumeInfo volumeInfo1 = VolumeInfo.builder().isbns(isbns1).build();
		BookInput bookInput1 = BookInput.builder().volumeInfo(volumeInfo1).id("ID1").build();
		
		Isbn[] isbns2 = {new Isbn("ISBN_10", "0123456789")};
		VolumeInfo volumeInfo2 = VolumeInfo.builder().isbns(isbns2).build();
		BookInput bookInput2 = BookInput.builder().volumeInfo(volumeInfo2).id("ID2").build();
		
		Book book = new Book();
		String result1 = ReflectionTestUtils.invokeMethod(book, "getMatchingIsbn", bookInput1, "ISBN_13");
		String result2 = ReflectionTestUtils.invokeMethod(book, "getMatchingIsbn", bookInput2, "ISBN_13");
		
		assertThat(result1).isEqualTo("0123456789123");
		assertThat(result2).isNull();
	}
	
}
