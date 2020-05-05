import { call, put, all, takeLatest } from 'redux-saga/effects';
import UserApi from '~/library/request/UserApi';
import { LOGIN_REQUEST, LOGIN_SUCCESS, LOGIN_FAILURE } from './actions';

function* login$(action) {
  try {
    const api = new UserApi();
    const payload = yield call(api.login.bind(api), action.payload);
    yield put({ type: LOGIN_SUCCESS, payload });
  } catch (err) {
    yield put({ type: LOGIN_FAILURE, payload: err });
  }
}

export function* userLoginSaga() {
  yield all([takeLatest(LOGIN_REQUEST, login$)]);
}
