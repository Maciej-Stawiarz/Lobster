package ms.lobster.fishes;

import lombok.RequiredArgsConstructor;
import ms.lobster.fishes.models.dtos.FishDTO;
import ms.lobster.fishes.models.entities.Fish;
import ms.lobster.fishes.utilities.FishMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FishService {
	
	private final FishRepository fishRepository;
	
	@Cacheable(value = "fish", key = "#id")
	public FishDTO getFish(Long id) {
		Fish fish = fishRepository
				.findById(id)
				.orElseThrow(() -> new RuntimeException("Fish not found"));
		
		return FishMapper.toDTO(fish);
	}
	
	@Cacheable(value = "allFishes")
	public List<FishDTO> getAllListedFish() {
		return fishRepository.findAll().stream()
				.map(FishMapper::toDTO)
				.toList();
	}
	
	@Caching(evict = {
		@CacheEvict(value = "allFishes", allEntries = true)
	})
	public FishDTO addFish(Fish fishEntity) {
		if (fishEntity == null || fishEntity.getId() != null) {
			throw new IllegalArgumentException("Id has to be null for an entity to be saved properly");
		}
		
		Fish savedFish = fishRepository.save(fishEntity);
		return FishMapper.toDTO(savedFish);
	}
	
	@Caching(evict = {
		@CacheEvict(value = "fish", key = "#id"),
		@CacheEvict(value = "allFishes", allEntries = true)
	})
	public void removeFish(Long id) {
		fishRepository.deleteById(id);
	}
}