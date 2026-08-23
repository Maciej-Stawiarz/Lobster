package ms.lobster.fishes;

import ms.lobster.fishes.models.dtos.FishDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("fish")
interface FishAPI {
	
	@PostMapping
	ResponseEntity<FishDTO> addFish(FishDTO fishDTO);
	
	@GetMapping
	ResponseEntity<FishDTO> getFish(Long id);
	
	@GetMapping
	ResponseEntity<List<FishDTO>> getAllListedFish();
	
	@DeleteMapping
	ResponseEntity<Void> removeFish(Long id);
}