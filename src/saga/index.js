import { all, fork } from 'redux-saga/effects';
import loginSaga from './login';
import userMultiProfileSaga from './user/userMutliProfile';
import userBirthdayFriendsSaga from './user/userBirthdayFriends';
import userProfileSaga from './user/userProfile';
import userUpdateFriendSaga from './user/userUpdateFriends';
import userFriendsSaga from './user/uersFriends';

export function* rootSaga() {
  yield all([
    fork(loginSaga),
    fork(userProfileSaga),
    fork(userBirthdayFriendsSaga),
    fork(userFriendsSaga),
    fork(userMultiProfileSaga),
    fork(userUpdateFriendSaga),
  ]); // all 은 배열 안의 여러 사가를 동시에 실행시켜줍니다.
}

export default rootSaga;
