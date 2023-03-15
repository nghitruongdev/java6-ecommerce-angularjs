package com.vnco.cartservice.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
@AllArgsConstructor
public class MyPageable {
    private Integer page;
    private Integer size;
}
