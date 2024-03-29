## Chapter 03. 역할, 책임, 협력

### 협력

>**영화 예매 시스템 돌아보기**

![20240325085651.png](image%2F20240325085651.png)
* 어떤 하나의 객체에 의해 통제되지 않고 다양한 객체들 사시에 균형있게 분배
* 객체들은 요청을 따라 자신에게 분배된 로직을 실행하면서 전체적인 기능 완성
* 다양한 객체들이 _영화 예매_ 라는 기능을 구현하기 위해 메시지를 주고 받으며 상호작용 ❗️
    * `협력` : 객체들이 애플리케이션의 기능을 구현하기 위해 수행하는 상호작용
    * `책임` : 객체가 협력에 참여하기 위해 수행하는 로직
    * `역할` : 객체들이 협력 안에서 수행하는 책임들이 모인 것
<br>
<br>
<br>

>**협력**

**협력이란 👀❓**   
- _어떤 객체가 다른 객체에게 무엇인가를 요청하는 것_
- 한 객체는 어떤 것이 필요할 때 다른 객체에게 전적으로 위임하거나 서로 협력   
  ➱ 두 객체가 상호작용을 통해 더 큰 책임 수행
- 객체는 고립된 존재가 아닌 시스템의 기능을 위해 다른 객체와 협력하는 사회적 존재
    - 협력은 이런 객체지향 세계에서 기능을 구현할 수 있는 유일한 방법
    - 한 객체가 다른 객체에게 도움을 요청할 때 협력 시작
- 객체 사이의 협력을 위해 사용할 수 있는 유일한 커뮤니케이션 수단 :: `메시지 전송 (message sending)`
    - 다른 객체의 내부에 직접 접근 할 수 없음 ❌ ➱ 메시지를 통하여 요청 전달
    - 메시지를 수신한 객체는 처리 방법을 직접 결정하여 메서드를 실행 후 응답   
      ➱ 자신의 일을 스스로 처리하는 자율적인 존재
      <br>
      <br>

Example 🔎 :: _Screening과 Movie의 협력_   

Screening 객체가 Movie에 calculateMovieFee 메시지를 전송하며 요금 계산을 요청   
➱ 기본 요금 & 할인 정책을 Movie 가 제일 잘 알고있기 때문.   

❓ _요금 계산을 Screening이 직접 수행한다면_ 🤔   
➱ Movie의 인스턴스 변수인 fee, discountPolicy에 직접 접근 :: Movie의 자율성 훼손 & _캡슐화 위반_   
➱ 정보와 행동이 Movie와 Screening으로 나뉘게 된다. :: Movie는 수동적 존재로 전락

∴ Movie가 자율적인 존재가 되려면 자신이 가지고있는 정보로 직접 요금 계산을 실행해야하기 때문에   
Screening이 Movie에게 요금 계산을 위임한 이유.   
<br>
<br>

>**협력이 설계를 위한 문맥을 결정한다**
   
애플리케이션 내에 어떤 객체가 필요한 이유는 협력에 참여하고 있기 때문.   
➱ 객체의 행동을 결정하는 것 :: 객체가 참여하고 있는 협력   
➱ 협력이 바뀌면 객체가 제공해야하는 행동도 변경   
➱ 협력은 객체가 필요한 이유, 수행하는 행동의 동기 제공   

❓ _Movie 객체는 어떤 행동을 수행할 수 있어야 할까?_ 🤔   
➱ 대부분의 메서드는 요금을 계산하는 행동과 관련   
➱ 영화를 예매하기 위한 협력에 참여하고 있고, Movie는 요금을 계산하는 책임을 갖고있기 때문   

```java
public class Movie {
  private Money fee;
  private DiscountPolicy discountPolicy;
  
  public Money calculateMovieFee(Screening screening) {
      return fee.minus(discountPolicy.calculateDiscountAmount(screening));
  }
}
```

