package com.sparta.security0531.model;

import com.sparta.security0531.dto.BlogRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Blog extends Timestamped{

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    private String contents; //내용

    public Blog(String contents) {
        this.contents = contents;
    }

    public Blog(BlogRequestDto requestDto){
        this.contents = requestDto.getContents();
    }

    public void update(BlogRequestDto requestDto){
        this.contents = requestDto.getContents();
    }
}
