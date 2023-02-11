package ru.practicum.explorewithme.compilations;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.explorewithme.compilations.dto.CompilationCreationDto;
import ru.practicum.explorewithme.compilations.dto.CompilationDto;
import ru.practicum.explorewithme.compilations.service.CompilationService;

@RestController
@RequiredArgsConstructor
public class CompilationController {

  private final CompilationService compilationService;

  @GetMapping("/compilations")
  public List<CompilationDto> getCompilations(@RequestParam Boolean pinned,
      @RequestParam int from,
      @RequestParam int size) {
    return compilationService.getCompilations(pinned, from, size);
  }

  @GetMapping("/compilations/{compilationId}")
  public CompilationDto getCompilation(@PathVariable long compilationId) {
    return compilationService.getCompilation(compilationId);
  }

  @PostMapping("/admin/compilations")
  public CompilationDto createCompilation(@RequestBody CompilationCreationDto compilationCreationDto) {
    return compilationService.createCompilation(compilationCreationDto);
  }

  @DeleteMapping("/admin/compilations/{compilationId}")
  public void deleteCompilation(@PathVariable long compilationId) {
    compilationService.deleteCompilation(compilationId);
  }

  @PatchMapping("/admin/compilations/{compilationId}")
  public CompilationDto updateCompilation(@PathVariable long compilationId,
      @RequestBody CompilationCreationDto compilationCreationDto) {
    return compilationService.updateCompilation(compilationId, compilationCreationDto);
  }
}
