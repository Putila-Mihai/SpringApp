package com.example.demo.aop;

import com.example.demo.model.TrackInfo;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

@Getter
@Service
public class TrackCounter {

    private final Map<Long, TrackInfo> counter = new HashMap<>();

    public void incrementCounter(Long id, OffsetDateTime time) {
        TrackInfo trackInfo = counter.getOrDefault(id, new TrackInfo(0, time));
        trackInfo.incrementOccurrences();
        trackInfo.setTime(time);
        counter.put(id, trackInfo);
    }

    public void reset() {
        counter.clear();
    }

    public Map<Long, TrackInfo> getUserCounter() {
        return counter;
    }

    public void updateStats(OffsetDateTime offsetDateTime) {
        counter.forEach((k, v) -> {
            if (v.getTime().isBefore(offsetDateTime)) {
                counter.remove(k);
            }
        });
    }
}