* 객체의 상태를 결정하는 것 = 행동
* 객체는 자신의 상태를 스스로 결정하고 관리하는 자율적인 존재
  * 객체가 수행하는 행동에 필요한 상태도 함께 가지고 있어야함
* Movie가 fee, discountPolicy라는 인스턴스 변수를 상태의 일부로 포함
  * 요금 계산 수행 시 위 정보들 필요   
   
상태는 객체가 행동하는 데 필요한 정보에 의해 결정, 행동은 협력 안에서 객체가 처리할 메시지로 결정.   
➱ 객체가 참여하는 협력이 객체를 구성하는 행동, 상태 모두 결정   

∴  협력은 객체를 설계하는 데 필요한 `문맥(context)` 제공
<br>
<br>
---   

### 책임

>**책임이란 무엇인가**

**책임이란 👀❓**   
* _협력에 참여하기 위해 객체가 수행하는 행동_
* 객체가 유지해야 하는 정보, 수행할 수 있는 행동에 대해 서술한 문장
* `하는 것 (doing)`
    * 객체를 생성하거나 계산을 수행하는 등 스스로 하는 것
    * 다른 객체의 행동을 시작 시키는 것
    * 다른 객체의 활동을 제어하고 조절하는 것
* `아는 것 (knowing)`
    * 사적인 정보에 관해 아는 것
    * 관련된 객체에 관해 아는 것
    * 자신이 유도하거나 계산할 수 있는 것에 관해 아는 것   
  


Example 🔎
```java
public class Screenning {
    private Movie movie; // 상영할 영화  
    
    ...
    
    public Reservation reserve(Customer customer, int audienceCount){
        return Reservation(customer, this, calculateFee(audienceCount), audienceCount);
    }
    
}
```

 ```java
public class Movie {
    private Money fee;
    private DiscountPolicy discountPolicy;
    
    ...
    
    public Money calculateMovieFee(Screening screening) {
        return fee.minus(discountPolicy.calculateDiscountAmount(screening));
    }
```
협력 안에서 객체에게 할당한 책임이 외부의 인터페이스와 내부의 속성을 결정한다.   

* `아는 것`과 `하는 것`의 연관
    * 자신이 맡은 책임을 수행하는 데 필요한 정보를 알고 있을 책임
    * 자신이 할 수 없는 작업을 도와줄 객체를 알고 있을 책임
    * 어떤 책임을 수행하기 위해 필요한 정보도 함께 알아야 할 책임
  
_적절한 협력이 적절한 책임을 제공하고, 적절한 책임을 적절한 객체에 할당해야 단순,유연한 설계 창조_   
<br>

>**책임 할당**   

`INFORMATION EXPERT(정보 전문가) 패턴`   
➱ 책임을 수행하는 데 필요한 정보를 가장 잘 알고있는 전문가에게 책임을 할당하는 것   
➱ 객체를 자율적인 존재로 만드는 가장 기본적인 방법   

**Example**  🔎 :: _영화 예매 시스템_

Step01 .   
![20240326115016.png](image%2F20240326115016.png)   
시스템의 책임 ➱ 영화를 예매하는 것   
_예매하라_ 는 메시지로 협력 시작 !   
<br>
Step02.   
![20240326170403.png](image%2F20240326170403.png)   
메시지를 선택했으면 이 메시지를 처리할 적절한 객체를 선택해야한다.   
➱ 영화를 예매하기 위해서 상영 시간과 기본 요금이 필요한데, 이 정보를 소유하고 있거나,   
소유자를 잘 아는 **_Screenig_** 에게 할당   
<br>
Step03.   
![20240326170711.png](image%2F20240326170711.png)   
영화 예매를 하려면 가격이 필요한데, Screening은 가격 계산 하기에는 정보가 충분하지 않다.   
➱ 다른 객체에게 도움을 요청해야 한다는 의미 :: 새로운 메시지 _가격을 계산하라_   
<br>
Step04.   
![20240326170711.png](image%2F20240326170711.png)   
가격 계산을 위해 필요한 것은, 기본 가격과 할인 정책   
➱ 위 정보를 잘 알고있는 **_Movie_** 에게 할당   
<br>
🤔❓   
하지만 할인 요금을 계산하기에는 Movie는 적절한 객체가 아니다.   
➱ 다른 객체에게 도움을 요청해야 하는 상황 :: 새로운 메시지 필요 _할인 요금을 계산하라_   
<br>
이처럼 객체지향 설계는 협력에 필요한 메시지를 찾고, 적절한 객체에게 할당하는 반복적인 과정이다.  
<br>

