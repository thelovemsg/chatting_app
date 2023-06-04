import { configureStore } from '@reduxjs/toolkit';
import createSagaMiddleware from 'redux-saga';
import { rootSaga } from '../saga/index';
import { loginReducer } from '../reducers/login';
import { userReducer } from '../reducers/user';
import logger from '../Logger/MyLogger';

const saga = createSagaMiddleware();
const store = configureStore({
  reducer: {
    login: loginReducer,
    user: userReducer,
  },
  middleware: [logger, saga],
});

saga.run(rootSaga);

export default store;
