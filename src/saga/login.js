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
// import { SET_USER_PROFILE } from 'reducers/user';
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
    if (result?.data?.status === 'UNAUTHORIZED') {
      throw new Error('error');
    } else {
      // yield put(SET_USER_PROFILE())
      yield put(LOG_IN_SUCCESS());
      // TODO : call SET_USER_ID action
    }
  } catch (err) {
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
  /**
   * 성공 아니면 전부 다 팅겨내야하는데? 분기문 나중에 다시 확인해야할듯
   */
  try {
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
  yield takeLatest('login/LOG_IN_CHECK_REQUEST', loginCheck);
}

function* watchLogIn() {
  yield takeLatest('login/LOG_IN_REQUEST', logIn);
}

function* watchLogOut() {
  yield takeLatest('login/LOG_OUT_REQUEST', logOut);
}

function* watchRegister() {
  yield takeLatest('login/REGISTER_REQUEST', userRegister);
}

export default function* loginSaga() {
  yield all([
    fork(watchLoginCheck),
    fork(watchLogIn),
    fork(watchLogOut),
    fork(watchRegister),
  ]);
}
