package com.sravan.Service;

import java.util.regex.Pattern;



public class ClassFilter<T>
implements Predicate<T>
{
private final Pattern pattern;

public ClassFilter(final String regex)
{
    pattern = Pattern.compile(regex);
}

public boolean apply(T input)
{
    return true;
}
}