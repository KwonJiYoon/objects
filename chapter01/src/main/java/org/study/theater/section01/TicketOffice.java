package org.study.theater.section01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TicketOffice {
    private Long amount;
    private List<Ticket> tickets = new ArrayList<>();

    public TicketOffice(Long amount, Ticket ... tickets) {
        this.amount = amount;
        this.tickets.addAll(Arrays.asList(tickets));
    }

    // tickets 컬렉션에서 맨 첫 번째 Ticket 반환
    public Ticket getTicket() {
        return tickets.remove(0);
    }

    // 금액 차감
    public void minusAmount(Long amount) {
        this.amount -= amount;
    }

    // 금액 더하기
    public void plusAmount(Long amount) {
        this.amount += amount;
    }

}
