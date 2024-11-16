package org.hdo.trackFood.model;

public enum ProductType {

    LIQUID,
    SWEET,
    VEGETABLE, ORGANIC,
    MILK, CHEESE,
    FISH,
    MEAT, BIRD,
    CEREALS;

    public ProductType fromString(String string) {
        ProductType[] values = ProductType.values();
        for(ProductType pt : values) {
            if(pt.toString().equals(string)) {
                return pt;
            }
        }

        throw new RuntimeException("Product type not found by string: " + string);
    }

}
