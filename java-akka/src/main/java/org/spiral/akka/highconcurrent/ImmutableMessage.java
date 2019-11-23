package org.spiral.akka.highconcurrent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 不可变的消息对象
 *
 * @author guoqing
 * @since ： 2019/7/7 21:08
 */
public final class ImmutableMessage {
    private final int sequenceNumber;

    private final List<String> values;

    public ImmutableMessage(int sequenceNumber, List<String> values) {
        this.sequenceNumber = sequenceNumber;
        this.values = Collections.unmodifiableList(new ArrayList<>(values));
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public List<String> getValues() {
        return values;
    }
}
