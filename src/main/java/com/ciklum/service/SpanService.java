package com.ciklum.service;

import com.ciklum.model.Span;
import com.ciklum.repo.SpanRepository;

import java.util.*;

public class SpanService {

    private SpanRepository spanRepository = new SpanRepository();

    public List<Integer> generate (Integer id) {
        List<Integer> ids = new ArrayList<>();
        Span mainSpan = findById(id);
        ids.addAll(handleIds(mainSpan));
        return ids;
    }

    private Collection<? extends Integer> handleIds(Span mainSpan) {
        List<Integer> ids = new ArrayList<>();
        ids.add(mainSpan.getId());
        mainSpan.getChildren().parallelStream().forEach(childSpan -> ids.addAll(Objects.requireNonNull(handleIds(childSpan))));
        return ids;
    }

    public Span create (int id, int value, Set<Span> children) {
        Span span = new Span(id, value, children);
        return spanRepository.save(span);
    }

    public Span update(Span span, Set<Span> newChildren) {
        Span thisSpan = spanRepository.findById(span.getId());
        thisSpan.getChildren().addAll(newChildren);
        return thisSpan;
    }

    public Span addChild (Span parent, Span child) {
        Span span = spanRepository.findById(parent.getId());
        span.getChildren().add(child);
        return span;
    }

    public void delete (Span span) {
        spanRepository.delete(span);
    }

    public Span findById(Integer id) {
        return spanRepository.findById(id);
    }

    public void print() {
        spanRepository.print();
    }
}
