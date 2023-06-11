import { createRandomUser } from 'component/utility/FakeUser';
import { GET_USER_BIRTHDAY_FRIENDS_SUCCESS } from 'reducers/user/userBirthdayFriends';
import { GET_USER_FRIENDS_FAILURE } from 'reducers/user/userFriends';
import { all, fork, put, takeLatest } from 'redux-saga/effects';

function* getBirthdayFriendsAction(action) {
  console.log('action in getBirthdayFriendsAction :: ', action);
  try {
    const fakeUsers = [];

    fakeUsers.push(createRandomUser(new Date('2023-06-05')));
    fakeUsers.push(createRandomUser(new Date('2023-06-05')));
    fakeUsers.push(createRandomUser(new Date('2023-06-06')));

    fakeUsers.push(createRandomUser(new Date('2023-06-10')));

    fakeUsers.push(createRandomUser(new Date('2023-06-11')));
    fakeUsers.push(createRandomUser(new Date('2023-06-12')));

    fakeUsers.push(createRandomUser(new Date('2023-06-30')));
    fakeUsers.push(createRandomUser(new Date('2023-06-29')));
    fakeUsers.push(createRandomUser(new Date('2023-06-30')));
    fakeUsers.push(createRandomUser(new Date('2023-06-29')));
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
