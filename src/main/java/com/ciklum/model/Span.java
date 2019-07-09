package com.ciklum.model;

import java.util.Set;

public class Span {
    private int id;
    private int value;
    private Set<Span> el;

    public Span (int id, int value, Set<Span> children) {
        this.id = id;
        this.value = value;
        this.el = children;
    }

    public Integer getId() {
        return id;
    }

    public Set<Span> getChildren () {
        return el;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Span span = (Span) o;

        if (id != span.id) return false;
        if (value != span.value) return false;
        return el != null ? el.equals(span.el) : span.el == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + value;
        result = 31 * result + (el != null ? el.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Span{" +
                "id=" + id +
                ", value=" + value +
                ", el=" + el +
                '}';
    }
}
