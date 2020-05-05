import { all, takeLatest } from 'redux-saga/effects';
import { LOGIN_REQUEST, LOGIN } from './actions';
import { makeApiCallSagaFunc } from '~/redux/util';

const login$ = makeApiCallSagaFunc(LOGIN);

export function* userLoginSaga() {
  yield all([takeLatest(LOGIN_REQUEST, login$)]);
}
