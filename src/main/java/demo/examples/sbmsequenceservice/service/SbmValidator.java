package demo.examples.sbmsequenceservice.service;

import demo.examples.sbmsequenceservice.model.IndexHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class SbmValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(SbmValidator.class);

    public static boolean validateOn3(List<Integer> list) {
        int n = list.size();
        for (int x = 0; x < n - 2; x++) {
            for (int y = x + 1; y < n - 1; y++) {
                int z = y + 1;
                while (z < n) {
                    if (list.get(x) < list.get(z) && list.get(z) < list.get(y)) {
                        LOGGER.info("{},{},{}", list.get(x), list.get(y), list.get(z));
                        return true;
                    }
                    z += 1;
                }
            }
        }
        return false;
    }

    public static boolean validateOn2(List<Integer> list) {

        List<IndexHelper> indexer = new ArrayList<>();

        for (Integer val: list) {
            indexer.add(new IndexHelper(val, Integer.MAX_VALUE));
            int currIdx = indexer.size() - 1;
            if (currIdx > 0) {
                IndexHelper prev = indexer.get(currIdx - 1);
                indexer.get(currIdx).setMinLeft(Math.min(prev.getMinLeft(), prev.getValue())); //setting the LEFT
            }
            //analysing the RIGHT
            for (int i = 1; i < indexer.size() - 1; i++) {
                IndexHelper curr = indexer.get(i);
                if (val > curr.getMinLeft() && curr.getMinLeft() < curr.getValue() && val < curr.getValue()) {
                    LOGGER.info("{},{},{}",curr.getMinLeft(),curr.getValue(),val);
                    return true;
                }
            }
        }
        return false;
    }

}
