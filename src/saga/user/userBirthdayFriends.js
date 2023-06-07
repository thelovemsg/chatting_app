import { createRandomUser } from 'component/utility/FakeUser';
import { GET_USER_BIRTHDAY_FRIENDS_SUCCESS } from 'reducers/user/userBirthdayFriends';
import { GET_USER_FRIENDS_FAILURE } from 'reducers/user/userFriends';
import { all, fork, put, takeLatest } from 'redux-saga/effects';
// import { getFriends } from '../../api/member/user/user';

function* getBirthdayFriendsAction(action) {
  console.log('getFriendsAction :: ', action);
  try {
    const fakeUsers = Array.from({ length: 3 }, () => createRandomUser());
    // const result = yield call(getBirthdayFriends, action.payload);
    // console.log('getFriendsAction :: ', result);
    yield put(GET_USER_BIRTHDAY_FRIENDS_SUCCESS(fakeUsers));
  } catch (error) {
    yield put(
      GET_USER_FRIENDS_FAILURE({ code: error.code, message: error.message })
    );
  }
}

function* watchGetBirthdayFriends() {
  yield takeLatest(
    'user/birthday/GET_USER_BIRTHDAY_FRIENDS_REQUEST',
    getBirthdayFriendsAction
  );
}

export default function* userBirthdayFriendsSaga() {
  yield all([fork(watchGetBirthdayFriends)]);
}
