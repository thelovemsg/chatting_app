import { createRandomProfile } from 'component/utility/FakeUser';
import {
  SET_USER_PROFILE_FAILURE,
  SET_USER_PROFILE_SUCCESS,
} from 'reducers/user/userProfile';
import { all, fork, put, takeLatest } from 'redux-saga/effects';

function* setProfileAction() {
  // -- NEED MORE WORK!!! --
  try {
    const user = createRandomProfile();
    console.log('user :: ', user);
    yield put(SET_USER_PROFILE_SUCCESS(user));
  } catch (error) {
    yield put(SET_USER_PROFILE_FAILURE());
  }
}

function* watchGetProfile() {
  yield takeLatest('user/profile/SET_USER_PROFILE_REQUEST', setProfileAction);
}

export default function* userProfileSaga() {
  yield all([fork(watchGetProfile)]);
}
