package ms.lobster.fishes.models.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FishDTO {
	
	private Long id;
	private String name;
	private String species;
	private String color;
	private Integer age;
	private String smell;
}
