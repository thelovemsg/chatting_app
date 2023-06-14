import { createRandomUser } from 'component/utility/FakeUser';
import {
  GET_USER_UPDATE_FRIENDS_FAILURE,
  GET_USER_UPDATE_FRIENDS_SUCCESS,
} from 'reducers/user/userUpdatedFriends';
import { all, fork, put, takeLatest } from 'redux-saga/effects';

function* getUserUpdateFriendsAction() {
  try {
    const fakeUsers = Array.from({ length: 7 }, () => createRandomUser());
    yield put(GET_USER_UPDATE_FRIENDS_SUCCESS(fakeUsers));
  } catch (error) {
    yield put(GET_USER_UPDATE_FRIENDS_FAILURE());
    console.log('error occur! ::', error);
  }
}

function* watchUpdateFriends() {
  yield takeLatest(
    'user/updateFriend/GET_USER_UPDATE_FRIENDS_REQUEST',
    getUserUpdateFriendsAction
  );
}

export default function* userUpdateFriendsSaga() {
  yield all([fork(watchUpdateFriends)]);
}
