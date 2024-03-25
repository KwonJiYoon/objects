### Chapter02 - 객체지향 프로그래밍
### 01. 영화 예매 시스템 <br>

>**_요구사항 살펴보기_**<br>
* **요구사항**
    * 사용자는 영화 예매 시스템을 이용해 쉽고 빠르게 보고싶은 영화를 예매할 수 있다.
      <br><br>
* **용어**
    * `영화` : 영화에 대한 기본 정보
        * 제목
        * 상영시간
        * 가격 정보
    <br><br>
    * `상영` : 실제로 관객들이 영화를 관람하는 사건 (_사용자가 실제로 예매하는 대상_)
        * 상영 일자
        * 시간
        * 순번
    <br><br><br>
* **규칙**
    * `할인 조건` : 가격의 할인 여부 결정
        * `순서 조건` : 상영 순번을 이용해 할인 여부를 결정하는 규칙 <br>
        ex ) 순서 조건의 순번이 10인 경우, 매일 10번째로 상영되는 영화를 예매한 사용자들에게 할인 제공
        * `기간 조건` : 영화 상영 시작 시간을 이용해서 할인 여부를 결정하는 규칙 <br>
        요일, 시작 시간, 종료 시간 세부분으로 구성되고, 영화 시작 시간이 해당 기간에 포함되는 경우 할인 제공.
        ex ) 요일이 원요일, 시작 시간 오전 10시, 종료 시간 오후 1시인 기간 조건을 사용하면,<br>
        매주 월요일 오전 10시 ~ 오후 1시 사이에 상영되는 영화에 대해 할인 적용<br><br>
        * _다수의 할인 조건을 함께 지정할 수 있고 순서 조건, 기간 조건을 섞을 수 있음._
    <br><br><br>
    * `할인 정책` :  할인 요금 결정
        * `금액 할인 정책` : 예매 요금에서 일정 금액을 할인해주는 방식
        * `비율 할인 정책` : 정가에서 일정 비율의 요금을 할인해주는 방식
        * ex ) 영화 가격이 9,000원이고 금액 할인 정책이 800원인 경우 : 8,200원 <br>
          영화 가격이 9,000원이고 비율 할인 정책이 10%인 경우 : 8,100원<br><br>
        * _할인 정책을 지정하지 않아도 되지만, 영화 별 하나의 할인 정책만 할당 가능._
          <br><br><br>
    <details open>
    <summary>영화 상영 표</summary>
    
    <table>
      <tr>
        <td>영화</td>
        <td>할인 정책</td>
        <td>할인 조건</td>
      </tr>
      <tr>
        <td rowspan="4">아바타<br>가격: 10,000원</td>
        <td rowspan="4">금액 할인 정책 <br> 할인액: 800원</td>
        <td>순번 조건<br>조조 상영</td>
      </tr>
      <tr>
        <td>순번 조건<br>10회 상영</td>
      </tr>
      <tr>
        <td>기간 조건<br>월요일 10:00~12:00 상영 시작</td>
      </tr>
      <tr>
        <td>기간 조건<br>목요일 18:00~21:00 상영 시작</td>
      </tr>
      <tr>
        <td rowspan="3">타이타닉<br>가격: 11,000원</td>
        <td rowspan="3">비율 할인 정책 <br> 할인율: 10%</td>
        <td>기간 조건<br>화요일 14:00~17:00 사이 상영 시작</td>
      </tr>
      <tr>
        <td>순번 조건<br>2회 상영</td>
      </tr>
      <tr>
        <td>기간 조건<br>목요일 10:00~14:00 사이 상영 시작</td>
      </tr>
      <tr>
        <td>스타워즈<br>가격: 10,000원</td>
        <td>없음</td>
        <td>없음</td>
      </tr>
    </table>
    </details>
    
    <style>
      table td {
        border: 1px solid;
      }
    </style>

