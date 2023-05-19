package com.jake.pattern.metacoding.ex03_adaptor;

public class TigerAdaptor extends Animal {
    private OuterTiger outerTiger;

    public TigerAdaptor(OuterTiger outerTiger) {
        this.outerTiger = outerTiger;
    }

    @Override
    public String getName() {
        return outerTiger.getFullName();
    }
}
