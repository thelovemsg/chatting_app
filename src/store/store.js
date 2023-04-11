import { configureStore } from '@reduxjs/toolkit';
import createSagaMiddleware from 'redux-saga';
import { userReducer } from '../reducers/user';
import rootSaga from '../saga/index';
import logger from '../Logger/MyLogger';

const saga = createSagaMiddleware();
const store = configureStore({
  reducer: {
    user: userReducer,
  },
  middleware: [logger, saga],
});

saga.run(rootSaga);

export default store;
