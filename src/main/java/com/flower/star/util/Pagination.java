package com.flower.star.util;

import lombok.Data;

@Data
public class Pagination {
	
	private int currentPage;			// 현재 페이지
    private int totalPages; 			// 전체 페이지
    private int blockSize;				// 블록 크기
    private int currentBlock;			// 현재 블록
    private int startPage;				// 시작 페이지
    private int endPage;				// 끝 페이지
    private boolean hasPreviousBlock;	// 이전 블록 여부
    private boolean hasNextBlock;		// 다음 블록 여부

    public Pagination(int currentPage, int totalPages, int blockSize) {
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.blockSize = blockSize;
        this.currentBlock = currentPage / blockSize;
        this.startPage = currentBlock * blockSize;
        this.endPage = Math.min(startPage + blockSize - 1, totalPages - 1);
        this.hasPreviousBlock = startPage > 0;
        this.hasNextBlock = endPage < totalPages - 1;
    }
    
}
