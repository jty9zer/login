package com.rb.login.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> {
    private int currentPage;
    private int pageSize;
    private int totalPages;
    private long totalRecords;
    private List<T> data;
}

