import { all, call, delay, fork, put, takeLatest } from 'redux-saga/effects';
import { addMultipleProfileApi } from '../api/member/profile';
import {
  ADD_USER_MULTI_PROFILE_INFO_SUCCESS,
  ADD_USER_MULTI_PROFILE_INFO_FAILURE,
  GET_USER_MULTI_PROFILE_INFO_SUCCESS,
  GET_USER_MULTI_PROFILE_INFO_FAILURE,
  ADD_USER_MULTI_PROFILE_INFO_REQUEST,
  GET_USER_MULTI_PROFILE_INFO_REQUEST,
  RESET_ADD_USER_MULTI_PROFILE_INFO_SUCCESS,
} from '../reducers/user';

function* getMultiProfile(action) {
  try {
    const data = yield call(addMultipleProfileApi, action.payload);
    console.log('data in getMultiProfile saga :: ', data);
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
  } catch (error) {
    yield put(
      ADD_USER_MULTI_PROFILE_INFO_FAILURE({
        code: error.response.data?.code,
        error: error.response.data?.message,
      })
    );
  }
}
function* watchAddMultiProfile() {
  yield takeLatest(ADD_USER_MULTI_PROFILE_INFO_REQUEST, addMultiProfile);
}

function* watchGetMultiProfile() {
  yield takeLatest(GET_USER_MULTI_PROFILE_INFO_REQUEST, getMultiProfile);
}

function* watchResetMultiProfileAddSuccess() {
  yield takeLatest(RESET_ADD_USER_MULTI_PROFILE_INFO_SUCCESS);
}

export default function* userSaga() {
  yield all([
    fork(watchAddMultiProfile),
    fork(watchGetMultiProfile),
    fork(watchResetMultiProfileAddSuccess),
  ]);
}
