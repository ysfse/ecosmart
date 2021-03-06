package ecosmart.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * DTO using for pagination
 */
@Getter
@Setter
@AllArgsConstructor
public class PagingResponse {

    /**
     * entity count
     */
    private Long count;
    /**
     * page number, 0 indicate the first page.
     */
    private Long pageNumber;
    /**
     * size of page, 0 indicate infinite-sized.
     */
    private Long pageSize;
    /**
     * Offset from the of pagination.
     */
    private Long pageOffset;
    /**
     * the number total of pages.
     */
    private Long pageTotal;
    /**
     * elements of page.
     */
    private List<Product> elements;
	/*public PagingResponse(Long count, Long pageNumber, Long pageSize, Long pageOffset, Long pageTotal,
			List<Product> elements) {
		super();
		this.count = count;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.pageOffset = pageOffset;
		this.pageTotal = pageTotal;
		this.elements = elements;
	}*/
	public List<Product> getElements() {
		return elements;
	}
	public void setElements(List<Product> elements) {
		this.elements = elements;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Long getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Long pageNumber) {
		this.pageNumber = pageNumber;
	}
	public Long getPageSize() {
		return pageSize;
	}
	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}
	public Long getPageOffset() {
		return pageOffset;
	}
	public void setPageOffset(Long pageOffset) {
		this.pageOffset = pageOffset;
	}
	public Long getPageTotal() {
		return pageTotal;
	}
	public void setPageTotal(Long pageTotal) {
		this.pageTotal = pageTotal;
	}
	
    
}