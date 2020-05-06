import { all, takeLatest } from 'redux-saga/effects';
import { LOGIN_REQUEST, LOGIN } from './actions';
import { makeApiCallSagaFunc } from '~/redux/util';
import sagaApiFuncParams from '~/redux/util/sagaApiFuncParams';

const login$ = makeApiCallSagaFunc(LOGIN, sagaApiFuncParams[LOGIN]);

export function* userLoginSaga() {
  yield all([takeLatest(LOGIN_REQUEST, login$)]);
}
