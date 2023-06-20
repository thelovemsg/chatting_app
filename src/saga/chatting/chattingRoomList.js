import { all, fork, put, takeLatest } from 'redux-saga/effects';
import { createChattingRoomInfo } from 'component/utility/FakeUser';
import {
  GET_USER_CHATTING_ROOM_FAILURE,
  GET_USER_CHATTING_ROOM_SUCCESS,
} from '../../reducers/chatting/chattingReducer';

function* getChattingRoomListAction(action) {
  try {
    // const data = yield call(addMultipleProfileApi, action.payload);
    console.log('action in chatting room request... :: ', action);

    const fakeUsers = Array.from({ length: 23 }, () =>
      createChattingRoomInfo()
    );

    yield put(GET_USER_CHATTING_ROOM_SUCCESS(fakeUsers));
  } catch (error) {
    yield put(
      GET_USER_CHATTING_ROOM_FAILURE({
        code: error.response.data?.code,
        error: error.response.data?.message,
      })
    );
  }
}

function* watchGetChattingRoom() {
  yield takeLatest(
    'user/chattingRoom/GET_USER_CHATTING_ROOM_REQUEST',
    getChattingRoomListAction
  );
}

export default function* userChattingRoomSaga() {
  yield all([fork(watchGetChattingRoom)]);
}
