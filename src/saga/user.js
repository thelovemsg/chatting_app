import { all, call, delay, fork, put, takeLatest } from 'redux-saga/effects';
import {
  LOG_IN_FAILURE,
  LOG_IN_SUCCESS,
  LOG_OUT_FAILURE,
  LOG_OUT_SUCCESS,
  REGISTER_SUCCESS,
  REGISTER_FAILURE,
  LOG_IN_CHECK_SUCCESS,
  LOG_IN_CHECK_FAILURE,
} from 'reducers/user';
import {
  memberJoinApi,
  memberLoginApi,
  memberLogoutApi,
  memberLoginCheckApi,
} from '../api/member/member';

function* loginCheck() {
  try {
    console.log('loginCheck in saga/users');
    const result = yield call(memberLoginCheckApi);
    console.log('loginCheck result', result);
    /*
    TODO:
      여기서 어떤 결과가 왓는지에 따라 isLoginDone 처리를 바꿔야함. 
      전달 결과를 이제 저장해놓고, 권한 페이지가 걸려있으면 팅겨내야함.
    */
    yield put(LOG_IN_CHECK_SUCCESS());
  } catch (error) {
    yield put(
      LOG_IN_CHECK_FAILURE({ code: 'test', error: error.response.data.message })
    );
  }
}

function* logIn(action) {
  try {
    // console.log('logIn action in saga/user.js :: ', action.payload);
    const result = yield call(memberLoginApi, action.payload);
    console.log(result);
    yield delay(1000);
    // LOG_IN_SUCCESS 시 특별한 page 접속 권한 정보를 미리 저장해줘서 페이지 이동때마다
    // 이것을 체크
    yield put(LOG_IN_SUCCESS());
  } catch (err) {
    yield put(LOG_IN_FAILURE());
  }
}

function* logOut(action) {
  try {
    console.log('logout before...');
    const result = yield call(memberLogoutApi, action.payload);
    console.log('result :: ', result);
    console.log('logout after...');
    yield put(LOG_OUT_SUCCESS());
  } catch (err) {
    console.error('error occur!');
    yield put(LOG_OUT_FAILURE({ code: err.code, message: err.messsage }));
  }
}

function* userRegister(action) {
  try {
    // const result = yield call(logInAPI);
    console.log('userRegister :: ', action.payload);
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
