import { configureStore } from '@reduxjs/toolkit';
import createSagaMiddleware from 'redux-saga';
import { chattingRoomReducer } from 'reducers/chatting/chattingReducer';
import { rootSaga } from '../saga/index';
import { loginReducer } from '../reducers/login';
import userReducer from '../reducers/user/userReducer';
import logger from '../Logger/MyLogger';

const saga = createSagaMiddleware();
const store = configureStore({
  reducer: {
    login: loginReducer,
    user: userReducer,
    chatting: chattingRoomReducer,
  },
  middleware: [logger, saga],
});

saga.run(rootSaga);

export default store;
