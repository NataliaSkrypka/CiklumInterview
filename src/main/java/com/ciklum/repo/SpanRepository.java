package com.ciklum.repo;

import com.ciklum.model.Span;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SpanRepository {

    private Map<Integer, Span> repo = new ConcurrentHashMap<>();

    void setRepo(Map<Integer, Span> repo) {
        this.repo = repo;
    }

    Map<Integer, Span> getRepo() {
        return repo;
    }

    public Span findById(Integer id) {
        return repo.get(id);
    }

    public Span save(Span span) {
        saveSpan(span);
        return span;
    }

    private void saveSpan(Span span) {
        if (!repo.containsKey(span.getId())) {
            repo.put(span.getId(), span);
            span.getChildren().parallelStream().forEach(this::saveSpan);
        }
    }

    public void delete(Span span) {
        Integer id = span.getId();
        repo.remove(id);
        repo.forEach((key, value) -> value.getChildren().remove(span));
    }

    public void print() {
        System.out.println(repo.toString());
    }
}
