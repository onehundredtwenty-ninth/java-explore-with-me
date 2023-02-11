package ru.practicum.explorewithme.event;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.explorewithme.event.dto.FullEventInfo;
import ru.practicum.explorewithme.event.dto.InputEventDto;
import ru.practicum.explorewithme.event.dto.OutputEventDto;
import ru.practicum.explorewithme.event.service.EventService;

@RestController
@RequiredArgsConstructor
public class EventController {

  private final EventService eventService;

  @GetMapping("/users/{userId}/events")
  public List<OutputEventDto> getEvents(@PathVariable long userId,
      @RequestParam(defaultValue = "0") int from,
      @RequestParam(defaultValue = "10") int size) {
    return eventService.getEvents(userId, from, size);
  }

  @PostMapping("/users/{userId}/events")
  @ResponseStatus(HttpStatus.CREATED)
  public OutputEventDto createEvent(@PathVariable long userId, @RequestBody InputEventDto inputEventDto) {
    return eventService.createEvent(userId, inputEventDto);
  }

  @GetMapping("/users/{userId}/events/{eventId}")
  public OutputEventDto getEvent(@PathVariable long userId, @PathVariable long eventId) {
    return eventService.getEvent(userId, eventId);
  }

  @PatchMapping("/users/{userId}/events/{eventId}")
  public OutputEventDto updateEvent(@PathVariable long userId,
      @PathVariable long eventId,
      @RequestBody InputEventDto inputEventDto) {
    return eventService.updateEvent(userId, eventId, inputEventDto);
  }

  @GetMapping("/admin/events")
  public List<FullEventInfo> searchEvents(@RequestParam(required = false) List<Long> users,
      @RequestParam(required = false) List<String> states,
      @RequestParam(required = false) List<Integer> categories,
      @RequestParam(required = false) String rangeStart,
      @RequestParam(required = false) String rangeEnd,
      @RequestParam(defaultValue = "0") int from,
      @RequestParam(defaultValue = "10") int size) {
    return eventService.searchEvents(users, states, categories, rangeStart, rangeEnd, from, size);
  }

  @PatchMapping("/admin/events/{eventId}")
  public OutputEventDto updateEvent(@PathVariable long eventId, @RequestBody InputEventDto inputEventDto) {
    return eventService.updateEvent(eventId, inputEventDto);
  }
}
