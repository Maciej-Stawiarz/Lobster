package ms.lobster.fishes.models.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FishDTO {
	
	private Long id;
	private String name;
	private String species;
	private String color;
	private Integer age;
	private String smell;
}
