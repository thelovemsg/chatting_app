import { all } from 'axios';
import { SET_USER_PROFILE_REQUEST } from 'reducers/user/userProfile';
import { fork, put, takeLatest } from 'redux-saga/effects';

function* setProfileAction() {
  try {
    console.log('getProfileAction test....');
    yield put(SET_USER_PROFILE_REQUEST());
  } catch (error) {
    console.log('error occur! ::', error);
  }
}

function* watchGetProfile() {
  yield takeLatest('user/profile/SET_USER_PROFILE_REQUEST', setProfileAction);
}

export default function* userProfileSaga() {
  yield all([fork(watchGetProfile)]);
}
