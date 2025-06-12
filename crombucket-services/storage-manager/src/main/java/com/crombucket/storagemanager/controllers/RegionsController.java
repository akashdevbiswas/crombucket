package com.crombucket.storagemanager.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.crombucket.common.dtos.PageResponse;
import com.crombucket.storagemanager.dtos.requests.RegionRequest;
import com.crombucket.storagemanager.dtos.response.RegionResponse;
import com.crombucket.storagemanager.service.RegionsService;
import com.crombucket.storagemanager.utils.SortOrders.SortDirection;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/regions")
@RequiredArgsConstructor
public class RegionsController {

  private final RegionsService regionsService;

  @GetMapping
  public ResponseEntity<PageResponse<RegionResponse>> findRegions(
      @RequestParam(required = false, defaultValue = "0") Integer page,
      @RequestParam(required = false, defaultValue = "6") Integer size,
      @RequestParam(required = false, defaultValue = "") String search,
      @RequestParam(required = false, defaultValue = "createdAt") String sortBy,
      @RequestParam(required = false, defaultValue = "ASC") SortDirection direction) {
    return ResponseEntity.ok().body(regionsService.getRegionsBy(page, size, search, sortBy,direction));
  }

  @GetMapping(value = "/all")
  public ResponseEntity<List<RegionResponse>> findAllRegions(){
    return ResponseEntity.ok().body(regionsService.getAllRegions());
  }

  @PostMapping
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void createRegion(@RequestBody RegionRequest regionRequest ){
    regionsService.saveRegion(regionRequest);
  }

}