>**책임 주도 설계**   

**_`책임 주도 설계 (Responsibility-Driven Design, RDD)`_**   
➱ 책임을 찾고 책임을 수행할 적절한 객체를 찾아 책임을 할당하는 방식으로 협력을 설계하는 방법   

**_책임 주도 설계 과정_**   
* 시스템이 사용자에게 제공해야 하는 기능인 시스템 책임 파악
* 시스템 책임을 더 작은 책임으로 분할
* 분할된 책임을 수행할 수 있는 적절한 객체 or 역할을 찾아 책임 할당
* 객체가 책임을 수행하는 도중 다른 객체의 도움이 필요한 경우, 이를 책임질 적절한 객체 or 역할 찾기
* 해당 객체 or 역할에게 책임을 할당함으로써 두 객체 협력   

책임 주도 설계는 자연스럽게 객체의 구현이 아닌 책임에 집중할 수 있게 함   
➱ 유연하고 견고한 객체지향 시스템을 위한 가장 중요한 것이 책임이기 때문.   

**_책임 할당 시 고려해야 하는 것_**  
1. 메시지가 객체를 결정
2. 행동이 상태를 결정   
<br>

>**메시지가 객체를 결정한다**   

**_메시지가 객체를 선택하게 해야하는 이유 ✨_**   
1. 객체가 **_`최소한의 인터페이스(minimal interface)`_** 를 가질 수 있게 된다.   
   ➱ 필요한 메시지가 생기기 전까지 객체의 퍼블릭 인터페이스에 어떤 것도 추가하지 않는다.   
   ➱ 꼭 필요한 크기의 퍼블릭 인터페이스를 가질 수 있다.
2. 객체는 **_`추상적인 인터페이스(abstract interface)`_**를 가질 수 있게 된다.   
   ➱ 객체의 인터페이스는 무엇을(what) 하는지는 표현할 수 있지만, 어떻게(how) 수행하는지는 노출하면 안 된다.    
   ➱ 메시지 = 외부의 객체가 요청하는 것
   ➱ 메시지를 먼저 식별할 경우 무엇을 수행해야 하는지에 초점을 맞추는 인터페이스를 얻을 수 있다.   



Example 🔎 :: _영화 예매 시스템의 설계_    
1. 시스템 책임을 _예매하라_ 로 결정
2. 메시지를 수신할 객체로 Screening 선택
3. Screening이 _가격을 계산하라_ 라는 메시지 필요
4. 메시지를 수신할 객체로 Movie 선택   

➱ 결과적으로 협력을 구성하는 객체들의 인터페이스들은 추상적인 동시에 최소한의 크기 유지   
<br>

>**행동이 상태를 결정한다**   

**객체의 행동 👀❓**     
* 객체가 협력에 참여할 수 있는 유일한 방법
* 객체가 협력에 적합한지를 결정하는 것

객체에 필요한 상태가 무엇인지 결정 ➱ 상태에 필요한 행동 결정 :: **캡슐화 저해**   

**_`데이터 주도 설계 (Data-Driven Design, RDD)`_**   
➱ 객체의 내부 구현에 초점을 맞춘 설계 방법   

**협력이 객체의 행동을 결정하고, 행동이 상태를 결정한다. 행동이 바로 객체의 책임이다.**



!!!!!!
<br>

>**역할과 추상화**   

