package com.trace.keywords.impl;

import java.lang.reflect.Field;
import com.trace.keywords.Keywords;
import com.trace.keywords.Masker;

public class KeywordsImpl implements Keywords {

    private final String alias;

    private final Masker masker;

    private final Field field;

    public KeywordsImpl(String alias, Masker masker, Field field) {
        this.alias = alias == null || alias.trim().isEmpty() ? field.getName() : alias;
        this.masker = masker;
        this.field = field;
    }

    @Override
    public Field getField() {
        return field;
    }

    @Override
    public String getAlias() {
        return alias;
    }

    @Override
    public Masker getMasker() {
        return masker;
    }
}
