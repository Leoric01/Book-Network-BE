package com.leoric.booknetworkbe.book;

import lombok.*;

import java.util.Base64;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookResponse {

    private Integer id;
    private String title;
    private String authorName;
    private String isbn;
    private String synopsis;
    private String owner;
    private String cover;
    private double rate;
    private boolean archived;
    private boolean shareable;

    public void setCover(byte[] cover) {
        this.cover = Base64.getEncoder().encodeToString(cover);
    }

    public byte[] getCoverAsBytes() {
        return Base64.getDecoder().decode(cover);
    }
}
