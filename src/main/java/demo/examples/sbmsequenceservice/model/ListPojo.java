package demo.examples.sbmsequenceservice.model;

import java.util.ArrayList;
import java.util.List;

public class ListPojo {
    private final List<Integer> seq;

    public List<Integer> getSeq() {
        return seq;
    }

    public ListPojo(List<Integer> seq) {
        this.seq = seq;
    }

    public ListPojo() {
        this.seq = new ArrayList<>();
    }
}
