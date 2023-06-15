import {
  FLIP_USER_NOTI_STATUS_SUCCESS,
  FLIP_USER_NOTI_STATUS_FAILURE,
} from 'reducers/user/userInfoSetting';
import { all, fork, put, takeLatest } from 'redux-saga/effects';

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

export default function* userInfoSettingSaga() {
  yield all([fork(watchUserNotiStatusFlip)]);
}