<br><br>
할인을 적용하기 위해 할인 조건과 할인 정책을 조합해서 사용한다.<br>
사용자의 예매 정보가 할인 조건에 하나라도 만족하는지 체크 후,<br>
할인 조건을 만족할 경우에만 할인 정책을 이용해 할인 요금을 계산한다. <br>
할인 조건을 만족하지 못하는 경우와 할인 정책이 적용되어 있지 않은 경우는 요금 할인을 적용하지 않는다.
<br>
<br>
**Example**<br>
_목요일 7회차 18:00 ~ 20:00 아바타 2인을 예매한다고 가정.<br>
-> 예매 정보가 할인 조건에 만족하는 경우 1인당 800원 할인<br>
-> 2인 이므로 총 1,600원 할인 적용 :: total 18,400원_

---

### 02. 객체지향 프로그래밍 향해 <br>
>**_협력, 객체, 클래스_**<br>

객체 지향은 객체를 지향하는 것.<br>

가장 먼저 어떤 클래스가 필요한지 고민하고 결정한 후 클래스에 어떤 메서드와 속성이 필요한지 고민 <br>
-> 객체 지향의 본질과는 거리가 멀다.<br>

클래스가 아닌 객체에 초점을 맞춰야 진정한 객체지향 패러다임으로의 전환이 가능하다.<br>
1. 어떤 클래스가 필요한지 고민하기 전에 어떤 객체들이 필요한지 고민해야한다.<br><br>
클래스는 공통적인 상태와 행동을 공유하는 객체들을 추상화한 것이기 때문에,<br>
클래스의 윤곽을 잡기 위해서 어떤 객체들이 어떤 상태와 행동을 가지는지 먼저 결정해야한다.<br><br>
객체를 중심에 두는 접근 방법은 설계를 단순하고 깔끔하게 만들 수 있다.<br>


2. 객체를 독립적인 존재가 아닌 기능을 구현하기 위해 협력하는 공동체의 일원으로 봐야한다.<br>
객체는 홀로 존재하는 것이 아닌 다른 객체에게 도움을 주거나 의존하며 살아가는 협력적인 존재이다.<br>
객체를 협력하는 공동체의 일원으로 바라보는 것은 설계를 유연하고 확장 가능하게 만든다. <br>
객체의 모양과 윤곽이 잡힌 후 공통된 특성과 상태를 가진 객체들을 타입으로 분류하고<br>
이 타입을 기반으로 클래스를 구현하면 된다.<br><br><br>


>**_도메인의 구조를 따르는 프로그램 구조_**<br>

* **소프트웨어**
    * 사용자가 원하는 어떤 문제를 해결하기 위함이 목적
    * 영화 예매 시스템의 목적 :: 영화를 좀 더 쉽고 빠르게 예매하려는 사용자의 문제 해결
<br><br>
* **도메인(domain)**
    * 문제 해결을 위해 사용자가 프로그램을 사용하는 분야
    * 객체지향 패러다임에서는 요구사항과 프로그램을 객체라는 동일한 관점에서 바라보기 때문에<br>
  도메인을 구성하는 개념들이 프로그램의 객체와 클래스로 매끄럽게 연결된다.<br>
<br>

일반적으로 클래스의 이름은 대응되는 도메인의 개념의 이름과 동일하거나 유사하게 지어야한다.<br>
클래스 사이의 관계도 프로그램의 구조를 이해하고 예상하기 쉽게 만들어야한다.<br>
➱ 영화(Movie), 상영(org.study.movie.Screening), 할인 정책(DiscountPolicy), 금액 할인 정책(AmountDiscountPolicy), 비율 할인 정책(PercentDiscountPolicy), 할인 조건(discountCondition),<br> 순번 조건(SequenceCondition), 기간 조건(PeriodCondition)
<br><br><br>


>**_클래스 구현하기_**<br>
```java
public class Screening {
    private Movie movie; // 상영할 영화
    private int sequence; // 순번
    private LocalDateTime whenScreened; // 상영 시작 시간

    public Screening(Movie movie, int sequence, LocalDateTime whenScreened) {
        this.movie = movie;
        this.sequence = sequence;
        this.whenScreened = whenScreened;
    }

    // 상영 시작 시간 반환
    public LocalDateTime getStartTime() {
        return whenScreened;
    }

    // 순번 일치 여부 검사
    public boolean isSequence(int sequence) {
        return this.sequence == sequence;
    }

    // 기본 요금 반환
    public Money getMovieFee() {
        return movie.getFee();
    }
}
```

