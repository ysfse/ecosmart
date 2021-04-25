package ecosmart.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PagingHeaders {
    PAGE_SIZE("Page-Size"),
    PAGE_NUMBER("Page-Number"),
    PAGE_OFFSET("Page-Offset"),
    PAGE_TOTAL("Page-Total"),
    COUNT("Count");

    

	private final String name;

	/*private PagingHeaders(String name) {
		this.name = name;
	}*/

	public String getName() {
		return name;
	}
	
	
}