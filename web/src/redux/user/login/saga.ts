import { all, takeLatest } from 'redux-saga/effects';
import { LOGIN_REQUEST, LOGIN } from './actions';
import { makeApiCallSagaFunc } from '~/redux/util';
import UserApi from '~/library/request/UserApi';

const api = new UserApi();
const login$ = makeApiCallSagaFunc({ type: LOGIN, apiFunc: api.login.bind(api) });

export function* userLoginSaga() {
  yield all([takeLatest(LOGIN_REQUEST, login$)]);
}
