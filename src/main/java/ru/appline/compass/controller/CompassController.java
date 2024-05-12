package ru.appline.compass.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.appline.compass.model.Compass;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@RestController
@RequestMapping("compass")
public class CompassController {

    private final Compass compass;

    @GetMapping(value = "/get", produces = "application/json")
    public Map<String, String> getSide(@RequestBody Map<String, Integer> request) {
        HashMap<String, String> res = new HashMap<>();
        if (!request.containsKey("Degree")) {
            res.put("Side", "Ключ Degree - отсутсвует в вашем запросе");
        } else {
            String side = compass.getSideByDegree(request.get("Degree"));
            res.put("Side", Objects.requireNonNullElse(side, "Значение ключа Degree не найдено в диапазоне"));
        }
        return res;
    }

    @PostMapping(value = "/range", consumes = "application/json")
    public String setRange(@RequestBody Map<String, String> range) {
        if (compass.setProcessedRange(range))
            return "Диапазоны успешно добавлены!";
        return "Ошибка при добавлении диапазонов, проверьте данные!";
    }
}
