package com.github.product.entity.bo;

import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author peach
 * @since 2020/11/19 16:11
 */
@Builder
public class NormalRedEnvelope extends RedEnvelope {
    @Override
    public List<Integer> doSplitRedEnvelope() {
        List<Integer> smallRedEnvelopes = new ArrayList<>();
        int splitAmount = getAmount() / getSize();
        for (int index = 0; index < getSize(); index++) {
            smallRedEnvelopes.add(splitAmount);
        }
        return smallRedEnvelopes;
    }
}