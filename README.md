# Getting Started with Create React App

This project was bootstrapped with [Create React App](https://github.com/facebook/create-react-app).

## Available Scripts

In the project directory, you can run:

### `npm start`

Runs the app in the development mode.\
Open [http://localhost:3000](http://localhost:3000) to view it in your browser.

The page will reload when you make changes.\
You may also see any lint errors in the console.

### `npm test`

Launches the test runner in the interactive watch mode.\
See the section about [running tests](https://facebook.github.io/create-react-app/docs/running-tests) for more information.

### `npm run build`

Builds the app for production to the `build` folder.\
It correctly bundles React in production mode and optimizes the build for the best performance.

The build is minified and the filenames include the hashes.\
Your app is ready to be deployed!

See the section about [deployment](https://facebook.github.io/create-react-app/docs/deployment) for more information.

### `npm run eject`

**Note: this is a one-way operation. Once you `eject`, you can't go back!**

If you aren't satisfied with the build tool and configuration choices, you can `eject` at any time. This command will remove the single build dependency from your project.

Instead, it will copy all the configuration files and the transitive dependencies (webpack, Babel, ESLint, etc) right into your project so you have full control over them. All of the commands except `eject` will still work, but they will point to the copied scripts so you can tweak them. At this point you're on your own.

You don't have to ever use `eject`. The curated feature set is suitable for small and middle deployments, and you shouldn't feel obligated to use this feature. However we understand that this tool wouldn't be useful if you couldn't customize it when you are ready for it.

## Learn More

You can learn more in the [Create React App documentation](https://facebook.github.io/create-react-app/docs/getting-started).

To learn React, check out the [React documentation](https://reactjs.org/).

### Code Splitting

This section has moved here: [https://facebook.github.io/create-react-app/docs/code-splitting](https://facebook.github.io/create-react-app/docs/code-splitting)

### Analyzing the Bundle Size

This section has moved here: [https://facebook.github.io/create-react-app/docs/analyzing-the-bundle-size](https://facebook.github.io/create-react-app/docs/analyzing-the-bundle-size)

### Making a Progressive Web App

This section has moved here: [https://facebook.github.io/create-react-app/docs/making-a-progressive-web-app](https://facebook.github.io/create-react-app/docs/making-a-progressive-web-app)

### Advanced Configuration

This section has moved here: [https://facebook.github.io/create-react-app/docs/advanced-configuration](https://facebook.github.io/create-react-app/docs/advanced-configuration)

### Deployment

This section has moved here: [https://facebook.github.io/create-react-app/docs/deployment](https://facebook.github.io/create-react-app/docs/deployment)

### `npm run build` fails to minify

This section has moved here: [https://facebook.github.io/create-react-app/docs/troubleshooting#npm-run-build-fails-to-minify](https://facebook.github.io/create-react-app/docs/troubleshooting#npm-run-build-fails-to-minify)
# chatting_app

## 사용중인 react 기술

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

### spring 이슈사항 정리
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

#### 2023-03-20  
##### 1. 암호화에 대한 이해와 방법 및 적용(java)
