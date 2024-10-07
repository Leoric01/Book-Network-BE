package com.leoric.booknetworkbe.book;

import com.leoric.booknetworkbe.book.Book;
import com.leoric.booknetworkbe.book.BookRequest;
import com.leoric.booknetworkbe.book.BookResponse;
import com.leoric.booknetworkbe.book.BorrowedBookResponse;
import com.leoric.booknetworkbe.history.BookTransactionHistory;
import org.springframework.stereotype.Service;

import static com.leoric.booknetworkbe.file.FileUtils.readFileFromLocation;

@Service
public class BookMapper {

    public Book toBook(BookRequest request) {
        return Book.builder()
                .title(request.title())
                .authorName(request.authorName())
                .isbn(request.isbn())
                .synopsis(request.synopsis())
                .archived(false)
                .shareable(request.shareable())
                .build();
    }

    public BookResponse toBookResponse(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .authorName(book.getAuthorName())
                .isbn(book.getIsbn())
                .synopsis(book.getSynopsis())
                .rate(book.getRate())
                .archived(book.isArchived())
                .shareable(book.isShareable())
                .owner(book.getOwner().getFullname())
                .cover(readFileFromLocation(book.getBookCover()))
                .build();
    }

    public BorrowedBookResponse toBorrowedBookResponse(BookTransactionHistory history) {
        return BorrowedBookResponse.builder()
                .id(history.getBook().getId())
                .title(history.getBook().getTitle())
                .authorName(history.getBook().getAuthorName())
                .isbn(history.getBook().getIsbn())
                .rate(history.getBook().getRate())
                .returned(history.isReturned())
                .returnApproved(history.isReturnApproved())
                .build();
    }
}
