package ms.lobster.fishes;

import ms.lobster.fishes.models.dtos.FishDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("fish")
interface FishAPI {
	
	@PostMapping
	ResponseEntity<FishDTO> addFish(@RequestBody FishDTO fishDTO);
	
	@GetMapping("{id}")
	ResponseEntity<FishDTO> getFish(@PathVariable("id") Long id);
	
	@GetMapping("all")
	ResponseEntity<List<FishDTO>> getAllListedFish();
	
	@DeleteMapping("{id}")
	ResponseEntity<Void> removeFish(@PathVariable("id") Long id);
}