package org.study.movie;

public class Reservation {

    private Customer customer; // 고객
    private Screening screening; // 상영 정보

    private Money fee; // 예매 요금

    private int audience; // 인원 수

    public Reservation(Customer customer, Screening screening, Money fee, int audience) {
        this.customer = customer;
        this.screening = screening;
        this.fee = fee;
        this.audience = audience;
    }
}
