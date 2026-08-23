package ms.lobster.fishes;

import lombok.RequiredArgsConstructor;
import ms.lobster.fishes.models.dtos.FishDTO;
import ms.lobster.fishes.models.entities.Fish;
import ms.lobster.fishes.utilities.FishMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FishService {
	
	private final FishRepository fishRepository;
	
	public FishDTO addFish(Fish fishEntity) {
		Fish savedFish = fishRepository.save(fishEntity);
		return FishMapper.toDTO(savedFish);
	}
	
	public FishDTO getFish(Long id) {
		Fish fish = fishRepository
				.findById(id)
				.orElseThrow(() -> new RuntimeException("Fish not found"));
		
		return FishMapper.toDTO(fish);
	}
	
	public List<FishDTO> getAllListedFish() {
		return fishRepository.findAll().stream()
				.map(FishMapper::toDTO)
				.toList();
	}
	
	public void removeFish(Long id) {
		fishRepository.deleteById(id);
	}
}