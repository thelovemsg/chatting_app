import { all, fork } from "redux-saga/effects";
import userSaga from "./user";

export function* rootSaga() {
  yield all([fork(userSaga)]); // all 은 배열 안의 여러 사가를 동시에 실행시켜줍니다.
}

export default rootSaga;