***추상화를 이용한 설계의 장점***
* 중요한 정책을 상위 수준에서 단순화 할 수 있다.
* 설계가 좀 더 유연해진다.   

***추상화의 장점***
1. **세부 사항에 억눌리지 않고 상위 수준의 정책을 쉽고 간단하게 표현 가능**   
   ➱ 적절하게 사용하면 불필요한 세부 사항을 생략하고 핵심 개념 강조 가능   

    ![20240327104239.png](image%2F20240327104239.png)   
    예매 요금을 계산하는데 필요한 할인 정책, 할인 조건의 구조   
    ➱ 금액 할인 정책, 비율 할인 정책을 순번 조건, 기간 조건과 조합해 다양한 방식의 요금 계산 규칙 설정 가능   
    ➱ 영화 예매 시스템에 존재하는 할인 정책, 할인 조건의 종류 파악에는 적합   
    ➱ BUT 할인 정책과 할인 조건의 종류라는 세부적인 상항으로 인해 객체들 사이의 핵심 관계과 관련된 것을 파악하는 것을 방해   
    <br>
    ![20240327103355.png](image%2F20240327103355.png)    
    협력이라는 관점에서는 세부적인 사항을 무시하고 추상화에 집중하는 것이 유용   
    ➱ 요금 계산에서의 세부 사항 :: 할인 정책 & 할인 조건   
    ➱ 세부 사항을 무시하고 DiscountPolicy와 DiscountCondition만 바라보면 상황을 추상화 가능    
   <br>
    ![20240327104732.png](image%2F20240327104732.png)   
    구체적인 할인 정책의 종류를 추상화한 DiscountPolicy, 상세한 할인 조건의 종류를 추상화한 DiscountCondition을   
    이용하여 협력을 표현하면 객체 사이의 핵심적인 상호작용이 또렷하게 들어날 수 있다.   
    ➱ 요금 계산을 위해 DiscountPolicy, DiscountCondition의 인스턴스를 조합 필요   
    ➱ Movie가 DiscountPolicy에게, DiscountPolicy가 DiscountCondition에게 메시지를 선송하여 협력  
    
    위 두 사실이 명확해짐을 알 수 있고, 구체적인 객체로 대체 가능한 DiscountPolicy, DiscountCondition이 역할이다.   
<br>
2. **설계를 유연하게 만들 수 있다.**
* 협력 안에서 동일한 책임을 수행하는 객체들은 동일한 역할을 수행하기 때문에 대체 가능
* 역할은 다양한 환경에서 다양한 객체들을 수용할 수 있게 해주므로 협력을 유연하게 만듦
* 협력 안에서 역할이라는 추상화를 이용하면 기존 코드를 수정하지 않고도 새로운 행동 추가 가능
* 프레임워크 or 디자인 패턴과 같은 재사용 가능한 코드나 설계 아이디어를 구성하는 핵심 요소 :: 역할   

<br>

>**배우와 배역**   

연극 안에서 배역을 연기하는 배우   
* 협력 안에서 역할을 수행하는 객체라는 관점이 가진 입체적 측면들을 담아낸다.   
* 협력 = 연극 / 코드 = 극본   
* 배우는 배역이라는 특정 역할 연기 :: 객체는 협력 안에서 특정 역할 수행   
* 배우는 연극 종료 시 원래 자신의 모습으로 복귀 :: 객체는 협력 종료 시 역할을 잊고 원래의 객체로 복귀   
* 역할은 객체가 협력에 참여하는 동안에만 존재하는 일시적 개념   
* 역할은 모양, 구조에 의해 정의될 수 ❌ :: 시스템의 문맥 안에서 무엇을 하는지에 의해 정의될 수 있다.
* 역할을 수행하는 하나 이상의 객체들 존재 가능 :: 동일한 역할을 수행하는 객체들은 서로 대체 가능
* 객체는 다양한 역할 가질 수 ⭕️
* 역할은 특정 객체의 종류를 캡슐화 ➱ 대체 가능한 객체들은 다형적