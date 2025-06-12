package com.crombucket.storagemanager.utils;

import java.util.Arrays;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import lombok.AllArgsConstructor;
import lombok.Builder;

public class SortOrders {

  public static enum SortDirection {
    ASC,
    DESC
  }

  @Builder
  @AllArgsConstructor
  public static class FieldOrders {
    SortDirection direction;
    String fieldName;
  }

  public static Sort getOrders(FieldOrders... fieldOrders) {
    List<Order> ordersList = Arrays.stream(fieldOrders).map(fields -> switch (fields.direction) {
      case ASC -> Order.asc(fields.fieldName);
      case DESC -> Order.desc(fields.fieldName);
    }).toList();

    return Sort.by(ordersList);
  }

  public static Sort getOrders(String sortBy, SortDirection direction) {
    return switch (direction) {
      case ASC -> Sort.by(sortBy);
      case DESC -> Sort.by(Sort.Direction.DESC, sortBy);
    };
  }
}
