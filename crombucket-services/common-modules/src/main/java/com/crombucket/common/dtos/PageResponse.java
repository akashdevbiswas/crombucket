package com.crombucket.common.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class PageResponse <T>{
  private Boolean isFirst;
  private Boolean isLast;
  private Integer page;
  private Integer size;
  private Integer numberOfElements;
  private Integer totalPages;
  private Long totalElements;
  private List<T> content;
}
