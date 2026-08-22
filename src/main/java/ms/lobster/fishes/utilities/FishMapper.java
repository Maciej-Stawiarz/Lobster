package ms.lobster.fishes.utilities;

import ms.lobster.fishes.models.dtos.FishDTO;
import ms.lobster.fishes.models.entities.Fish;

public final class FishMapper {

	public static Fish toEntity(FishDTO dto) {
		return Fish.builder()
				.id(dto.getId())
				.name(dto.getName())
				.species(dto.getSpecies())
				.color(dto.getColor())
				.age(dto.getAge())
				.smell(dto.getSmell())
				.build();
	}
	
	public static FishDTO toDTO(Fish entity) {
		return FishDTO.builder()
				.id(entity.getId())
				.name(entity.getName())
				.species(entity.getSpecies())
				.color(entity.getColor())
				.age(entity.getAge())
				.smell(entity.getSmell())
				.build();
	}
}