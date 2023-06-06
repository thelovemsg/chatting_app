import {
  GET_USER_FRIENDS_FAILURE,
  //   GET_USER_FRIENDS_SUCCESS,
} from 'reducers/user/userFriends';
import { all, fork, put, takeLatest } from 'redux-saga/effects';
// import { getFriends } from '../../api/member/user/user';

function* getBirthdayFriendsAction(action) {
  try {
    console.log('getFriendsAction :: ', action);
    // const result = yield call(getBirthdayFriends, action.payload);
    // console.log('getFriendsAction :: ', result);
    // yield put(GET_USER_FRIENDS_SUCCESS(result.data));
  } catch (error) {
    yield put(
      GET_USER_FRIENDS_FAILURE({ code: error.code, message: error.message })
    );
  }
}

function* watchGetBirthdayFriends() {
  yield takeLatest(
    'user/friends/GET_USER_BIRTHDAY_FRIENDS_REQUEST',
    getBirthdayFriendsAction
  );
}

export default function* userBirthdayFriendsSaga() {
  yield all([fork(watchGetBirthdayFriends)]);
}
