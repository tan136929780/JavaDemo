package com.client.model;

import lombok.Data;
import java.util.List;

@Data
public class PageResult {
    private Long    currentPage = 1L;
    private Long    pageSize    = 10L;
    private Long    total       = 0L;
    private Long    pages       = 0L;
    private List<?> list        = null;
}
