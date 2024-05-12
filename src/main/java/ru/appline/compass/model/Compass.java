package ru.appline.compass.model;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Component
@Getter
public class Compass {

    private final Map<String, List<Integer>> processedRange = new HashMap<>();

    public boolean setProcessedRange(Map<String, String> range) {
        processedRange.clear();
        range.forEach((key, value) -> {
            Integer a = null;
            Integer b = null;
            try {
                a = Integer.parseInt(value.split("-")[0]);
                b = Integer.parseInt(value.split("-")[1]);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException ignored) {}
            if (a != null && b != null) {
                if (a > b) {
                    List<Integer> list = new ArrayList<>();
                    list.addAll(IntStream.range(a, 360).boxed().toList());
                    list.addAll(IntStream.range(0, b + 1).boxed().toList());
                    processedRange.put(key.toLowerCase(), list);
                } else {
                    processedRange.put(key.toLowerCase(), IntStream.range(a, b + 1).boxed().toList());
                }
            }
        });
        List<Integer> res = processedRange.values().stream().flatMap(List::stream).toList();

        return processedRange.keySet().size() == range.keySet().size() &&
                res.size() == 360 &&
                res.stream().mapToInt(v -> v).max().getAsInt() == 359 &&
                res.stream().mapToInt(v -> v).min().getAsInt() == 0;
    }

    public String getSideByDegree(Integer degree) {
        return processedRange.entrySet().stream()
                .filter(entry -> entry.getValue().contains(degree))
                .map(Map.Entry::getKey)
                .findFirst().orElse(null);
    }
}
