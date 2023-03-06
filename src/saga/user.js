import { all, delay, fork, put, takeLatest } from "redux-saga/effects";
import {
  LOG_IN_FAILURE,
  LOG_IN_SUCCESS,
  LOG_OUT_FAILURE,
  LOG_OUT_SUCCESS,
} from "../reducers/user";

function* logIn(action) {
  try {
    // const result = yield call(logInAPI);
    yield delay(1000);
    yield put(LOG_IN_SUCCESS());
  } catch (err) {
    yield put(LOG_IN_FAILURE());
  }
}

function* logOut(action) {
  try {
    // const result = yield call(logInAPI);
    yield delay(1000);
    yield put(LOG_OUT_SUCCESS());
  } catch (err) {
    yield put(LOG_OUT_FAILURE());
  }
}

function* watchLogIn() {
  console.log("watchLogIn");
  yield takeLatest("user/LOG_IN_REQUEST", logIn);
}

function* watchLogOut() {
  yield takeLatest("user/LOG_OUT_REQUEST", logOut);
}

export default function* userSaga() {
  yield all([fork(watchLogIn), fork(watchLogOut)]);
}
