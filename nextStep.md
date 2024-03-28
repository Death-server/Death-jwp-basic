# 7장

7.1  회원 데이터 DB 저장 실습<br>
7.2 DAO 리팩토링 실습<br>
7.3 동영상을 활용한 DAO 리팩토링 실습<br>
7.4 DAO 리팩토링 및 설명<br>
7.5 추가 학습자료<br>
## expret one-on-one J2EE 설계와 개발 책 출처
### 컴파일타임 Exception과 런타임 Exception 가이드라인

	- API를 사용하는 모든 곳에서 이 예외를 처리해야 하는가? 예외가 반드시 메소드에 대한 반환 값이 되어야 하는가?
		-> 컴파일 타임 Exception
	
	- API를 사용하는 소수가 이 예외를 처리해야 하는가?
		-> 런타임 Exception
		-> 이때 API를 사용하는 모든 코드가 Exception을 catch하도록 강제하지 않는 것이 좋다.
	
	- 무엇인가 큰 문제가 발생했는가? 이 문제를 복구할 방법이 없는가?
		-> 런타임 Exception
	
	- 아직 불명확한가?
		-> 런타임 Exception
		-> Exception에 대해 문서화하고 API를 사용하는 곳에 Exception 처리를 결정하도록 하라.

---

# 8장

8.1 질문/답변 게시판 구현<br>
8.2 AJAX 활용해 답변 추가, 삭제 실습<br>
8.3 MVC 프레임워크 요구사항 2단계<br>
8.4 MV 프레임워크 구현 2단계<br>
8.5 추가 학습 자료<br>

## 자바스크립트의 this?

- 해당 함수를 누가 호출하느냐에 따라 this가 변경됨.
- 자바스크립트의 this에 대해 더 깊이 있게 학습하려면 Scope을 다루는 부분을 학습. 
- apply(), call(), bind() 함수에 대해서도 알아야 함.

## ObjectMapper란?

- JSON 형식을 사용할 때, 응답들을 직렬화하고 요청들을 역직렬화 할 때 사용하는 기술.

- 직렬화(Serialize)
  데이터를 전송하거나 저장할 때 바이트 문자열이어야 하기 때문에 객체들을 문자열로 바꾸어 준다.
  Object -> String 문자열

- 역직렬화(Deserialize)
  데이터 모든 전송된 이후, 수신측에서 다시 문자열을 기존의 객체로 회복시켜주는 것
- String 문자열 -> Object

- 스프링 부트의 경우 spring-boot-starter-web에 기본적으로 Jackson 라이브러리가 있어서 자동으로 처리해줌.

- mapper.WriteValueAsString(car); -> car 객체의 내용을 JSON으로 변환


## 경우의 수가 하나가 아닌 2개 이상이 발생하는 경우

- if/else를 통해 해결할 수도 있지만 인터페이스를 통해 문제를 해결하는 것이 좀 더 확장가능하고 깔끔한 코드.


## 제네릭과 와일드카드 타입
- 제네릭의 개념을 보다 유연하게 활용할 수 있도록 도입된 것. -> 제네릭의 불공변때문

- 공변 (covariant) : A가 B의 하위 타입일 때, T &lt;A&gt;가 T&lt;B&gt;의 하위 타입이면 T는 공변<br>
- 불공변 (invariant) : A가 B의 하위 타입일 때, T&lt;A&gt;가 T&lt;B&gt;의 하위 타입이 아니면 T는 불공변
- ex) 배열은 공변, 제네릭은 불공변
```java
//배열은 공변이라 다음 코드가 통과
class Array {
    @Test
    void genericTest() {
        Integer[] integers = new Integer[]{1, 2, 3};
        printArray(integers);
    }

    void printArray(Object[] arr) {
        for (Object e : arr) {
            System.out.println(e);
        }
    }
}
```

```java
//Generic은 불공변이라 컴파일 에러 발생
class Generic {
    @Test
    void genericTest() {
        List<Integer> list = Arrays.asList(1, 2, 3);
        printCollection(list);   // 컴파일 에러 발생
    }


    void printCollection(Collection<Object> c) {
        for (Object e : c) {
            System.out.println(e);
        }
    }
}
```

- 와일드 카드로 선언된 타입은 Any Type이 아니라 Unknow Type이다!
```
Collection<?> c = new ArrayList<String>();
c.add(new Object()); // 컴파일 에러
```

- 한정적 와일드 카드
  - 특정 타입을 기준으로 상한 범위와 하한 범위를 지정하는 와일드 카드
  - 상한 경계 와일드 카드(Collection<? extends MyParent> c)
    - extends를 활용하여 와일드 카드 타입의 최상위 타입을 정의
  - 하한 경계 와일드 카드(Collections<? super MyParent> c)
    - super를 사용해 와일드카드의 최하위 타입을 정의

-  Producer-Extends, Consumer-Super
  - 와일드카드 타입의 객체를 생성 및 만들면 extends를, 갖고 있는 객체를 컬렉션에 사용 또는 소비하면 super를 사용

```
void printCollection (Collection<? extends MyParent> c) {
    for (MyParent e : c) {
        System.out.println(e);
    }
}

void addElement(Collection<? super MyParent> c) {
    c.add(new MyParent());
}
```
