# API 명세서

---

|기능|Method|URL|Request|Response|상태코드|
|:---:|:---:|:---:|:---:|:---:|:---:|
|환전 요청 수행|POST|/api/exchanges|-|{"message" : "요청에 성공하였습니다."}|상태코드 : 201|
|환전 요청 조회|GET|/api/exchanges/{userId}|{"userId" : 1}|조회 body|상태코드 : 200|
|환전 요청 취소|PATCH|/api/exchanges/{userId}/{currencyId}|{"userId" : 1, "currencyId" : 1}|{"message" : "해당 요청을 취소하였습니다."}|상태코드 : 200|
|각 사용자 별 총 환전 금액 조회|GET|/api/exchanges/total|-|총 금액 body|상태코드 : 200|

---

# ERD

---

![ERD 스크린샷](https://github.com/Mini9709/sample_user_currency/blob/main/ERD-user_currency.png)

---

## Lv 1


User entity와 Currency entity를 잇는 중간 테이블 UserCurrency를 구현한다.

UserCurrency가 갖는 속성은 다음과 같다.

id(환전 요청 id)

amountInKrw(환전 요청 금액)

amountAfterExchange(환전 후 금액)

status(환전 요청 상태)

user(join한 user entity)

currency(join한 currency entity)

---

## Lv 2


환전과 관련된 기능을 수행하는 ExchangeController를 구현한다.

해당 Controller는 환전 요청 수행, 환전 요청 조회, 환전 요청 취소, 각 사용자 별 총 환전 금액 조회를 기능으로 갖는다.

환전 요청 조회, 각 사용자 별 총 환전 금액 조회 기능은 총 결과값을 반환하며,

환전 요청 수행, 환전 요청 취소 기능은 간략한 결과와 메세지를 반환한다.

---

## Lv 3


해당 기능들을 수행하며 발생하는 예외들은 ExceptionController에서 처리한다.

몇몇의 예외들은 CustomException을 설정하여 처리한다.

ErrorCode를 Enum 클래스로 구현하여 활용한다.

ExceptionController는 발생한 예외를 ErrorCode, 상태코드, 에러 메세지로 반환한다.

---

## Lv 4


@PostConstruct를 활용하여 테스트환경 DataInitializer 클래스를 구현한다.

스프링이 구동된 후, 통화(Currency)의 환율(exchangeRate)이 양수가 아닐 경우, 로그를 기록한 뒤 해당 데이터를 삭제한다.

---

## Lv 5


각 사용자 별 총 환전 금액 조회 기능을 JPQL를 활용하여 구현한다.

status가 NORMAL인 경우에 한해 각 사용자의 환전 요청 수와 환전 요청 금액의 총합을 반환한다.

---

## Lv 6


Currency entity에 환율의 기준이 되는 baseCurrency 속성을 추가한다.

기능을 수행할 때, 달러가 아닌 설정된 baseCurrency을 기준으로 환전이 진행된다.
