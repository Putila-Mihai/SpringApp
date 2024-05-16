package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;


@Getter
public class TrackInfo {
    private int occurrences;
    @Setter
    private OffsetDateTime time;

    public TrackInfo(int occurrences, OffsetDateTime time) {
        this.occurrences = occurrences;
        this.time = time;
    }

    public void incrementOccurrences() {
        this.occurrences++;
    }

}