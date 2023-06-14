import { createRandomUser } from 'component/utility/FakeUser';
import {
  GET_USER_FRIENDS_FAILURE,
  GET_USER_FRIENDS_SUCCESS,
  //   GET_USER_FRIENDS_SUCCESS,
} from 'reducers/user/userFriends';
import { all, fork, put, takeLatest } from 'redux-saga/effects';
// import { getFriends } from '../../api/member/user/user';

function* getFriendsAction() {
  try {
    // const result = yield call(getFriends, action.payload);
    // console.log('getFriendsAction :: ', result);
    const fakeUsers = Array.from({ length: 23 }, () => createRandomUser());
    yield put(GET_USER_FRIENDS_SUCCESS(fakeUsers));
  } catch (error) {
    yield put(
      GET_USER_FRIENDS_FAILURE({ code: error.code, message: error.message })
    );
  }
}

function* watchGetFriends() {
  yield takeLatest('user/friends/GET_USER_FRIENDS_REQUEST', getFriendsAction);
}

export default function* userFriendsSaga() {
  yield all([fork(watchGetFriends)]);
}
