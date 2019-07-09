package com.ciklum.repo;

import com.ciklum.model.Span;
import com.ciklum.service.SpanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.*;

class SpanRepositoryTest {

    private Map<Integer, Span> repo = new ConcurrentHashMap<>();
    private Span mainSpan;
    SpanRepository spanRepository = new SpanRepository();

    @BeforeEach
    void setUp() {
        Span span1 = new Span(1, 1, new HashSet<>());
        Span span2 = new Span(2, 2, new HashSet<>());
        Span span3 = new Span(3, 3, new HashSet<>());
        Span span4 = new Span(4, 4, new HashSet<>());
        HashSet<Span> span5Children = new HashSet<>();
        span5Children.add(span1);
        span5Children.add(span2);
        span5Children.add(span3);
        span5Children.add(span4);
        Span span5 = new Span(5, 5, span5Children);
        HashSet<Span> span6Children = new HashSet<>();
        span6Children.add(span1);
        span6Children.add(span5);
        mainSpan = new Span(6, 6, span6Children);

        repo.put(6, mainSpan);
        repo.put(1, span1);
        repo.put(2, span2);
        repo.put(3, span3);
        repo.put(4, span4);
        repo.put(5, span5);

        spanRepository.setRepo(repo);
    }

    @Test
    void findById() {
        Span spanActual = spanRepository.findById(2);
        assertEquals(new Span(2, 2, new HashSet<>()), spanActual);
    }

    @Test
    void save() {
        SpanRepository spanRepository = new SpanRepository();
        Span resultActual = spanRepository.save(mainSpan);
        assertEquals(mainSpan, resultActual);
        assertEquals(repo, spanRepository.getRepo());
    }

    @Test
    void delete() {
        spanRepository.delete(new Span(1, 1, new HashSet<>()));
        ConcurrentHashMap<Integer, Span> repoActual = (ConcurrentHashMap<Integer, Span>)spanRepository.getRepo();
        ConcurrentHashMap<Integer, Span> repoExpected = new ConcurrentHashMap<>();
        Span span2 = new Span(2, 2, new HashSet<>());
        Span span3 = new Span(3, 3, new HashSet<>());
        Span span4 = new Span(4, 4, new HashSet<>());
        HashSet<Span> span5Children = new HashSet<>();
        span5Children.add(span2);
        span5Children.add(span3);
        span5Children.add(span4);
        Span span5 = new Span(5, 5, span5Children);
        HashSet<Span> span6Children = new HashSet<>();
        span6Children.add(span5);
        mainSpan = new Span(6, 6, span6Children);

        repoExpected.put(6, mainSpan);
        repoExpected.put(2, span2);
        repoExpected.put(3, span3);
        repoExpected.put(4, span4);
        repoExpected.put(5, span5);

        assertEquals(repoExpected.size(), repoActual.size());
        for (Integer id : repoActual.keySet()) {
            assertEquals(repoExpected.get(id), repoActual.get(id));
        }
    }

}