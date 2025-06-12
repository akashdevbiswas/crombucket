package com.crombucket.storagemanager.utils;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

public enum SortOrder {
  NAME,
  CREATED_AT;

  public Sort getSort(String name) {
    return switch (this) {
      case NAME -> Sort.by(Order.asc(name));
      case CREATED_AT -> Sort.by(Order.asc("createdAt"));
    };
  }
}
