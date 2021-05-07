package com.trace.keywords.impl;

import java.lang.reflect.Field;
import com.trace.keywords.Keywords;
import com.trace.keywords.annotation.Keyword;

public class AnnotationKeywords extends KeywordsImpl implements Keywords {

    public AnnotationKeywords(Keyword keyword, Field field)
            throws InstantiationException, IllegalAccessException {
        super(keyword.alias(), keyword.masker().newInstance(), field);
    }
}
