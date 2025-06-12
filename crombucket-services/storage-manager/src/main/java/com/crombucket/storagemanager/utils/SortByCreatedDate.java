package com.crombucket.storagemanager.utils;

import org.springframework.data.domain.Sort.Order;

public enum SortByCreatedDate {
  ASC,
  DESC;

  public Order getOrder(){
    return switch (this) {
      case ASC -> Order.by("createdAt");
      case DESC -> Order.desc("createdAt");
    };
  }
}
