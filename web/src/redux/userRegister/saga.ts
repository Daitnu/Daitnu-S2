import { call, put, all, takeLatest } from 'redux-saga/effects';
import UserApi from '~/library/request/UserApi';
import { REGISTER_SUCCESS, REGISTER_FAILURE, REGISTER_REQUEST } from './actions';

function* register$(action) {
  try {
    const payload = yield call(new UserApi().register, action.payload);
    yield put({ type: REGISTER_SUCCESS, payload });
  } catch (err) {
    yield put({ type: REGISTER_FAILURE, payload: err });
  }
}

export function* userRegisterSaga() {
  yield all([takeLatest(REGISTER_REQUEST, register$)]);
}