위 코드에서 인스턴스 변수의 가시성은 private, 메서드의 가시성은 public이다. <br>
클래스의 내/외부로 구분하는 이유는 경계의 명확성이 객체의 자율성을 보장하기 때문이다.
<br><br>

**_자율적인 객체_** <Br>

1. 객체는 `상태(state)`와 `행동(behavior)`을 함께 가지는 복합적인 존재
2. 객체가 스스로 판단하고 행동하는 `자율적인 존재`

* 데이터와 기능을 객체 내부로 함께 묶는 것 :: `캡슐화`
* 외부에서의 접근을 통제할 수 있는 것 :: `접근 제어 (access control)`
    * 접근 제어를 위해 public, protected, private 등 `접근 수정자 (access modifier)` 사용
    * **퍼블릭 인터페이스(public interface)** : 외부에서 접근 가능한 부분
    * **구현(implementation)** : 오직 내부에서만 접근 가능한 부분
* 객체지향 핵심 원칙 :: `인터페이스와 구현의 분리(separation of interface and implementation)`
* 외부에 제공하는 메서드는 public, 서브클래스나 내부에서만 접근 간능해야하는 매서드는 protected, private
* public으로 지정된 메서드만 퍼블릭 인터페이스에 포함, private/protected는 구현에 포함
<br><br>

**_프로그래머의 자유_** <Br>

* **클래스 작성자 (class creator)** :  새로운 데이터 타입을 프로그램에 추가
    * 클라이언트 프로그래머에세 필요한 부분만 공개하고 나머지는 숨김
* **클라이언트 프로그래머 (client programmer)** : 클래스 작성자가 추가한 데이터 타입 사용
    * 필요한 클래스들을 엮어서 애플리케이션을 빠르고 안정적으로 구축

_클라이언트 프로그래머가 숨겨 놓은 부분에 마음대로 접근할 수 없도록 방지함으로써 클라이언트 프로그래머에 대한 영향을<br>
걱정하지 않고 내부 구현을 마음대로 변경할 수 있다 :: `구현 은닉 (implementation hiding)`_
<br><br>

>**_협력하는 객체들의 공동체_**<br>

```java
public class Screenning {
    public Reservation reserve(Customer customer, int audienceCount){
        return Reservation(customer, this, calculateFee(audienceCount), audienceCount);
    }

    private Money calculateFee(int audienceCount) {
        return movie.calculateMovieFee(this).times(audienceCount);
    }
}
```

```java
public class Money {
    public static final Money ZERO = Money.wons(0);

    private final BigDecimal amount;

    public  static Money wons(long amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    public static Money wons(double amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    Money(BigDecimal amount) {
        this.amount = amount;
    }

    public Money plus(Money amount) {
        return new Money(this.amount.add(amount.amount));
    }

    public Money minus(Money amount) {
        return new Money(this.amount.subtract(amount.amount));
    }

    public Money times(double percent) {
        return new Money(this.amount.multiply(BigDecimal.valueOf(percent)));
    }

    public boolean isLessThan(Money other) {
        return amount.compareTo(other.amount) < 0;
    }

    public boolean isGreaterThanOrEqual(Money other) {
        return amount.compareTo(other.amount) >= 0;
    }
}
```

* Long 타입은 변수의 크기, 연산자의 종류와 관련된 구현 관점의 제약은 표현할 수 있지만,<br>
Money 타입처럼 저장하는 값이 금액과 관련되어 있다는 의미는 전달할 수 없다.
* 객체를 이용해 도메인의 의미를 풍부하게 표현할 수 있고, 개념을 명시적으로 표현하는 것은<br>
전체적인 설계의 명확성과 유연성을 높일 수 있다.

