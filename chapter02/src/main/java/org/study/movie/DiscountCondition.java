package org.study.movie;

public interface DiscountCondition {
    boolean isSatisfiedBy(Screening screening);
}
