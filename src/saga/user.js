import { all, call, fork, put, takeLatest } from 'redux-saga/effects';
import {
  LOG_IN_FAILURE,
  LOG_IN_SUCCESS,
  LOG_OUT_FAILURE,
  LOG_OUT_SUCCESS,
  REGISTER_SUCCESS,
  REGISTER_FAILURE,
  LOG_IN_CHECK_SUCCESS,
  LOG_IN_CHECK_FAILURE,
} from 'reducers/login';
import {
  memberJoinApi,
  memberLoginApi,
  memberLogoutApi,
  memberLoginCheckApi,
} from '../api/member/member';

function* loginCheck() {
  try {
    yield call(memberLoginCheckApi);
    yield put(LOG_IN_CHECK_SUCCESS());
  } catch (error) {
    yield put(
      LOG_IN_CHECK_FAILURE({
        code: error.response.data?.code,
        error: error.response.data?.message,
      })
    );
  }
}

function* logIn(action) {
  try {
    const result = yield call(memberLoginApi, action.payload);
    console.log('result login ::', result);
    if (result?.data?.status === 'UNAUTHORIZED') {
      throw new Error('error');
    } else {
      yield put(LOG_IN_SUCCESS());
    }
  } catch (err) {
    console.log('error!!!!!');
    yield put(
      LOG_IN_FAILURE({
        code: '101',
        message: '로그인중 에러 발생!',
      })
    );
  }
}

function* logOut(action) {
  try {
    yield call(memberLogoutApi, action.payload);
    yield put(LOG_OUT_SUCCESS());
  } catch (err) {
    yield put(LOG_OUT_FAILURE({ code: err.code, message: err.messsage }));
  }
}

function* userRegister(action) {
  try {
    // const result = yield call(logInAPI);
    const data = yield call(memberJoinApi, action.payload);
    if (data.status === 'BAD_REQUEST') {
      throw new Error(data.message);
    }
    yield put(REGISTER_SUCCESS());
  } catch (err) {
    alert(err.message);
    yield put(REGISTER_FAILURE(err.message));
  }
}

function* watchLoginCheck() {
  yield takeLatest('user/LOG_IN_CHECK_REQUEST', loginCheck);
}

function* watchLogIn() {
  yield takeLatest('user/LOG_IN_REQUEST', logIn);
}

function* watchLogOut() {
  yield takeLatest('user/LOG_OUT_REQUEST', logOut);
}

function* watchRegister() {
  yield takeLatest('user/REGISTER_REQUEST', userRegister);
}

export default function* userSaga() {
  yield all([
    fork(watchLoginCheck),
    fork(watchLogIn),
    fork(watchLogOut),
    fork(watchRegister),
  ]);
}
