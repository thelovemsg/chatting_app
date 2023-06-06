import { all } from 'axios';
import {
  GET_USER_UPDATE_FRIENDS_FAILURE,
  GET_USER_UPDATE_FRIENDS_SUCCESS,
} from 'reducers/user/userUpdatedFriends';
import { fork, put, takeLatest } from 'redux-saga/effects';

function* getUserUpdateFriendsAction() {
  try {
    console.log('getUserUpdateFriendsAction test....');
    yield put(GET_USER_UPDATE_FRIENDS_SUCCESS());
  } catch (error) {
    yield put(GET_USER_UPDATE_FRIENDS_FAILURE());
    console.log('error occur! ::', error);
  }
}

function* watchUpdateFriends() {
  yield takeLatest(
    'user/friends/GET_USER_UPDATE_FRIENDS_REQUEST',
    getUserUpdateFriendsAction
  );
}

export default function* userUpdateFriendsSaga() {
  yield all([fork(watchUpdateFriends)]);
}