```java
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
```
Screening, Movie, Reservation 인스턴스 들은 서로의 메서드를 호출하며 상호작용한다.<br>
시스템의 어떤 기능을 구현하기 위해 객체들 사이에 이뤄지는 상호작용이 `협력(Collaboration)`이다.
<br><br><br>

>**_협력에 관한 짧은 이야기_**<br>

* `요청(request)` : 다른 객체의 인터페이스에 공개된 행동을 수행하도록 요청
* `응답(request)` : 요청을 받은 객체는 자율적인 방법에 따라 요청 처리 후 응답
* `메시지 전송(send a message)` : 다른 객체와 상호작용 할 수 있는 유일한 방법 
* `메시지 수신(receive a message)` : 다른 객체에게 요청이 도착
* `메서드(method)` : 수신 된 메시지를 처리하기 위한 방법


---

### 03. 할인 요금 구하기 <br>
>**_할인 요금 계산을 위한 협력 시작하기_**<br>
```java
public class Movie {
    
    private String title; // 제목
    private Duration runningTime; // 상영시간
    private Money fee; // 기본 요금
    private DiscountPolicy discountPolicy; // 할인 정책

    public Movie(String title, Duration runningTime, Money fee, DiscountPolicy discountPolicy) {
        this.title = title;
        this.runningTime = runningTime;
        this.fee = fee;
        this.discountPolicy = discountPolicy;
    }

    public Money getFee() {
        return fee;
    }
    
    public Money calculateMovieFee(Screening screening) {
        return fee.minus(discountPolicy.calculateDiscountAmount(screening));
    }
}
```
discountPolicy에 메시지를 전송할 뿐 할인 정책을 판단하는 코드는 존재하지 ❌<br>
➱ **상속(inheritance), 다형성, 추상화(abstraction)** 의 개념/원리가 숨겨져있음<br>
<br>
>**_할인 정책과 할인 조건_**<br>
```java
public class DiscountPolicy {
    
    private List<DiscountCondition> conditions = new ArrayList<>();
    
    public DiscountPolicy(DiscountCondition ...conditions) {
        this.conditions = Arrays.asList(conditions);
    }
    
    public Money calculateDiscountAmount(Screening screening) {
        for (DiscountCondition each : conditions) {
            // 할인 조건을 만족하는 경우 :: 추상 메서드인 getDiscountAmount 호출
            if (each.isSatisfiedBy(screening)) {
                return getDiscountAmount(screening);
            }
        }
        // 만족하는 할인 조건이 없는 경우 :: 0으로 리턴
        return Money.ZERO;
    }
    
    // 실제 애플리케이션에서는 DiscountPolicy의 인스턴스 생성이 필요없기 때문에
    // 추상 클래스(abstract class)로 구현
    abstract protected Money getDiscountAmount(Screening screening);
    
}
```
할인 정책의 금액 할인 / 비율 할인 정책은 대부분의 코드가 유사하고, 할인 요금 계산 방식에만 차이가 있을 것이다.<br>
중복 코드를 제거하기 위해 부모 클래스인 DiscountPolicy 안에 중복코드를 작성하고, 두 클래스에서 상속 받게 할 예정<br><br>

전체적인 흐름은 DiscountPolicy에서 정의하지만, 실제 요금 계산을 하는 부분은 getDiscountAmount 추상 메서드 위임<br>
DiscountPolicy를 상속받은 자식 클래스의 오버라이딩 메서드가 실행 될 것이다.<br><br>

`TEMPLATE METHOD`패턴 :: 부모 클래스에 기본적인 알고리즘 흐름 구현, 자식 클래스에 처리로직 위임하는 디자인 패턴<br><br>

```java
public interface DiscountCondition {
    boolean isSatisfiedBy(Screening screening);
}
```

```java
public class PeriodCondition implements DiscountCondition{
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;

    public PeriodCondition(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
    public boolean isSatisfiedBy(Screening screening) {
        return screening.getStartTime().getDayOfWeek().equals(dayOfWeek) &&
                startTime.compareTo(screening.getStartTime().toLocalTime()) <= 0 &&
                endTime.compareTo(screening.getStartTime().toLocalTime()) >= 0;
    }
}
```
```java

```
