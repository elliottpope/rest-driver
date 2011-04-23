package com.github.restdriver.serverdriver.matchers;

import java.util.Iterator;

import org.codehaus.jackson.JsonNode;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Matcher to check the size of a Json array.
 */
public final class WithSize extends TypeSafeMatcher<JsonNode> {

    private final Matcher<?> matcher;

    /**
     * Creates a new instance of this matcher.
     * 
     * @param matcher The matcher against which the size of the JSON node will be evaluated
     */
    public WithSize(Matcher<?> matcher) {
        this.matcher = matcher;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("A JSON array with size: ");
        matcher.describeTo(description);
    }

    @Override
    public boolean matchesSafely(JsonNode node) {

        if (!node.isArray()) {
            return false;
        }

        Iterator<JsonNode> nodeIterator = node.getElements();
        int nodeCount = 0;

        while (nodeIterator.hasNext()) {
            nodeIterator.next();
            nodeCount++;
        }

        return matcher.matches(nodeCount);
    }

}
