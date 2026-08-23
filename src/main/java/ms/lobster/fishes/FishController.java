package ms.lobster.fishes;

import lombok.RequiredArgsConstructor;
import ms.lobster.fishes.models.dtos.FishDTO;
import ms.lobster.fishes.utilities.FishMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FishController implements FishAPI {
	
	private final FishService fishService;
	
	@Override
	public ResponseEntity<FishDTO> addFish(FishDTO fishDTO) {
		return new ResponseEntity<>(
				fishService.addFish(FishMapper.toEntity(fishDTO)),
				HttpStatus.CREATED
		);
	}
	
	@Override
	public ResponseEntity<FishDTO> getFish(Long id) {
		return new ResponseEntity<>(
				fishService.getFish(id),
				HttpStatus.OK
		);
	}
	
	@Override
	public ResponseEntity<List<FishDTO>> getAllListedFish() {
		return new ResponseEntity<>(
				fishService.getAllListedFish(),
				HttpStatus.OK
		);
	}
	
	@Override
	public ResponseEntity<Void> removeFish(Long id) {
		fishService.removeFish(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}