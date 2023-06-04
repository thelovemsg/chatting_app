import { all, call, delay, fork, put, takeLatest } from 'redux-saga/effects';
import { addMultipleProfileApi } from '../api/member/profile';
import {
  ADD_USER_MULTI_PROFILE_INFO_SUCCESS,
  ADD_USER_MULTI_PROFILE_INFO_FAILURE,
  GET_USER_MULTI_PROFILE_INFO_SUCCESS,
  GET_USER_MULTI_PROFILE_INFO_FAILURE,
  SET_USER_MULTI_PROFILE_STATUS_TRUE,
  SET_USER_MULTI_PROFILE_STATUS_FALSE,
} from '../reducers/user';

function* getMultiProfile(action) {
  try {
    const data = yield call(addMultipleProfileApi, action.payload);
    yield put(GET_USER_MULTI_PROFILE_INFO_SUCCESS(data));
  } catch (error) {
    yield put(
      GET_USER_MULTI_PROFILE_INFO_FAILURE({
        code: error.response.data?.code,
        error: error.response.data?.message,
      })
    );
  }
}

function* addMultiProfile(action) {
  try {
    console.log('something is wrong');
    // const data = yield call(addMultipleProfileApi, action.payload);
    yield delay(1000);
    yield put(ADD_USER_MULTI_PROFILE_INFO_SUCCESS(action.payload));
    yield put(SET_USER_MULTI_PROFILE_STATUS_TRUE());
  } catch (error) {
    yield put(
      ADD_USER_MULTI_PROFILE_INFO_FAILURE({
        code: error.response.data?.code,
        error: error.response.data?.message,
      })
    );
  }
}

function* setMultiProfileFalse() {
  yield put(SET_USER_MULTI_PROFILE_STATUS_FALSE());
}

function* watchAddMultiProfile() {
  console.log('addMultiProfile working');
  yield takeLatest('user/ADD_USER_MULTI_PROFILE_INFO_REQUEST', addMultiProfile);
}

function* watchGetMultiProfile() {
  yield takeLatest('user/GET_USER_MULTI_PROFILE_INFO_REQUEST', getMultiProfile);
}

function* watchMultiProfileUpdateState() {
  yield takeLatest(
    'user/SET_USER_MULTI_PROFILE_STATUS_FALSE_REQUEST',
    setMultiProfileFalse
  );
}

export default function* userSaga() {
  yield all([
    fork(watchAddMultiProfile),
    fork(watchGetMultiProfile),
    fork(watchMultiProfileUpdateState),
  ]);
}
