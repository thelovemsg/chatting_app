import { createRandomUser } from 'component/utility/FakeUser';
import {
  FLIP_USER_NOTI_STATUS_SUCCESS,
  FLIP_USER_NOTI_STATUS_FAILURE,
  GET_TARGET_USER_PROFILE_IMAGE_LIST_SUCCESS,
  GET_TARGET_USER_PROFILE_IMAGE_LIST_FAILURE,
} from 'reducers/user/userInfoSetting';
import { all, fork, put, takeLatest } from 'redux-saga/effects';

function* getUserProfileImageListAction(action) {
  try {
    const fakeUsers = Array.from({ length: 10 }, () => createRandomUser());
    console.log(action.payload);

    yield put(GET_TARGET_USER_PROFILE_IMAGE_LIST_SUCCESS(fakeUsers));
  } catch (error) {
    yield put(
      GET_TARGET_USER_PROFILE_IMAGE_LIST_FAILURE({
        code: error.code,
        message: error.message,
      })
    );
  }
}

function* getUserNotiStatusFlipAction(action) {
  try {
    yield put(FLIP_USER_NOTI_STATUS_SUCCESS(action.payload));
  } catch (error) {
    yield put(
      FLIP_USER_NOTI_STATUS_FAILURE({
        code: error.code,
        message: error.message,
      })
    );
  }
}

function* watchUserNotiStatusFlip() {
  yield takeLatest(
    'user/info/FLIP_USER_NOTI_STATUS_REQUEST',
    getUserNotiStatusFlipAction
  );
}

function* watchUserProfileImage() {
  yield takeLatest(
    'user/info/GET_TARGET_USER_PROFILE_IMAGE_LIST_REQUEST',
    getUserProfileImageListAction
  );
}

export default function* userInfoSettingSaga() {
  yield all([fork(watchUserNotiStatusFlip)]);
  yield all([fork(watchUserProfileImage)]);
}
