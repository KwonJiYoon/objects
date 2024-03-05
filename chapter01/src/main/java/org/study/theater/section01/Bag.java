package org.study.theater.section01;

public class Bag {

    // 초대장 + 현금이 있는 손님, 현금만 있는 손님 > 2가지 경우
    // Bag 인스턴스 생성 시점에 위 제약을 강제할 수 있는 생성자
    public Bag(long amount) {
        this(null, amount);
    }

    public Bag(Invitation invitation, long amount) {
        this.invitation = invitation;
        this.amount = amount;
    }

    private Long amount;
    private Invitation invitation;
    private Ticket ticket;

    // 초대장 보유 여부
    public boolean hasInvitation() {
        return invitation != null;
    }

    // 티켓 소유 여부
    public boolean hasTicket() {
        return ticket != null;
    }

    // 초대장을 티켓으로 교환
    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    // 현금 감소
    public void minusAmount(Long amount) {
        this.amount -= amount;
    }

    // 현금 증가
    public void plusAmount(Long amount) {
        this.amount += amount;
    }
}
