package com.example.xowrld.Service;

import org.springframework.stereotype.Component;

import java.util.List;

import java.util.Random;

@Component

public class SlotService {

    private List<String> loops;
    private List<String> beats;
    private List<String> oneshots;

    public String randomloop(){
        loops.add("loop");
        Random random = new Random();
        String randomElement = loops.get(random.nextInt(loops.size()));
        return randomElement;
    }
    public String randombeat(){
        beats.add("beat");
        Random random = new Random();
        String randomElement = beats.get(random.nextInt(beats.size()));
        return randomElement;
    }
    public String randomoneshot(){
        beats.add("oneshot");
        Random random = new Random();
        String randomElement = oneshots.get(random.nextInt(oneshots.size()));
        return randomElement;
    }

    public SlotService(List<String> loops, List<String> beats, List<String> oneshots) {
        this.loops = loops;
        this.beats = beats;
        this.oneshots = oneshots;
    }

    public List<String> getLoops() {
        return loops;
    }

    public void setLoops(List<String> loops) {
        this.loops = loops;
    }

    public List<String> getBeats() {
        return beats;
    }

    public void setBeats(List<String> beats) {
        this.beats = beats;
    }

    public List<String> getOneshots() {
        return oneshots;
    }

    public void setOneshots(List<String> oneshots) {
        this.oneshots = oneshots;
    }
}
