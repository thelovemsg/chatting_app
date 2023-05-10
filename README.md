# chatting_app - 채팅 앱 제작기

### title 변경 이유
- spring web socekt은 in-memory message broker 인데, 한계점이 많다.
- 허나, 다른 메시지 브로커에 대한 지식이 부족하기 때문에 초창기에는 spring web socekt을 사용할 것이며, 추후에 다른 메시지 브로커를 적용할 것이다.
 2023-05-09
 
### 속도가 안나와 gpt에게 물어본 대화 내용

#### Me:
```I think programmer also need to spend tons of time to know what they should make.```

#### ChatGPT:
```Absolutely! Understanding the requirements and designing the system is a significant part of a software developer's job. The process of gathering requirements, understanding the user's needs, and figuring out how best to solve the problem can often take as much time, if not more, than the actual coding itself.

This process is crucial because it helps ensure that the system you're building will meet the user's needs and expectations. If you skip this step or rush through it, you risk building a system that doesn't do what it's supposed to do or that isn't easy for users to use.

In addition, this process often involves a lot of communication and collaboration with others, including users, stakeholders, and other developers. This can involve meetings, discussions, and writing and reviewing documents.

Once the requirements are understood, a developer can then start designing and implementing the system. This involves making decisions about what technologies to use, how to structure the code, and how to handle different scenarios.

So yes, a lot of a developer's time is spent on tasks other than just writing code. But all these tasks are essential for building a successful software system.
```

## 사용중인 react 기술

## ERD 설계
https://www.notion.so/2023-04-15-98fc69e81e464f3682e01adcbfe0fa27

### 1. 다국어 처리 기능 - react-i18next
  참고 사이트 
  https://locize.com/blog/react-i18next/#why-i18next
  
### 2. 리액트 라우터 - react-router-dom
  
### 3. 리덕스 사가 : redux-saga 
  비동기 작업을 처리하기 위한 미들웨어. redux-thunk보다 많은 기능 지원
  redux => useDispatch & dispatch로 action 처리
        => useSelector로 사용하길 원하는 data 선택

### 4. immer 
  더 쉬운 불변성 관리 가능
 
### 5. react bootstrap 적용

### 6. 리덕스 툴킷 사용
   npm i @reduxjs/toolkit react-redux

### 7. styled-component 사용

명령어 모음
 npm i react-datepicker --save

## spring 이슈사항 정리
#### 2023-03-16 추가
##### spring-data-jpa
##### spring-web-socket
##### query-dsl

  1. jpa를 사용하는 방법을 현재 까먹음 => jpashop2에서 한 작업들 참고로 Member(일), Address(다) 구현하기 
  2. passwordConverter 기능을 추가해서 실제로 db에 잘 저장이 되는지 확인하기
  3. 서버 시작시 DataInit 작업 해주기
  4. 신경써야 할 작업이 있는지 더 확인해야함

### react 이슈사항 정리
#### 2023-03-13
로그인, 로그아웃 시 url 관리 => 가장 최상위에서 해줘야 복잡하지 않고 관리가 잘 된다. 
=> useEffect사용 시에는 state값의 변화를 통해 이벤트를 줄 수 가 있는데... 이것의 문제는 문맥에 맞지 않는 이벤트를 중구난방으로 난사하면 어디서 수정해야하는지 절대 못찾음

#### 2023-03-15
다국어 처리 적용 완료(영어랑 한국어클릭시 변환 되도록 다 등록해야함 ㅠㅠ...)
bootstrap carousel적용. (디자인 이상하다는것은 반박 불가)

git repository 연결 테스트...

## 새로이 학습해야 할 것 / 적용 목표
#### 필수사항
##### 1. frontend 클린 코드 작성법 숙지
참고 영상
https://www.youtube.com/watch?v=edWbHp_k_9Y&ab_channel=%ED%86%A0%EC%8A%A4

사실 프론트엔드에서의 클린 코드를 신경쓰지 않았음. 학습 필수!

#### 2023-03-20 오전
##### 1. 암호화에 대한 이해와 방법 및 적용 필요(java)
##### 2. 공통 에러 처리를 위한 Custom Exception 처리 로직 (java)

#### 2023-03-20 오후
##### 1. 다국어 처리를 react 에서 관리하긴 하지만 spring boot 에서도 해주고 싶음.
이유는 메시지를 그대로 alert로 띄워주고 싶음.(다국어 처리된 상태로). 이에 대한 고민이 필요함.

#### 2023-04-13 00:00 정각
##### 현재 js => jsx 파일로 변경
이유 
1. jsx 사용시 React 컴포넌트인것을 명확히 파악이 가능.
2. jsx가 코드 지원(하이라이팅)이 더 잘됨. js도 물론 요즘은 잘 됌
3. 필자 생각에는 util 기능 내용은 js에 만들어서 저장하는게 더욱 직관적임.

현재 chatting 화면의 디자인을 반영하고 있으며, @faker-js/faker 를 이용해서 임의의 화면을 구성해서 배치를 파악중
또한, chatting 화면을 위해 여러 component로 나누어 생성. 

=> 디자인도 신경을 써서 하다보니 작업량이 배로 늘어남.
