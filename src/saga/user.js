import { all, call, delay, fork, put, takeLatest } from "redux-saga/effects";
import { memberJoinApi } from "../api/member/member";
import {
  LOG_IN_FAILURE,
  LOG_IN_SUCCESS,
  LOG_OUT_FAILURE,
  LOG_OUT_SUCCESS,
  REGISTER_SUCCESS,
  REGISTER_FAILURE,
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
    // throw new Error("tesitng");
    yield put(LOG_OUT_SUCCESS());
  } catch (err) {
    yield put(LOG_OUT_FAILURE({ code: err.code, message: err.messsage }));
  }
}

function* userRegister(action) {
  console.log("user register test... :: ", action);
  try {
    // const result = yield call(logInAPI);
    const data = yield call(memberJoinApi, action.payload);
    console.log("result data :: ", data);
    yield put(REGISTER_SUCCESS());
  } catch (err) {
    console.log(err);
    yield put(REGISTER_FAILURE({ code: err.code, message: err.messsage }));
  }
}

function* watchLogIn() {
  yield takeLatest("user/LOG_IN_REQUEST", logIn);
}

function* watchLogOut() {
  yield takeLatest("user/LOG_OUT_REQUEST", logOut);
}

function* watchRegister() {
  yield takeLatest("user/REGISTER_REQUEST", userRegister);
}

export default function* userSaga() {
  yield all([fork(watchLogIn), fork(watchLogOut), fork(watchRegister)]);
}
