package com.crombucket.storagemanager.dtos.response;

import java.time.LocalDate;

public record RegionResponse (
  String id,
  String regionName,
  String regionCode,
  LocalDate createdAt
){

}
