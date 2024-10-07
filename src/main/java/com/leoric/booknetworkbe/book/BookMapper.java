package com.leoric.booknetworkbe.book;

import com.leoric.booknetworkbe.history.BookTransactionHistory;
import org.springframework.stereotype.Service;

import java.util.Base64;

import static com.leoric.booknetworkbe.file.FileUtils.readFileFromLocation;

@Service
public class BookMapper {

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
                .cover(convertToBase64(readFileFromLocation(book.getBookCover())))
                .build();
    }
    private String convertToBase64(byte[] coverBytes) {
        if (coverBytes != null) {
            return Base64.getEncoder().encodeToString(coverBytes);
        }
        return null; // TODO Handle case when coverBytes is null
    }

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
