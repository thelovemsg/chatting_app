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
  참고 사이트 : https://ddeck.tistory.com/40
  
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

## 사용중인 spring 기술
??

### 이슈사항 정리
#### 2023-03-13
로그인, 로그아웃 시 url 관리 => 가장 최상위에서 해줘야 복잡하지 않고 관리가 잘 된다. 
=> useEffect사용 시에는 state값의 변화를 통해 이벤트를 줄 수 가 있는데... 이것의 문제는 문맥에 맞지 않는 이벤트를 중구난방으로 난사하면 어디서 수정해야하는지 절대 못찾음
