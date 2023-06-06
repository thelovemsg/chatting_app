import { all, call, fork, put, takeLatest } from 'redux-saga/effects';
import { addMultipleProfileApi } from '../../api/member/profile';
import {
  ADD_USER_MULTI_PROFILE_INFO_SUCCESS,
  ADD_USER_MULTI_PROFILE_INFO_FAILURE,
  GET_USER_MULTI_PROFILE_INFO_SUCCESS,
  GET_USER_MULTI_PROFILE_INFO_FAILURE,
  SET_USER_MULTI_PROFILE_STATUS_TRUE,
  SET_USER_MULTI_PROFILE_STATUS_FALSE,
  REMOVE_USER_MULTI_PROFILE_INFO_SUCCESS,
} from '../../reducers/user/userMultiProfile';

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
    // const data = yield call(addMultipleProfileApi, action.payload);
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

function* removeMutltiProfile(action) {
  yield put(REMOVE_USER_MULTI_PROFILE_INFO_SUCCESS(action.payload));
}

function* watchAddMultiProfile() {
  yield takeLatest(
    'user/multiProfile/ADD_USER_MULTI_PROFILE_INFO_REQUEST',
    addMultiProfile
  );
}

function* watchGetMultiProfile() {
  yield takeLatest(
    'user/multiProfile/GET_USER_MULTI_PROFILE_INFO_REQUEST',
    getMultiProfile
  );
}

function* watchMultiProfileUpdateState() {
  yield takeLatest(
    'user/multiProfile/SET_USER_MULTI_PROFILE_STATUS_FALSE_REQUEST',
    setMultiProfileFalse
  );
}

function* watchMultiProfileRemove() {
  yield takeLatest(
    'user/multiProfile/REMOVE_USER_MULTI_PROFILE_INFO_REQUEST',
    removeMutltiProfile
  );
}

export default function* userMultiProfileSaga() {
  yield all([
    fork(watchAddMultiProfile),
    fork(watchGetMultiProfile),
    fork(watchMultiProfileUpdateState),
    fork(watchMultiProfileRemove),
  ]);
}
