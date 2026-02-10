package me.rcortesb.browse.domain.entity.dto;

import java.util.ArrayList;
import java.util.List;

/*public record SearchFilter(List<String> tags,
							int minAge,
							int maxAge,
							int distance) {}*/

public class SearchFilter {
	private int page;
	private int pageSize;
	private int minAge;
	private int maxAge;
	private int distance;
	private List<String> tags;

	public SearchFilter() {}

	public int getPage() {
		return page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getMinAge() {
		return minAge;
	}

	public int getMaxAge() {
		return maxAge;
	}

	public int getDistance() {
		return distance;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setMinAge(int minAge) {
		this.minAge = minAge;
	}

	public void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public void normalizeDTO() {
		if (page < 0)
			page = 0;
		if (pageSize <= 0)
			pageSize = 16;
		if (minAge < 18)
			minAge = 18;
		if (maxAge < minAge)
			maxAge = minAge;
		if (distance <= 0)
			distance = 3;
		if (tags == null)
			tags = new ArrayList<>();
	}

	@Override
	public String toString() {
		return "SearchFilter [page=" + page + ", pageSize=" + pageSize + ", minAge=" + minAge + ", maxAge=" + maxAge
				+ ", distance=" + distance + ", tags=" + tags + "]";
	}
}