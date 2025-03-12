package com.harryPotter.util;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PageableResponse<T> {

	private List<T> content;
	private int pageNumber;
	private int pageSize;
	private long totalElement;
	private int totalPages;
	private Boolean lastPage;
}
