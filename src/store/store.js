import { configureStore, applyMiddleware } from "@reduxjs/toolkit";
import { userReducer } from "../reducers/user";
import createSagaMiddleware from "redux-saga";
import rootSaga from "../saga/index";
import logger from "../Logger/MyLogger";

const saga = createSagaMiddleware();
const store = configureStore({
  reducer: {
    user: userReducer,
  },
  middleware: [logger, saga],
});

saga.run(rootSaga);

export default store;
