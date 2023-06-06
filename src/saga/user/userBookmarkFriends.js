import {
  //   GET_USER_BOOKMARK_FRIENDS_SUCCESS,
  GET_USER_BOOKMARK_FRIENDS_FAILURE,
} from 'reducers/user/userBookmarkFriends';
import { all, fork, put, takeLatest } from 'redux-saga/effects';
// import { getFriends } from '../../api/member/user/user';

function* getBirthdayFriendsAction(action) {
  try {
    // const result = yield call(getBirthdayFriends, action.payload);
    // console.log('getFriendsAction :: ', result);
    // yield put(GET_USER_BOOKMARK_FRIENDS_SUCCESS(result.data));
    console.log('getBirthdayFriendsAction action ... :: ', action);
  } catch (error) {
    yield put(
      GET_USER_BOOKMARK_FRIENDS_FAILURE({
        code: error.code,
        message: error.message,
      })
    );
  }
}

function* watchGetBirthdayFriends() {
  yield takeLatest(
    'user/friends/GET_USER_BOOKMARK_FRIENDS_REQUEST',
    getBirthdayFriendsAction
  );
}

export default function* userBirthdayFriendsSaga() {
  yield all([fork(watchGetBirthdayFriends)]);
}
