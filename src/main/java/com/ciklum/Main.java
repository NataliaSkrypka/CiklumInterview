package com.ciklum;

import com.ciklum.model.Span;
import com.ciklum.service.SpanService;

import java.util.HashSet;

public class Main {

    public static void main(String[] args) {
        SpanService service = new SpanService();
        Span span1 = service.create(1, 1, new HashSet<>());
        Span span2 = service.create(2, 2, new HashSet<>());
        Span span3 = service.create(3, 3, new HashSet<>());
        Span span4 = service.create(4, 4, new HashSet<>());
        Span span5 = service.create(5, 5, new HashSet<>());
        Span span6 = service.create(6, 6, new HashSet<>());
        Span span7 = service.create(7, 7, new HashSet<>());
        Span span8 = service.create(8, 8, new HashSet<>());
        Span span9 = service.create(9, 9, new HashSet<>());
        service.addChild(span1, span2);
        service.addChild(span1, span3);

        service.addChild(span2, span4);
        service.addChild(span2, span5);

        service.addChild(span3, span6);

        service.addChild(span4, span7);
        service.addChild(span4, span8);
        service.addChild(span4, span9);

        service.print();
    }
}
